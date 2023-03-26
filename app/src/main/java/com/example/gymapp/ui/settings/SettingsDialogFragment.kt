package com.example.gymapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.databinding.FragmentSettingsDialogBinding


class SettingsDialogFragment : DialogFragment() {

    private val settingsViewModel: SettingsViewModel by viewModels(ownerProducer = { requireParentFragment()} )

    private var _binding: FragmentSettingsDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsDialogBinding.inflate(inflater, container, false)

        if (arguments != null) {
            settingsViewModel.squatMax.value = arguments?.getFloat("squatMax") ?: 0f
            settingsViewModel.benchMax.value = arguments?.getFloat("benchMax") ?: 0f
            settingsViewModel.deadliftMax.value = arguments?.getFloat("deadliftMax") ?: 0f
            settingsViewModel.ohpMax.value = arguments?.getFloat("ohpMax") ?: 0f
        }

        binding.editSquatValue.hint = settingsViewModel.squatMax.value.toString()
        binding.editBenchValue.hint = settingsViewModel.benchMax.value.toString()
        binding.editDeadliftValue.hint = settingsViewModel.deadliftMax.value.toString()
        binding.editOhpValue.hint = settingsViewModel.ohpMax.value.toString()

        binding.saveMaxesButton.setOnClickListener{
            onSaveMaxes()
            dismiss()
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

        settingsViewModel.onSaveMaxes(trainingMaxes)
    }

    private fun onAutoIncrementMaxes() {
        settingsViewModel.onAutoIncrementMaxes()
        binding.editSquatValue.setText(settingsViewModel.squatMax.value.toString())
        binding.editBenchValue.setText(settingsViewModel.benchMax.value.toString())
        binding.editDeadliftValue.setText(settingsViewModel.deadliftMax.value.toString())
        binding.editOhpValue.setText(settingsViewModel.ohpMax.value.toString())
    }

    companion object {
        const val TAG = "SettingsDialogFragment"
    }
}