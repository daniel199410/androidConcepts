package co.com.flypass.flypassNative.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.databinding.DocumentBinding

class Document: Fragment(R.layout.document){
    private lateinit var binding: DocumentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DocumentBinding.bind(view)
    }
}