package lord.main.fragment_with_navigationview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import lord.main.fragment_with_navigationview.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    /**
     * Layout с [виджетом навигации][navView].
     *
     * В закрытом состоянии находится за пределами экрана, при открытии выдвигается со стороны, куда "направлена" его "гравитация"
     */
    lateinit var drawerLayout: DrawerLayout

    /**
     * Виджет навигации
     */
    lateinit var navView: NavigationView

    var code = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        navView = binding.navView
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return if (drawerLayout.isOpen) {
            drawerLayout.close()
            navController.navigateUp()
        } else NavigationUI.navigateUp(navController, drawerLayout)
    }
}