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

public class PopupZoomDialog {

    public interface OnDismissListener {
        void onDismiss();
    }

    public static void show(Context context, final OnDismissListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_anim_popup, null);

        MaterialButton btnClose = view.findViewById(R.id.btn_popup_close);
        MaterialButton btnConfirm = view.findViewById(R.id.btn_popup_confirm);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation_Popup);
        }

        View.OnClickListener clickDismiss = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onDismiss();
            }
        };

        btnClose.setOnClickListener(clickDismiss);
        btnConfirm.setOnClickListener(clickDismiss);

        dialog.show();
    }
}
