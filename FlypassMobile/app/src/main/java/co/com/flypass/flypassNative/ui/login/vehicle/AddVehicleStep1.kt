package co.com.flypass.flypassNative.ui.login.vehicle

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.flypass.flypassNative.R
import co.com.flypass.flypassNative.databinding.FragmentAddVehicleStep1Binding
import co.com.flypass.flypassNative.databinding.VehicleFragmentBinding

class AddVehicleStep1 : Fragment(R.layout.fragment_add_vehicle_step1) {
    private lateinit var binding: FragmentAddVehicleStep1Binding
    private var message: String = "¡Ojo! antes de continuar…\n<br/>\nSi tu carro tiene activo otro servicio de pago electrónico de peajes, deberás desvincularlo eliminando tu vehículo de su plataforma,\nsolo así podrás ser Flypass y comenzar a viajar cómodo y feliz.\n<br/>\n<br/>\nConoce cómo desvincularte de:\n<br/>\n<a href=\"http://gopass.com.co/desvincular.php\" target=\"_blank\">Gopass</a><br/>\n<a href=\"http://www.copilotocolombia.com/contactanos\" target=\"_blank\">Copiloto</a>"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddVehicleStep1Binding.bind(view)
        binding.htmlText.text = Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY)
    }
}