package com.dkgtech.notesappdarkmode.ui.fragments

import android.os.Bundle
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
import com.dkgtech.notesappdarkmode.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        with(binding) {

            viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
                rcViewNotes.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                rcViewNotes.adapter = NoteRecyclerAdapter(requireContext(), noteList)

            }

            btnFab.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_createNotesFragment)
            }

            filterHigh.setOnClickListener {
                viewModel.getHighNotes().observe(viewLifecycleOwner) { noteList ->
                    rcViewNotes.adapter = NoteRecyclerAdapter(requireContext(), noteList)

                }
            }

            filterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { noteList ->
                    rcViewNotes.adapter = NoteRecyclerAdapter(requireContext(), noteList)

                }
            }

            filterLow.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { noteList ->
                    rcViewNotes.adapter = NoteRecyclerAdapter(requireContext(), noteList)

                }
            }
            clearFilter.setOnClickListener {
                viewModel.getAllNotes().observe(viewLifecycleOwner) { noteList ->
                    rcViewNotes.adapter = NoteRecyclerAdapter(requireContext(), noteList)

                }
            }

        }

        return binding.root
    }
}