package cr.ac.una.firebase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.ac.una.firebase.R
import cr.ac.una.firebase.adapter.PersonaAdapter
import cr.ac.una.firebase.databinding.FragmentPersonaBinding
import cr.ac.una.firebase.entity.Persona
import cr.ac.una.firebase.viewModel.PersonaViewModel


class PersonaFragment : Fragment() {

    private var _binding: FragmentPersonaBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaViewModel : PersonaViewModel
    //listapersonas
    private lateinit var personas :List<Persona>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonaBinding.inflate(inflater, container, false  )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<RecyclerView>(R.id.list_view)

        personas = mutableListOf<Persona>()

        var adapter = PersonaAdapter(personas as ArrayList<Persona>)


        listView.adapter = adapter

        listView.layoutManager = LinearLayoutManager(requireContext())

        personaViewModel = ViewModelProvider(requireActivity()).get(PersonaViewModel::class.java)


        //personaViewModel.personas

    }

}