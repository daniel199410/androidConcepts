package co.com.flypass.flypassNative.ui.login.bottomNavigation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.presentation.AccountStatusViewModel

class AccountStatusFragment : Fragment(R.layout.account_status_fragment) {

    companion object {
        fun newInstance() = AccountStatusFragment()
    }

    private lateinit var viewModel: AccountStatusViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountStatusViewModel::class.java)
        // TODO: Use the ViewModel
    }

}