package co.com.flypass.flypassNative.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.databinding.FragmentSplahBinding

class SplashFragment : Fragment(R.layout.fragment_splah) {
    private lateinit var binding: FragmentSplahBinding
    private var loggedIn = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplahBinding.bind(view)
        val action: NavDirections
        if(loggedIn) {
            action = SplashFragmentDirections.actionSplashFragmentToHomeActivity()
            activity?.finish()
        } else {
            action = SplashFragmentDirections.actionSplashFragmentToDocument()
        }
        findNavController().navigate(action)
    }

}