package co.com.flypass.flypassNative.ui.login.bottomNavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.data.model.*
import co.com.flypass.flypassNative.databinding.RvVehicleBinding
import co.com.flypass.flypassNative.databinding.VehicleFragmentBinding
import kotlinx.android.synthetic.main.vehicle_fragment.*
import java.util.*

class VehicleFragment : Fragment(R.layout.vehicle_fragment) {

    private val vehicles: MutableList<Vehicle> = ArrayList()
    var page = 1
    var limit = 10

    lateinit var adapter: VehicleAdapter
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: VehicleFragmentBinding

    private lateinit var viewModel: VehicleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = VehicleFragmentBinding.bind(view)
        layoutManager = LinearLayoutManager(context)
        binding.rvVehicles.layoutManager = layoutManager
        getPage()

    }

    fun getPage() {
        val start = (page - 1) * limit
        val end = page * limit
        for(i in start..end) {
            vehicles.add(Vehicle(
                    "ABC123",
                    CarConfiguration("Vehículo"),
                    Brand("CHEVROLET"),
                    BrandSeries("SWIFT"),
                    Color("CELESTE METALIZADO"),
                    "Daniel Cataño Restrepo",
                    GeneralEnum(1, "prematriculado")))
        }
        adapter = VehicleAdapter(vehicles)
        binding.rvVehicles.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        // TODO: Use the ViewModel
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