package co.com.flypass.flypassNative.ui.login

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.databinding.PasswordBinding

class Password: Fragment(R.layout.password) {
    private lateinit var binding: PasswordBinding
    private val args by navArgs<PasswordArgs>()
    private lateinit var document: String
    private var showPassword = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PasswordBinding.bind(view)
        binding.btnSeePassword.setOnClickListener {
            showPassword = !showPassword
            if(showPassword) {
                binding.txtPassword.transformationMethod = null
            } else {
                binding.txtPassword.transformationMethod = PasswordTransformationMethod()
            }
        }
        document = args.document
    }
}