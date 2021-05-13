package co.com.flypass.flypassNative.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.databinding.ActivityHomeBinding
import co.com.flypass.flypassNative.ui.login.bottomNavigation.AccountStatusFragment
import co.com.flypass.flypassNative.ui.login.bottomNavigation.VehicleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment? ?: return
        val navController = host.navController
        bottom_navigation.setupWithNavController(navController)
    }
}