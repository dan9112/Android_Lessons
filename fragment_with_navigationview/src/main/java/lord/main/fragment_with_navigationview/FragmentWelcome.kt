package lord.main.fragment_with_navigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import lord.main.fragment_with_navigationview.databinding.Fragment1Binding

class FragmentWelcome : Fragment() {
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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val navView = (activity as ActivityMain).navView
        val drawerLayout = (activity as ActivityMain).drawerLayout

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
                    findNavController().navigate(R.id.action_fragmentWelcome_to_fragmentInfo)
                    return@setNavigationItemSelectedListener true
                }
                R.id.fragmentSettings -> {
                    findNavController().navigate(R.id.action_fragmentWelcome_to_fragmentSettings)
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    (activity as ActivityMain).code = -1
                    return@setNavigationItemSelectedListener true
                }
            }
            drawerLayout.close()
            findNavController().navigate(R.id.action_fragmentWelcome_to_fragment0)
            true
        }

        navView.getHeaderView(0).setOnClickListener {
            drawerLayout.close()
        }
    }
}