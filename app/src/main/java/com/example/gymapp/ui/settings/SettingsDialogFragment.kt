package com.example.gymapp.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.gymapp.R

class SettingsDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.edit_training_max)
//                .setPositiveButton(R.string.start,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // START THE GAME!
//                    })
//                .setNegativeButton(R.string.cancel,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // User cancelled the dialog
//                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "SettingsDialogFragment"
    }
}