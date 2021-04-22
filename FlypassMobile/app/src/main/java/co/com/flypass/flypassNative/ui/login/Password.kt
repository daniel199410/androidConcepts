package co.com.flypass.flypassNative.ui.login

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.core.AppConstants
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.databinding.PasswordBinding
import co.com.flypass.flypassNative.presentation.LoginViewModel
import co.com.flypass.flypassNative.presentation.PasswordViewModelFactory
import co.com.flypass.flypassNative.repository.SecurityRepository
import com.google.android.material.snackbar.Snackbar

class Password: Fragment(R.layout.password) {
    private val viewModel by viewModels<LoginViewModel> { PasswordViewModelFactory(SecurityRepository()) }
    private lateinit var binding: PasswordBinding
    private val args by navArgs<PasswordArgs>()
    private lateinit var document: String
    private var showPassword = false
    private lateinit var snackBar: Snackbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackBar = Snackbar.make(view, R.string.ivalid_user_or_password, Snackbar.LENGTH_LONG)
        document = args.document
        binding = PasswordBinding.bind(view)
        binding.btnSeePassword.setOnClickListener { togglePasswordType() }
        binding.btnLogin.setOnClickListener {
            viewModel.getToken(document, binding.txtPassword.text.toString()).observe(viewLifecycleOwner, {
                when(it) {
                    is Resource.Loading -> binding.progessBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progessBar.visibility = View.GONE
                        if(it.data.accessToken.isNotBlank()) {
                            viewModel.saveSessionState(it.data, activity)
                            val sharedPref = activity?.getSharedPreferences(AppConstants.REFERENCE_FILE_KEY, Context.MODE_PRIVATE) ?: return@observe
                            val highScore = sharedPref.getString(AppConstants.ACCESS_TOKEN, "defaultValue")
                            Log.d("Preference", highScore!!)
                            requireActivity().finish()
                        }
                    }
                    is Resource.Failure -> {
                        snackBar.show()
                        binding.progessBar.visibility = View.GONE
                    }
                }
            })
        }
    }

    private fun togglePasswordType() {
        showPassword = !showPassword
        if(showPassword) {
            binding.txtPassword.transformationMethod = null
        } else {
            binding.txtPassword.transformationMethod = PasswordTransformationMethod()
        }
    }
}