package com.dialogshowcase.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MaterialDestructiveDialog {

    public interface OnDestructiveActionListener {
        void onConfirmDestructive();
        void onCancel();
    }

    public static void show(Context context, String title, String message, final OnDestructiveActionListener listener) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title != null ? title : "Discard Changes?")
                .setMessage(message != null ? message : "All modifications made in this session will be permanently deleted.")
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.onConfirmDestructive();
                    }
                })
                .setNegativeButton("Keep Editing", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.onCancel();
                    }
                })
                .show();
    }
}
