package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;

public class RegisterFormDialog {

    public interface OnRegisterListener {
        void onRegister(String name, String email, String password);
    }

    public static void show(Context context, final OnRegisterListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_register_form, null);

        final TextInputEditText etName = view.findViewById(R.id.et_register_name);
        final TextInputEditText etEmail = view.findViewById(R.id.et_register_email);
        final TextInputEditText etPassword = view.findViewById(R.id.et_register_password);
        final CheckBox cbTerms = view.findViewById(R.id.cb_register_terms);
        MaterialButton btnCancel = view.findViewById(R.id.btn_register_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_register_submit);

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
                String name = etName.getText() != null ? etName.getText().toString().trim() : "";
                String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
                String password = etPassword.getText() != null ? etPassword.getText().toString().trim() : "";

                if (name.isEmpty()) {
                    etName.setError("Name is required");
                    return;
                }
                if (email.isEmpty()) {
                    etEmail.setError("Email is required");
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("Password must be at least 6 characters");
                    etPassword.requestFocus();
                    return;
                }
                if (!password.matches(".*[a-z].*")) {
                    etPassword.setError("Password must contain at least 1 lowercase letter (a-z)");
                    etPassword.requestFocus();
                    return;
                }
                if (!password.matches(".*\\d.*")) {
                    etPassword.setError("Password must contain at least 1 number (0-9)");
                    etPassword.requestFocus();
                    return;
                }
                if (!cbTerms.isChecked()) {
                    cbTerms.setError("Please agree to the terms");
                    return;
                }

                dialog.dismiss();
                if (listener != null) {
                    listener.onRegister(name, email, password);
                }
            }
        });

        dialog.show();
    }
}
