package lord.main.fragment_with_navigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import lord.main.fragment_with_navigationview.databinding.Fragment0Binding

class Fragment0 : Fragment() {

    private lateinit var projectIcon: ImageView
    private lateinit var projectTitle: TextView
    private lateinit var projectDescription: TextView

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
        projectIcon = binding.projectIcon
        projectTitle = binding.projectTitle
        projectDescription = binding.projectDescription

        binding.projectGo.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_fragment0_to_fragment1)
        }
        return binding.root
    }

    /**
     * Заменить на современную реализацию функционала!
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navView = (activity as ActivityMain).navView
        val drawerLayout = (activity as ActivityMain).drawerLayout
        navView.menu.clear()
        navView.inflateMenu(R.menu.navdrawer_0_menu)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.kamaz13 -> {
                    (activity as ActivityMain).code = 0
                }
                R.id.code666 -> {
                    (activity as ActivityMain).code = 1
                }
                R.id.friday_ -> {
                    (activity as ActivityMain).code = 2
                }
                R.id.fragment_info -> {
                    findNavController().navigate(R.id.action_fragment0_to_fragmentInfo)
                    return@setNavigationItemSelectedListener true
                }
                R.id.fragmentSettings -> {
                    findNavController().navigate(R.id.action_fragment0_to_fragmentSettings)
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    (activity as ActivityMain).code = -1
                    return@setNavigationItemSelectedListener true
                }
            }
            drawerLayout.close()
            findNavController().navigate(R.id.action_fragment0_self)
            true
        }

        navView.getHeaderView(0).setOnClickListener {
            drawerLayout.close()
            findNavController().navigate(R.id.action_fragment0_to_fragmentWelcome)
        }

        when ((activity as ActivityMain).code) {
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