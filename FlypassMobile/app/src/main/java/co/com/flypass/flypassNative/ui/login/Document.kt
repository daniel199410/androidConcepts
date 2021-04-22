package co.com.flypass.flypassNative.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.databinding.DocumentBinding
import co.com.flypass.flypassNative.presentation.LoginViewModel
import co.com.flypass.flypassNative.presentation.LoginViewModelFactory
import co.com.flypass.flypassNative.repository.UserRepository
import com.google.android.material.snackbar.Snackbar

class Document: Fragment(R.layout.document){
    private val viewModel by viewModels<LoginViewModel> { LoginViewModelFactory(UserRepository()) }
    private lateinit var binding: DocumentBinding
    private lateinit var snackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackBar = Snackbar.make(view, R.string.user_not_found, Snackbar.LENGTH_LONG)
        binding = DocumentBinding.bind(view)
        binding.btnNext.setOnClickListener{
            if(!binding.txtDocument.text.isNullOrBlank()) {
                userExists(binding.txtDocument.text.toString())
            }
        }
    }

    private fun userExists(document: String) {
        viewModel.userExists(document).observe(
            viewLifecycleOwner, {
                when(it) {
                    is Resource.Loading -> binding.progessBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progessBar.visibility = View.GONE
                        if(it.data.body == 1) {
                            val action = DocumentDirections.actionDocumentToPassword(document)
                            findNavController().navigate(action)
                        } else {
                            snackBar.show()
                        }
                    }
                    is Resource.Failure -> {
                        binding.progessBar.visibility = View.GONE
                    }
                }
            }
        )
    }
}