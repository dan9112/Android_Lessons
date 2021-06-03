package lord.main.fragment_with_navigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.findNavController
import lord.main.fragment_with_navigationview.databinding.Fragment1Binding

class FragmentWelcome : Fragment(), LifecycleObserver {
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

        viewLifecycleOwner.lifecycle.addObserver(this)

        return binding.root
    }

    /*
     * Функция используется!
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onActivityCreated() {

        viewLifecycleOwner.lifecycle.removeObserver(this)

        val navView = (activity as ActivityMain).navView
        val drawerLayout = (activity as ActivityMain).drawerLayout
        navView.setNavigationItemSelectedListener { item ->
            val code: Int
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
                    findNavController().navigate(FragmentWelcomeDirections.actionFragmentWelcomeToFragmentInfo())
                    return@setNavigationItemSelectedListener true
                }
                R.id.fragmentSettings -> {
                    findNavController().navigate(FragmentWelcomeDirections.actionFragmentWelcomeToFragmentSettings())
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
            drawerLayout.close()
            if (code != -1) findNavController().navigate(
                FragmentWelcomeDirections.actionFragmentWelcomeToFragment0(
                    code
                )
            )
            else Toast.makeText(context, "Ошибка перехода!", Toast.LENGTH_SHORT).show()
            true
        }

        navView.getHeaderView(0).setOnClickListener {
            drawerLayout.close()
        }
    }
}