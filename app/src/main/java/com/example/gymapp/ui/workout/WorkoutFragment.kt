package com.example.gymapp.ui.workout

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.gymapp.database.TrainingMaxDatabase
import com.example.gymapp.databinding.FragmentWorkoutBinding


class WorkoutFragment: Fragment(){

    private var _binding: FragmentWorkoutBinding? = null

    private val binding get() = _binding!!

    private var amrapRepCount: Int = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val application = requireNotNull(this.activity).application

        val dataSource = TrainingMaxDatabase.getInstance(application).trainingMaxDatabaseDao

        val args: WorkoutFragmentArgs by navArgs()

        val viewModelFactory = WorkoutViewModelFactory(dataSource, args.workoutType, args.weekCount)

        val workoutViewModel = ViewModelProvider(this, viewModelFactory)[WorkoutViewModel::class.java]

        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        var adapter = WorkoutAdapter(emptyList(), args.weekCount)
        binding.setList.adapter = adapter

        workoutViewModel.setList.observe(viewLifecycleOwner) {
            adapter = workoutViewModel.setList.value?.let { setList ->
                WorkoutAdapter(setList, args.weekCount)
            }!!
            binding.setList.adapter = adapter
        }

        val recordAmrapButton: View = binding.recordAmrapButton
        recordAmrapButton.setOnClickListener {
            val numberPicker = NumberPicker(requireActivity())
            numberPicker.minValue = 0
            numberPicker.maxValue = 30
            numberPicker.value = 5
            numberPicker.wrapSelectorWheel = true

            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Reps done for AMRAP set")
                .setView(numberPicker)
                .setPositiveButton("OK") { _, _ ->
                    amrapRepCount = numberPicker.value
                }
                .setNegativeButton("Cancel", null)
                .create()

            dialog.show()
        }

//        val finishWorkoutButton: View = binding.finishWorkoutButton
//        finishWorkoutButton.setOnClickListener {
//
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}