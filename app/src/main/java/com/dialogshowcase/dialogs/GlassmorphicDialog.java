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


public class GlassmorphicDialog {

    public interface OnExploreListener {
        void onExplore();
    }

    public static void show(Context context, final OnExploreListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_glassmorphic, null);

        MaterialButton btnExplore = view.findViewById(R.id.dialog_btn_explore);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onExplore();
            }
        });

        dialog.show();
    }
}
