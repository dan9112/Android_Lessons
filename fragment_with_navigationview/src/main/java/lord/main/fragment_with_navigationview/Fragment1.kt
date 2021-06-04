package lord.main.fragment_with_navigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import lord.main.fragment_with_navigationview.databinding.Fragment1Binding

/**
 * Фрагмент для взаимодействия с выбранным проектом
 */
class Fragment1 : Fragment(), LifecycleObserver {
    /**
     * Виджет для отображения иконки выбранного проекта (если есть)
     */
    private lateinit var currentProjectIcon: ImageView

    /**
     * Виджет для вывода заголовка выбранного проекта
     */
    private lateinit var currentProjectTitle: TextView

    /**
     * Виджет для вывода описания выбранного проекта
     */
    private lateinit var currentProjectDescription: TextView

    /**
     * Виджет навигационного меню. Перезаписывается содержимое при загрузке фрагмента, но меню
     * выбора проекта возвращается перед уничтожением фрагмента
     */
    private lateinit var navView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<Fragment1Binding>(
            inflater,
            R.layout.fragment_1,
            container,
            false
        )
        currentProjectIcon = binding.currentProjectIcon
        currentProjectTitle = binding.currentProjectTitle
        currentProjectDescription = binding.currentProjectDescription
        viewLifecycleOwner.lifecycle.addObserver(this)// подписываемся на события жизненного цикла родительской активности
        return binding.root
    }

    /**
     * Функция для получения и обработки данных родительской активности.
     *
     * Важно: до окончания события onCreate активности данные, которые инициализируются в нём, будут
     * возвращать значения null!
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onActivityCreated() {
        viewLifecycleOwner.lifecycle.removeObserver(this)// отменяем подписку на события жизненного цикла родительской активности
        navView = (activity as ActivityMain).navView

        when (Fragment1Args.fromBundle(requireArguments()).code) {
            0 -> {
                currentProjectIcon.setImageResource(R.drawable.kamaz13)
                currentProjectTitle.text = resources.getString(R.string.kamaz13_title)
                currentProjectDescription.text = resources.getString(R.string.kamaz13_description)

                navView.menu.clear()
                navView.inflateMenu(R.menu.nav_drawer_kamaz13_menu)
            }
            1 -> {
                currentProjectIcon.setImageResource(R.drawable.code666)
                currentProjectTitle.text = resources.getString(R.string.code666_title)
                currentProjectDescription.text = resources.getString(R.string.code666_description)

                navView.menu.clear()
                navView.inflateMenu(R.menu.nav_drawer_code666)
            }
            2 -> {
                currentProjectIcon.visibility = View.GONE
                currentProjectTitle.text = resources.getString(R.string.friday__title)
                currentProjectDescription.text = resources.getString(R.string.friday__description)

                navView.menu.clear()
            }
        }

        navView.getHeaderView(0).setOnClickListener {
            (activity as ActivityMain).drawerLayout.close()
            findNavController().navigate(Fragment1Directions.actionFragment1ToFragmentWelcome())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        navView.menu.clear()
        navView.inflateMenu(R.menu.navdrawer_0_menu)
    }
}