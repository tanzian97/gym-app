package com.example.gymapp.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.databinding.FragmentWorkoutBinding

class WorkoutFragment: Fragment(){

    private var _binding: FragmentWorkoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        val adapter = WorkoutAdapter()
        binding.setList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}