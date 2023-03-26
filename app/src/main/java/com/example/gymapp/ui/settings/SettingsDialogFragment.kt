package com.example.gymapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.databinding.FragmentSettingsDialogBinding


class SettingsDialogFragment : DialogFragment() {

    val settingsDialogViewModel = ViewModelProvider(this)[SettingsDialogViewModel::class.java]

    private var _binding: FragmentSettingsDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsDialogBinding.inflate(inflater, container, false)

        if (arguments != null) {
            settingsDialogViewModel.squatMax = arguments?.getFloat("squatMax") ?: 0f
            settingsDialogViewModel.benchMax = arguments?.getFloat("benchMax") ?: 0f
            settingsDialogViewModel.deadliftMax = arguments?.getFloat("deadliftMax") ?: 0f
            settingsDialogViewModel.ohpMax = arguments?.getFloat("ohpMax") ?: 0f
        }

        binding.editSquatValue.hint = settingsDialogViewModel.squatMax.toString()
        binding.editBenchValue.hint = settingsDialogViewModel.benchMax.toString()
        binding.editDeadliftValue.hint = settingsDialogViewModel.deadliftMax.toString()
        binding.editOhpValue.hint = settingsDialogViewModel.ohpMax.toString()

        binding.saveMaxesButton.setOnClickListener{
            onSaveMaxes()
        }

        binding.autoIncrementButton.setOnClickListener{
            onAutoIncrementMaxes()
        }

        return binding.root
    }

    private fun onSaveMaxes() {
        val trainingMaxes = TrainingMax(
            squatMax = binding.editSquatValue.text.toString().toFloat(),
            benchMax = binding.editBenchValue.text.toString().toFloat(),
            deadliftMax = binding.editDeadliftValue.text.toString().toFloat(),
            ohpMax = binding.editOhpValue.text.toString().toFloat(),
        )

        settingsDialogViewModel.onSaveMaxes(trainingMaxes)
    }

    private fun onAutoIncrementMaxes() {
        settingsDialogViewModel.onAutoIncrementMaxes()
    }

    companion object {
        const val TAG = "SettingsDialogFragment"
    }
}