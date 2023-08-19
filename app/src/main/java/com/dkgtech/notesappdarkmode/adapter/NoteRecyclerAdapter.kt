package com.dkgtech.notesappdarkmode.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.notesappdarkmode.R
import com.dkgtech.notesappdarkmode.databinding.NoteRowBinding
import com.dkgtech.notesappdarkmode.model.NoteModel
import com.dkgtech.notesappdarkmode.ui.fragments.HomeFragmentDirections
import com.dkgtech.notesappdarkmode.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class NoteRecyclerAdapter(
    val context: Context,
    val noteList: List<NoteModel>
) :

    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {

    val application: Application = Application()

    class ViewHolder(val binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val data = noteList[position]
            noteTitle.text = data.title
            notSubtitle.text = data.subtitle
            noteDate.text = data.date

            when (data.priority) {
                "1" -> {
                    notePriority.setBackgroundResource(R.drawable.green_dot)
                }

                "2" -> {
                    notePriority.setBackgroundResource(R.drawable.yellow_dot)
                }

                "3" -> {
                    notePriority.setBackgroundResource(R.drawable.red_dot)
                }
            }

            root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
                Navigation.findNavController(it).navigate(action)
            }

            noteDelete.setOnClickListener {
                val bottomSheet: BottomSheetDialog =
                    BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme)
                bottomSheet.setContentView(R.layout.dialog_delete)

                val textViewYes = bottomSheet.findViewById<TextView>(R.id.btnDeleteYes)
                val textViewNo = bottomSheet.findViewById<TextView>(R.id.btnDeleteNo)

                textViewYes?.setOnClickListener {
                    val viewModel = NotesViewModel(application)
                    viewModel.deleteNote(noteList[position].id!!)
                    bottomSheet.dismiss()
                }

                textViewNo?.setOnClickListener {
                    bottomSheet.dismiss()
                }


                bottomSheet.show()
            }
        }
    }
}