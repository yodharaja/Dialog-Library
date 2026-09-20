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


public class BounceSpringDialog {

    public interface OnDismissListener {
        void onDismiss();
    }

    public static void show(Context context, final OnDismissListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_anim_bounce, null);

        MaterialButton btnDismiss = view.findViewById(R.id.btn_bounce_dismiss);
        MaterialButton btnConfirm = view.findViewById(R.id.btn_bounce_confirm);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation_Bounce);
        }

        View.OnClickListener clickDismiss = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onDismiss();
            }
        };

        btnDismiss.setOnClickListener(clickDismiss);
        btnConfirm.setOnClickListener(clickDismiss);

        dialog.show();
    }
}
