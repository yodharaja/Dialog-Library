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

public class PinSecurityDialog {

    public interface OnPinEnteredListener {
        void onPinEntered(String pin);
    }

    public static void show(Context context, final OnPinEnteredListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pin_security, null);

        final TextInputEditText editPin = view.findViewById(R.id.dialog_pin_edit_text);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnConfirm = view.findViewById(R.id.dialog_btn_confirm);

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

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = editPin.getText() != null ? editPin.getText().toString().trim() : "";
                if (pin.length() < 4) {
                    editPin.setError("Enter 4 digits");
                    return;
                }
                dialog.dismiss();
                if (listener != null) {
                    listener.onPinEntered(pin);
                }
            }
        });

        dialog.show();
    }
}
