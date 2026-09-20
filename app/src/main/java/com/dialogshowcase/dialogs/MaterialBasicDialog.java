package com.dialogshowcase.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MaterialBasicDialog {

    public interface OnDialogActionListener {
        void onPositive();
        void onNegative();
    }

    public static void show(Context context, String title, String message, final OnDialogActionListener listener) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title != null ? title : "Alert Dialog")
                .setMessage(message != null ? message : "This is a standard Material 3 dialog with clean typography.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.onPositive();
                    }
                })
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.onNegative();
                    }
                })
                .show();
    }
}
