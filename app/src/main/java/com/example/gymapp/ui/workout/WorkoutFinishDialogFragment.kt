package com.example.gymapp.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.gymapp.databinding.FragmentWorkoutFinishDialogBinding

class WorkoutFinishDialogFragment(private val amrapRepCount: Int) : DialogFragment() {

    private val workoutViewModel: WorkoutViewModel by viewModels(ownerProducer = { requireParentFragment() } )

    private var _binding: FragmentWorkoutFinishDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWorkoutFinishDialogBinding.inflate(inflater, container, false)

        binding.thirdMainSetReps.setText(amrapRepCount)

        binding.finishButton.setOnClickListener{
//            TODO: Upsert into session and set DBs
            dismiss()
        }

        return binding.root
    }


    companion object {
        const val TAG = "WorkoutFinishDialogFragment"
    }
}