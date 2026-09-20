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

public class NumberStepperDialog {

    public interface OnQuantitySelectedListener {
        void onQuantitySelected(int quantity);
    }

    public static void show(Context context, int initialQuantity, final int min, final int max, final OnQuantitySelectedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_number_stepper, null);

        final TextView tvQuantity = view.findViewById(R.id.tv_quantity);
        MaterialButton btnMinus = view.findViewById(R.id.btn_minus);
        MaterialButton btnPlus = view.findViewById(R.id.btn_plus);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnConfirm = view.findViewById(R.id.dialog_btn_confirm);

        final int[] count = {Math.max(min, Math.min(max, initialQuantity))};
        tvQuantity.setText(String.valueOf(count[0]));

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] > min) {
                    count[0]--;
                    tvQuantity.setText(String.valueOf(count[0]));
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] < max) {
                    count[0]++;
                    tvQuantity.setText(String.valueOf(count[0]));
                }
            }
        });

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
                dialog.dismiss();
                if (listener != null) {
                    listener.onQuantitySelected(count[0]);
                }
            }
        });

        dialog.show();
    }
}
