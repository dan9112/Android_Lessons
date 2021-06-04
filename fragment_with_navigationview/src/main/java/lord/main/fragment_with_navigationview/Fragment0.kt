package lord.main.fragment_with_navigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import lord.main.fragment_with_navigationview.databinding.Fragment0Binding

import androidx.lifecycle.Lifecycle.Event.ON_CREATE

/**
 * Фрагмент для отображения краткой информации о проектах, выбранных в меню навигации. Перехватывает
 * его у родительской активности
 */
class Fragment0 : Fragment(), LifecycleObserver {
    /**
     * Виджет для отображения иконки проекта (если есть)
     */
    private lateinit var projectIcon: ImageView

    /**
     * Виджет для вывода заголовка проекта
     */
    private lateinit var projectTitle: TextView

    /**
     * Виджет для вывода краткого описания проекта
     */
    private lateinit var projectDescription: TextView

    /**
     * Код, по которому определяется какой проект нужно отобразить во фрагменте
     */
    private var code = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<Fragment0Binding>(
            inflater,
            R.layout.fragment_0,
            container,
            false
        )

        val args = Fragment0Args.fromBundle(requireArguments())// получаем передаваемые фрагменту аргументы
        code = args.code

        projectIcon = binding.projectIcon
        projectTitle = binding.projectTitle
        projectDescription = binding.projectDescription

        binding.projectGo.setOnClickListener { view: View ->
            if (code != -1) view.findNavController()
                .navigate(Fragment0Directions.actionFragment0ToFragment1(code))
            else Toast.makeText(context, "Ошибка перехода!", Toast.LENGTH_SHORT).show()
        }
        viewLifecycleOwner.lifecycle.addObserver(this)// подписываемся на события жизненного цикла родительской активности
        return binding.root
    }

    /**
     * Функция для получения и обработки данных родительской активности.
     *
     * Важно: до окончания события onCreate активности данные, которые инициализируются в нём, будут
     * возвращать значения null!
     */
    @OnLifecycleEvent(ON_CREATE)
    fun onActivityCreated() {
        viewLifecycleOwner.lifecycle.removeObserver(this)// отменяем подписку на события жизненного цикла родительской активности
        val navView = (activity as ActivityMain).navView
        val drawerLayout = (activity as ActivityMain).drawerLayout
        navView.menu.clear()
        navView.inflateMenu(R.menu.navdrawer_0_menu)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.kamaz13 -> {
                    code = 0
                }
                R.id.code666 -> {
                    code = 1
                }
                R.id.friday_ -> {
                    code = 2
                }
                R.id.fragment_info -> {
                    findNavController().navigate(Fragment0Directions.actionFragment0ToFragmentInfo())
                    return@setNavigationItemSelectedListener true
                }
                R.id.fragmentSettings -> {
                    findNavController().navigate(Fragment0Directions.actionFragment0ToFragmentSettings())
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    code = -1
                    return@setNavigationItemSelectedListener true
                }
            }
            drawerLayout.close()
            findNavController().navigate(Fragment0Directions.actionFragment0Self(code))
            true
        }
        // вешаем слушатель нажатия на виджет заголовка навигационного окна, который возвращает пользователя во фрагмент приветствия
        navView.getHeaderView(0).setOnClickListener {
            drawerLayout.close()
            findNavController().navigate(Fragment0Directions.actionFragment0ToFragmentWelcome())
        }
        // определяем по коду какой проект нужно отобразить и выводим его данные пользователю
        when (code) {
            0 -> {
                projectIcon.visibility = View.VISIBLE
                projectIcon.setImageResource(R.drawable.kamaz13)
                projectTitle.setText(R.string.kamaz13_title)
                projectDescription.setText(R.string.kamaz13_description_short)
            }
            1 -> {
                projectIcon.visibility = View.VISIBLE
                projectIcon.setImageResource(R.drawable.code666)
                projectTitle.setText(R.string.code666_title)
                projectDescription.setText(R.string.code666_description_short)
            }
            2 -> {
                projectIcon.visibility = View.GONE
                projectTitle.setText(R.string.friday__title)
                projectDescription.setText(R.string.friday__description_short)

            }
        }
    }
}