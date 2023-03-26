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
            squatMax = binding.editSquatValue.text.toString().toFloat(),
            benchMax = binding.editBenchValue.text.toString().toFloat(),
            deadliftMax = binding.editDeadliftValue.text.toString().toFloat(),
            ohpMax = binding.editOhpValue.text.toString().toFloat(),
        )

        settingsViewModel.onSaveMaxes(trainingMaxes)
    }

    private fun onAutoIncrementMaxes() {
        val increment = 2.5f

        settingsViewModel.editSquatMax.value = settingsViewModel.editSquatMax.value?.plus(increment)
        settingsViewModel.editBenchMax.value = settingsViewModel.editBenchMax.value?.plus(increment)
        settingsViewModel.editDeadliftMax.value = settingsViewModel.editDeadliftMax.value?.plus(increment)
        settingsViewModel.editOhpMax.value = settingsViewModel.editOhpMax.value?.plus(increment)
    }

    companion object {
        const val TAG = "SettingsDialogFragment"
    }
}