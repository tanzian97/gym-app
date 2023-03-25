package com.example.gymapp.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.gymapp.R
import com.example.gymapp.databinding.FragmentSettingsDialogBinding


class SettingsDialogFragment : DialogFragment() {

    private var _binding: FragmentSettingsDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        const val TAG = "SettingsDialogFragment"
    }
}