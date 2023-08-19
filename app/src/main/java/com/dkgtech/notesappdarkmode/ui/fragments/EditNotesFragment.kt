package com.dkgtech.notesappdarkmode.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.dkgtech.notesappdarkmode.R
import com.dkgtech.notesappdarkmode.databinding.FragmentEditNotesBinding
import com.dkgtech.notesappdarkmode.model.NoteModel
import com.dkgtech.notesappdarkmode.viewModel.NotesViewModel
import java.util.Date

class EditNotesFragment : Fragment() {

    val notes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        with(binding) {
            edtTitle.setText(notes.data.title)
            edtSubtitle.setText(notes.data.subtitle)
            edtNote.setText(notes.data.note)

            when (notes.data.priority) {
                "1" -> {
                    priority = "1"
                    pGreen.setImageResource(R.drawable.icon_check)
                    pYellow.setImageResource(0)
                    pRed.setImageResource(0)
                }

                "2" -> {
                    priority = "2"
                    pYellow.setImageResource(R.drawable.icon_check)
                    pGreen.setImageResource(0)
                    pRed.setImageResource(0)
                }

                "3" -> {
                    priority = "3"
                    pRed.setImageResource(R.drawable.icon_check)
                    pGreen.setImageResource(0)
                    pYellow.setImageResource(0)
                }
            }

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

            btnUpdateNotes.setOnClickListener {
                updateNotes(it)
            }

        }

        return (binding.root)
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubtitle.text.toString()
        val note = binding.edtNote.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = NoteModel(notes.data.id, title, subtitle, note, date.toString(), priority)
        viewModel.updateNote(data)

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

}