package com.dkgtech.notesappdarkmode.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dkgtech.notesappdarkmode.R
import com.dkgtech.notesappdarkmode.adapter.NoteRecyclerAdapter
import com.dkgtech.notesappdarkmode.databinding.FragmentHomeBinding
import com.dkgtech.notesappdarkmode.model.NoteModel
import com.dkgtech.notesappdarkmode.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var filterNoteList = arrayListOf<NoteModel>()
    lateinit var notesAdapter: NoteRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        with(binding) {


//            for all notes

            viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
                filterNoteList = noteList as ArrayList<NoteModel>
                rcViewNotes.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                notesAdapter = NoteRecyclerAdapter(requireContext(), noteList)
                rcViewNotes.adapter = notesAdapter
            }

            btnFab.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_createNotesFragment)
            }

//            for high priority notes

            filterHigh.setOnClickListener {
                viewModel.getHighNotes().observe(viewLifecycleOwner) { noteList ->
                    filterNoteList = noteList as ArrayList<NoteModel>
                    notesAdapter = NoteRecyclerAdapter(requireContext(), noteList)
                    rcViewNotes.adapter = notesAdapter

                }
            }

//            for medium priority notes
            filterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { noteList ->
                    filterNoteList = noteList as ArrayList<NoteModel>
                    notesAdapter = NoteRecyclerAdapter(requireContext(), noteList)
                    rcViewNotes.adapter = notesAdapter

                }
            }
//            for low priority notes
            filterLow.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { noteList ->
                    filterNoteList = noteList as ArrayList<NoteModel>
                    notesAdapter = NoteRecyclerAdapter(requireContext(), noteList)
                    rcViewNotes.adapter = notesAdapter

                }
            }

//            for all priority notes
            clearFilter.setOnClickListener {
                viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
                    filterNoteList = noteList as ArrayList<NoteModel>
                    notesAdapter = NoteRecyclerAdapter(requireContext(), noteList)
                    rcViewNotes.adapter = notesAdapter

                }
            }

            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    noteFilter(p0)
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })


        }

        return binding.root
    }

    private fun noteFilter(p0: CharSequence?) {
        Log.d("@@@@", "noteFilter: ${p0}")
        val newFilterList = arrayListOf<NoteModel>()
        for (i in filterNoteList) {
            if (i.title.contains(p0!!) || i.subtitle.contains(p0) || i.note.contains(p0)) {
                newFilterList.add(i)
            }
        }
        notesAdapter.filtering(newFilterList)
    }
}