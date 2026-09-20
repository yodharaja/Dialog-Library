package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;


public class EmailSubscribeDialog {

    public interface OnSubscribedListener {
        void onSubscribed(String email);
    }

    public static void show(Context context, final OnSubscribedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_email_subscribe, null);

        final TextInputEditText editEmail = view.findViewById(R.id.dialog_email_edit_text);
        MaterialButton btnSubscribe = view.findViewById(R.id.dialog_btn_subscribe);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText() != null ? editEmail.getText().toString().trim() : "";
                if (email.isEmpty() || !email.contains("@")) {
                    editEmail.setError("Please enter a valid email address");
                    return;
                }
                dialog.dismiss();
                if (listener != null) {
                    listener.onSubscribed(email);
                }
            }
        });

        dialog.show();
    }
}
