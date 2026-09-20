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


public class TermsOfServiceDialog {

    public interface OnTermsDecisionListener {
        void onAccept();
        void onDecline();
    }

    public static void show(Context context, final OnTermsDecisionListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_terms, null);

        MaterialButton btnDecline = view.findViewById(R.id.dialog_btn_decline);
        MaterialButton btnAccept = view.findViewById(R.id.dialog_btn_accept);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onDecline();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onAccept();
            }
        });

        dialog.show();
    }
}
