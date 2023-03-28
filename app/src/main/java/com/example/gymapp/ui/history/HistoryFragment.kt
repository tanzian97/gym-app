package com.example.gymapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.gymapp.database.SessionDatabase
import com.example.gymapp.database.SetDatabase
import com.example.gymapp.database.TrainingMaxDatabase
import com.example.gymapp.databinding.FragmentHistoryBinding
import com.example.gymapp.ui.workout.WorkoutAdapter
import com.example.gymapp.ui.workout.WorkoutFragmentArgs
import com.example.gymapp.ui.workout.WorkoutViewModelFactory

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application

        val setDataSource = SetDatabase.getInstance(application).setDatabaseDao

        val viewModelFactory = HistoryViewModelFactory(setDataSource)

        val historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        var adapter = HistoryAdapter(emptyList())

//        historyViewModel.setList.observe(viewLifecycleOwner) {
//            adapter = workoutViewModel.setList.value?.let { setList ->
//                HistoryAdapter(setList, args.weekCount)
//            }!!
//            binding.setList.adapter = adapter
//        }

        binding.sessionHistoryList.adapter = adapter

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}