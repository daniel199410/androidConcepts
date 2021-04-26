package co.com.flypass.flypassNative.ui.login.bottomNavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.core.Resource
import co.com.flypass.flypassNative.data.model.*
import co.com.flypass.flypassNative.databinding.RvVehicleBinding
import co.com.flypass.flypassNative.databinding.VehicleFragmentBinding
import co.com.flypass.flypassNative.presentation.LoginViewModel
import co.com.flypass.flypassNative.presentation.PasswordViewModelFactory
import co.com.flypass.flypassNative.presentation.VehicleModelFactory
import co.com.flypass.flypassNative.presentation.VehicleViewModel
import co.com.flypass.flypassNative.repository.SecurityRepository
import co.com.flypass.flypassNative.repository.VehicleRepository
import co.com.flypass.flypassNative.ui.login.PasswordDirections
import java.util.*

class VehicleFragment : Fragment(R.layout.vehicle_fragment) {
    private val viewModel by viewModels<VehicleViewModel> { VehicleModelFactory(VehicleRepository(requireContext())) }
    private var vehicles: MutableList<Vehicle> = ArrayList()
    var page = 1
    var limit = 10

    lateinit var adapter: VehicleAdapter
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: VehicleFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = VehicleFragmentBinding.bind(view)
        layoutManager = LinearLayoutManager(context)
        binding.rvVehicles.layoutManager = layoutManager
        adapter = VehicleAdapter(vehicles)
        binding.rvVehicles.adapter = adapter
        getVehicles()
    }

    private fun getVehicles() {
        viewModel.getVehicles("", page).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    vehicles.addAll(it.data.body.content)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    Log.e("Error", it.toString())
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    class VehicleAdapter(private val vehicles: MutableList<Vehicle>): RecyclerView.Adapter<VehicleAdapter.VehiclesViewHolder>() {

        class VehiclesViewHolder(private val binding: RvVehicleBinding): RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(item: Vehicle) {
                binding.vehicleCategory.text = item.carConfiguration.configuration
                binding.vehicleLicensePlate.text = item.licensePlate
                binding.vehicleSpecifications.text = "${item.brand.brandDescription} ${item.brandSeries.seriesDescription} ${item.color.color}"
                binding.vehicleTitular.text = item.titular
                binding.vehicleStatus.text = item.status.name
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
            val itemBinding = RvVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return VehiclesViewHolder(itemBinding)
        }

        override fun getItemCount(): Int = vehicles.size

        override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
            holder.bind(vehicles[position])
        }
    }

}