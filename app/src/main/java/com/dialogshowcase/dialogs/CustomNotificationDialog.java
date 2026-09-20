package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R;

public class CustomNotificationDialog {

    public interface OnNotificationToggleListener {
        void onEnable();
        void onSkip();
    }

    public static void show(Context context, final OnNotificationToggleListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom_notification, null);

        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnEnable = view.findViewById(R.id.dialog_btn_enable);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onSkip();
            }
        });

        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onEnable();
            }
        });

        dialog.show();
    }
}
