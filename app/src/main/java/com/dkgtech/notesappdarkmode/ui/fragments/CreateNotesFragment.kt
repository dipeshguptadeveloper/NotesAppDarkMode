package com.dkgtech.notesappdarkmode.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.dkgtech.notesappdarkmode.R
import com.dkgtech.notesappdarkmode.databinding.FragmentCreateNotesBinding
import com.dkgtech.notesappdarkmode.model.NoteModel
import com.dkgtech.notesappdarkmode.viewModel.NotesViewModel
import java.util.Date

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.icon_check)

        with(binding) {

            pGreen.setOnClickListener {
                priority = "1"
                pGreen.setImageResource(R.drawable.icon_check)
                pYellow.setImageResource(0)
                pRed.setImageResource(0)
            }

            pYellow.setOnClickListener {
                priority = "2"
                pYellow.setImageResource(R.drawable.icon_check)
                pGreen.setImageResource(0)
                pRed.setImageResource(0)
            }

            pRed.setOnClickListener {
                priority = "3"
                pRed.setImageResource(R.drawable.icon_check)
                pGreen.setImageResource(0)
                pYellow.setImageResource(0)
            }


            btnSaveNotes.setOnClickListener {
                createNotes(it)

            }
        }



        return binding.root
    }

    private fun createNotes(it: View) {
        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubtitle.text.toString()
        val note = binding.edtNote.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = NoteModel(null, title, subtitle, note, date.toString(), priority)
        viewModel.addNote(data)

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)

    }

}