package com.example.gymapp.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentWorkoutFinishDialogBinding

class WorkoutFinishDialogFragment(
    private val amrapRepCount: Int,
) : DialogFragment() {

    private val workoutViewModel: WorkoutViewModel by viewModels(ownerProducer = { requireParentFragment() } )

    private var _binding: FragmentWorkoutFinishDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWorkoutFinishDialogBinding.inflate(inflater, container, false)

        val mainSetWeights = workoutViewModel.getMainSetWeights()
        binding.firstMainSetWeight.text = getString(R.string.weight_format_text, mainSetWeights[0])
        binding.secondMainSetWeight.text = getString(R.string.weight_format_text, mainSetWeights[1])
        binding.thirdMainSetWeight.text = getString(R.string.weight_format_text, mainSetWeights[2])

        val mainSetReps = workoutViewModel.getMainSetReps()
        binding.firstMainSetReps.setText(mainSetReps[0].toString())
        binding.secondMainSetReps.setText(mainSetReps[1].toString())

        if (amrapRepCount != 0) {
            binding.thirdMainSetReps.setText(amrapRepCount.toString())
        } else {
            binding.thirdMainSetReps.setText(mainSetReps[2].toString())
        }

        binding.submitButton.setOnClickListener{
//            TODO: Upsert into session and set DBs
            dismiss()
        }

        return binding.root
    }


    companion object {
        const val TAG = "WorkoutFinishDialogFragment"
    }
}