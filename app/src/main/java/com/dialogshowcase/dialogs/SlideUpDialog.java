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


public class SlideUpDialog {

    public interface OnActionListener {
        void onConfirm();
        void onCancel();
    }

    public static void show(Context context, final OnActionListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_anim_slide_up, null);

        MaterialButton btnDismiss = view.findViewById(R.id.btn_slide_dismiss);
        MaterialButton btnConfirm = view.findViewById(R.id.btn_slide_confirm);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation_SlideUp);
        }

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onCancel();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onConfirm();
            }
        });

        dialog.show();
    }
}
