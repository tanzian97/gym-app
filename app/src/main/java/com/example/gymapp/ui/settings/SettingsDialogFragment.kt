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

    private val settingsViewModel: SettingsViewModel by viewModels(ownerProducer = { requireParentFragment() })

    private var _binding: FragmentSettingsDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsDialogBinding.inflate(inflater, container, false)

        settingsViewModel.editSquatMax.value = settingsViewModel.squatMax.value
        settingsViewModel.editBenchMax.value = settingsViewModel.benchMax.value
        settingsViewModel.editDeadliftMax.value = settingsViewModel.deadliftMax.value
        settingsViewModel.editOhpMax.value = settingsViewModel.ohpMax.value

        settingsViewModel.editSquatMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editSquatValue.setText(it.toString())
            }
        }

        settingsViewModel.editBenchMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editBenchValue.setText(it.toString())
            }
        }

        settingsViewModel.editDeadliftMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editDeadliftValue.setText(it.toString())
            }
        }

        settingsViewModel.editOhpMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editOhpValue.setText(it.toString())
            }
        }

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
            squatMax = binding.editSquatValue.text.toString().toFloatOrNull() ?: 0f,
            benchMax = binding.editBenchValue.text.toString().toFloatOrNull() ?: 0f,
            deadliftMax = binding.editDeadliftValue.text.toString().toFloatOrNull() ?: 0f,
            ohpMax = binding.editOhpValue.text.toString().toFloatOrNull() ?: 0f,
        )

        settingsViewModel.onSaveMaxes(trainingMaxes)
    }

    private fun onAutoIncrementMaxes() {
        val upperBodyIncrement = 2.5f
        val lowerBodyIncrement = 5f

        settingsViewModel.editSquatMax.value = settingsViewModel.editSquatMax.value?.plus(lowerBodyIncrement)
        settingsViewModel.editBenchMax.value = settingsViewModel.editBenchMax.value?.plus(upperBodyIncrement)
        settingsViewModel.editDeadliftMax.value = settingsViewModel.editDeadliftMax.value?.plus(lowerBodyIncrement)
        settingsViewModel.editOhpMax.value = settingsViewModel.editOhpMax.value?.plus(upperBodyIncrement)
    }

    companion object {
        const val TAG = "SettingsDialogFragment"
    }
}