package cr.ac.una.firebase.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cr.ac.una.firebase.R
import cr.ac.una.firebase.databinding.FragmentSecondBinding
import cr.ac.una.firebase.viewModel.PersonaViewModel


class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _biding: FragmentSecondBinding? = null

    private val binding get() = _biding!!
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _biding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personaViewModel = ViewModelProvider(requireActivity()).get(PersonaViewModel::class.java)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.button.setOnClickListener {
            val nombre = binding.editNombre.text.toString()
            Log.d("SecondFragment", "nombre: $nombre")

            personaViewModel.addPersona(nombre)

            //create persona
//            personaViewModel.insertPersona(nombre, apellido, edad)
        }
    }
}