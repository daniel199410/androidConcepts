package co.com.flypass.flypassNative.ui.login.vehicle

import android.opengl.Visibility
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.core.RegexExpressions
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.core.Validators
import co.com.flypass.flypassNative.databinding.FragmentAddVehicleStep1Binding
import co.com.flypass.flypassNative.databinding.VehicleFragmentBinding
import co.com.flypass.flypassNative.presentation.VehicleModelFactory
import co.com.flypass.flypassNative.presentation.VehicleViewModel
import co.com.flypass.flypassNative.repository.VehicleRepository

class AddVehicleStep1 : Fragment(R.layout.fragment_add_vehicle_step1) {
    private val viewModel by viewModels<VehicleViewModel> { VehicleModelFactory(VehicleRepository(requireContext())) }
    private lateinit var binding: FragmentAddVehicleStep1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getVehicleMessages()
        binding = FragmentAddVehicleStep1Binding.bind(view)
        binding.btnNext.setOnClickListener {
            if(checkValidations()) {

            }
        }
    }

    private fun getVehicleMessages() {
        viewModel.getVehicleMessages().observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Loading -> binding.shimmerLayout.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.htmlText.text = Html.fromHtml(it.data.body, Html.FROM_HTML_MODE_LEGACY)
                    binding.shimmerLayout.visibility = View.GONE
                    binding.htmlText.visibility = View.VISIBLE
                }
                is Resource.Failure -> {
                    Log.e("Error", it.toString())
                    binding.shimmerLayout.visibility = View.GONE
                }
            }
        })
    }

    private fun checkValidations(): Boolean {
        var valid = true
        if(binding.txtLicensePlate.text.isNullOrEmpty()) {
            binding.txtLayoutLicensePlate.error = resources.getString(R.string.required_field)
            valid = false
        }else if(RegexExpressions.LICENSE_PLATE.matches(binding.txtLicensePlate.text.toString())) {
            binding.txtLayoutLicensePlate.error = resources.getString(R.string.invalid_license_plate)
            valid = false
        } else {
            binding.txtLayoutLicensePlate.error = null
        }
        if(binding.txtConfirmLicensePlate.text.isNullOrEmpty()) {
            binding.txtLayoutConfirmLicensePlate.error = resources.getString(R.string.required_field)
            valid = false
        } else if(binding.txtConfirmLicensePlate.text.toString() != binding.txtLicensePlate.text.toString()) {
            binding.txtLayoutConfirmLicensePlate.error = resources.getString(R.string.license_plate_match_error)
            valid = false
        } else {
            binding.txtLayoutConfirmLicensePlate.error = null
        }
        return valid
    }
}