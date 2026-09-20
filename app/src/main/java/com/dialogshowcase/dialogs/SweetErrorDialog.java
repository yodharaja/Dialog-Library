package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R;

public class SweetErrorDialog {

    public interface OnRetryListener {
        void onRetry();
    }

    public static void show(Context context, String title, String message, final OnRetryListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sweet_error, null);

        TextView tvTitle = view.findViewById(R.id.dialog_title);
        TextView tvMessage = view.findViewById(R.id.dialog_message);
        MaterialButton btnConfirm = view.findViewById(R.id.dialog_btn_confirm);

        if (title != null) tvTitle.setText(title);
        if (message != null) tvMessage.setText(message);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onRetry();
                }
            }
        });

        dialog.show();
    }
}
