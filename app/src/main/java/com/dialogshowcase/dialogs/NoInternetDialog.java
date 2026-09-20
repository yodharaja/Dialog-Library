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

public class NoInternetDialog {

    public interface OnRetryListener {
        void onRetry();
    }

    public static void show(Context context, final OnRetryListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_no_internet, null);

        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnRetry = view.findViewById(R.id.dialog_btn_retry);

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
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onRetry();
            }
        });

        dialog.show();
    }
}
