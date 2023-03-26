package com.example.gymapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.TrainingMaxDatabase
import com.example.gymapp.databinding.FragmentSettingsBinding
import com.example.gymapp.ui.workout.WorkoutViewModelFactory

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

        binding.squatMaxValue.text = settingsViewModel.squatMax.toString()
        binding.benchMaxValue.text = settingsViewModel.benchMax.toString()
        binding.deadliftMaxValue.text = settingsViewModel.deadliftMax.toString()
        binding.ohpMaxValue.text = settingsViewModel.ohpMax.toString()

        val fab: View = binding.fab
        fab.setOnClickListener { view ->
            val args = Bundle()
            args.putFloat("squatMax", settingsViewModel.squatMax)
            args.putFloat("benchMax", settingsViewModel.benchMax)
            args.putFloat("deadliftMax", settingsViewModel.deadliftMax)
            args.putFloat("ohpMax", settingsViewModel.ohpMax)

            SettingsDialogFragment()
                .apply { arguments = args }
                .show(childFragmentManager, SettingsDialogFragment.TAG)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}