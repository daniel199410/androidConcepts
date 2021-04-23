package co.com.flypass.flypassNative.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.databinding.ActivityHomeBinding
import co.com.flypass.flypassNative.ui.login.bottomNavigation.AccountStatusFragment
import co.com.flypass.flypassNative.ui.login.bottomNavigation.VehicleFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.item_menu_account_status -> {
                    openFragment(AccountStatusFragment())
                    true
                }
                R.id.menu_item_vehicle -> {
                    openFragment(VehicleFragment())
                    true
                }
                else -> false
            }
        }
        bottom_navigation.selectedItemId = R.id.item_menu_account_status
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}