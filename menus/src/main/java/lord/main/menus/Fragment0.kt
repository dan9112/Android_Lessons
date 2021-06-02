package lord.main.menus

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lord.main.menus.databinding.Fragment0Binding


class Fragment0 : Fragment() {

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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment0_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}