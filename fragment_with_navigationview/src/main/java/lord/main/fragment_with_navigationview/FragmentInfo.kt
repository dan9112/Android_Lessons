package lord.main.fragment_with_navigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import lord.main.fragment_with_navigationview.databinding.Fragment1Binding

class FragmentInfo : Fragment() {

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

        (activity as ActivityMain).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        val icon = binding.currentProjectIcon
        icon.setImageResource(R.drawable.ic_info)
        binding.currentProjectTitle.text = "О программе"
        binding.currentProjectDescription.text = "В разработке..."
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        (activity as ActivityMain).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}