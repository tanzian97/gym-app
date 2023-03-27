package com.example.gymapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.R
import com.example.gymapp.database.TrainingMaxDatabase
import com.example.gymapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val application = requireNotNull(this.activity).application

        val dataSource = TrainingMaxDatabase.getInstance(application).trainingMaxDatabaseDao

        val viewModelFactory = SettingsViewModelFactory(dataSource)

        val settingsViewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        settingsViewModel.squatMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.squatMaxValue.text = getString(R.string.training_max_weight, it)
            }
        }

        settingsViewModel.benchMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.benchMaxValue.text = getString(R.string.training_max_weight, it)
            }
        }

        settingsViewModel.deadliftMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.deadliftMaxValue.text = getString(R.string.training_max_weight, it)
            }
        }

        settingsViewModel.ohpMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.ohpMaxValue.text = getString(R.string.training_max_weight, it)
            }
        }

        val fab: View = binding.fab
        fab.setOnClickListener {
            val args = Bundle()
            settingsViewModel.squatMax.value?.let { args.putFloat("squatMax", it) }
            settingsViewModel.benchMax.value?.let { args.putFloat("benchMax", it) }
            settingsViewModel.deadliftMax.value?.let { args.putFloat("deadliftMax", it) }
            settingsViewModel.ohpMax.value?.let { args.putFloat("ohpMax", it) }

            SettingsDialogFragment()
                .show(childFragmentManager, SettingsDialogFragment.TAG)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}