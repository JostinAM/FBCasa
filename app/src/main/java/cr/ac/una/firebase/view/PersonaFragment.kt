package cr.ac.una.firebase.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.ac.una.firebase.R
import cr.ac.una.firebase.adapter.PersonaAdapter
import cr.ac.una.firebase.databinding.FragmentPersonaBinding
import cr.ac.una.firebase.entity.Persona
import cr.ac.una.firebase.viewModel.PersonaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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

    private fun deletePersona(ins: String) {
        GlobalScope.launch(Dispatchers.Main) {
            if(ins!=null){
                personaViewModel.deletePersona(ins)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<RecyclerView>(R.id.list_view)

        personas = mutableListOf<Persona>()

        var adapter = PersonaAdapter(personas as ArrayList<Persona>)

        listView.adapter = adapter

        listView.layoutManager = LinearLayoutManager(requireContext())

        personaViewModel = ViewModelProvider(requireActivity()).get(PersonaViewModel::class.java)


        personaViewModel.personas.observe(viewLifecycleOwner){
            elementos->
            adapter.updateData(elementos as ArrayList<Persona>)
            personas = elementos
        }

        GlobalScope.launch(Dispatchers.Main) {
            personaViewModel.getPersonas()!!
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (position!=0){
                    val ins = personas[position]
                    (personas as MutableList<Persona>).removeAt(position)
                    adapter.notifyItemRemoved(position)

//                    adapter.updateData(personas as ArrayList<Persona>)
                    deletePersona(ins.id)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if(viewHolder is PersonaAdapter.ViewHolder){
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )

                    if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                        val itemView = viewHolder.itemView
                        val paint = Paint()
                        paint.color = Color.RED
                        val deleteIcon = ContextCompat.getDrawable(
                            requireContext(),
                            android.R.drawable.ic_menu_delete
                        )
                        val iconMargin = (itemView.height - deleteIcon!!.intrinsicHeight) / 2
                        val iconTop =
                            itemView.top + (itemView.height - deleteIcon.intrinsicHeight) / 2
                        val iconBottom = iconTop + deleteIcon.intrinsicHeight

                        // Dibuja el fondo rojo
                        c.drawRect(
                            itemView.left.toFloat(),
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat(),
                            paint
                        )

                        // Calcula las posiciones del icono de eliminar
                        val iconLeft = itemView.right - iconMargin - deleteIcon.intrinsicWidth
                        val iconRight = itemView.right - iconMargin
                        deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                        // Dibuja el icono de eliminar
                        deleteIcon.draw(c)
                    }
                }
            }

        })

        itemTouchHelper.attachToRecyclerView(listView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}