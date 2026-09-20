package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;


public class LoginFormDialog {

    public interface OnLoginListener {
        void onLogin(String email, String password, boolean rememberMe);
        void onForgotPassword();
    }

    public static void show(Context context, final OnLoginListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_login_form, null);

        final TextInputEditText etEmail = view.findViewById(R.id.et_login_email);
        final TextInputEditText etPassword = view.findViewById(R.id.et_login_password);
        final CheckBox cbRememberMe = view.findViewById(R.id.cb_remember_me);
        TextView tvForgotPassword = view.findViewById(R.id.tv_forgot_password);
        MaterialButton btnCancel = view.findViewById(R.id.btn_login_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_login_submit);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onForgotPassword();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
                String password = etPassword.getText() != null ? etPassword.getText().toString().trim() : "";

                if (email.isEmpty()) {
                    etEmail.setError("Email is required");
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError("Password is required");
                    return;
                }

                dialog.dismiss();
                if (listener != null) {
                    listener.onLogin(email, password, cbRememberMe.isChecked());
                }
            }
        });

        dialog.show();
    }
}
