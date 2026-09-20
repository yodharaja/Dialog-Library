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

public class ChangePasswordDialog {

    public interface OnPasswordChangedListener {
        void onPasswordChanged(String oldPassword, String newPassword);
    }

    public static void show(Context context, final OnPasswordChangedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_change_password, null);

        final TextInputEditText etOldPassword = view.findViewById(R.id.et_old_password);
        final TextInputEditText etNewPassword = view.findViewById(R.id.et_new_password);
        final TextInputEditText etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        MaterialButton btnCancel = view.findViewById(R.id.btn_change_pw_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_change_pw_submit);

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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPw = etOldPassword.getText() != null ? etOldPassword.getText().toString().trim() : "";
                String newPw = etNewPassword.getText() != null ? etNewPassword.getText().toString().trim() : "";
                String confirmPw = etConfirmPassword.getText() != null ? etConfirmPassword.getText().toString().trim() : "";

                if (oldPw.isEmpty()) {
                    etOldPassword.setError("Enter current password");
                    return;
                }
                if (newPw.length() < 6) {
                    etNewPassword.setError("New password must be at least 6 characters");
                    etNewPassword.requestFocus();
                    return;
                }
                if (!newPw.matches(".*[a-z].*")) {
                    etNewPassword.setError("Must contain at least 1 lowercase letter (a-z)");
                    etNewPassword.requestFocus();
                    return;
                }
                if (!newPw.matches(".*\\d.*")) {
                    etNewPassword.setError("Must contain at least 1 number (0-9)");
                    etNewPassword.requestFocus();
                    return;
                }
                if (!newPw.equals(confirmPw)) {
                    etConfirmPassword.setError("Passwords do not match");
                    etConfirmPassword.requestFocus();
                    return;
                }

                dialog.dismiss();
                if (listener != null) {
                    listener.onPasswordChanged(oldPw, newPw);
                }
            }
        });

        dialog.show();
    }
}
