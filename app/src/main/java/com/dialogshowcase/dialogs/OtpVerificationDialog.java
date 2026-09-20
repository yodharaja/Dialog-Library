package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R;

public class OtpVerificationDialog {

    public interface OnOtpVerifiedListener {
        void onVerified(String otpCode);
    }

    public static void show(final Context context, final OnOtpVerifiedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_otp_verification, null);

        final EditText digit1 = view.findViewById(R.id.otp_digit_1);
        final EditText digit2 = view.findViewById(R.id.otp_digit_2);
        final EditText digit3 = view.findViewById(R.id.otp_digit_3);
        final EditText digit4 = view.findViewById(R.id.otp_digit_4);
        TextView tvResend = view.findViewById(R.id.tv_resend_otp);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnVerify = view.findViewById(R.id.dialog_btn_verify);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Auto-advance focus on typing
        setupOtpInput(digit1, digit2, null);
        setupOtpInput(digit2, digit3, digit1);
        setupOtpInput(digit3, digit4, digit2);
        setupOtpInput(digit4, null, digit3);

        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "New OTP sent to your phone!", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d1 = digit1.getText().toString().trim();
                String d2 = digit2.getText().toString().trim();
                String d3 = digit3.getText().toString().trim();
                String d4 = digit4.getText().toString().trim();

                if (d1.isEmpty() || d2.isEmpty() || d3.isEmpty() || d4.isEmpty()) {
                    Toast.makeText(context, "Please enter all 4 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                String fullOtp = d1 + d2 + d3 + d4;
                dialog.dismiss();
                if (listener != null) {
                    listener.onVerified(fullOtp);
                }
            }
        });

        dialog.show();
        digit1.requestFocus();
    }

    private static void setupOtpInput(final EditText current, final EditText next, final EditText prev) {
        current.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1 && next != null) {
                    next.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        current.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (current.getText().toString().isEmpty() && prev != null) {
                        prev.requestFocus();
                        prev.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
