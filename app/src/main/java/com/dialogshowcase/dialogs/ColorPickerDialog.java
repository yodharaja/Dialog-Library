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

public class ColorPickerDialog {

    public interface OnColorSelectedListener {
        void onColorSelected(String hexColor, int colorInt);
    }

    public static void show(Context context, final OnColorSelectedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_color_picker, null);

        final TextView tvHex = view.findViewById(R.id.tv_selected_hex);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnSelect = view.findViewById(R.id.dialog_btn_select);

        final String[] selectedHex = {"#4F46E5"};

        final View[] colorViews = new View[]{
                view.findViewById(R.id.color_1),
                view.findViewById(R.id.color_2),
                view.findViewById(R.id.color_3),
                view.findViewById(R.id.color_4),
                view.findViewById(R.id.color_5),
                view.findViewById(R.id.color_6),
                view.findViewById(R.id.color_7),
                view.findViewById(R.id.color_8)
        };

        final String[] hexCodes = new String[]{
                "#4F46E5", "#10B981", "#F59E0B", "#EF4444",
                "#0284C7", "#8B5CF6", "#EC4899", "#0F172A"
        };

        for (int i = 0; i < colorViews.length; i++) {
            final String hex = hexCodes[i];
            colorViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedHex[0] = hex;
                    tvHex.setText("Selected: " + hex);
                }
            });
        }

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

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onColorSelected(selectedHex[0], Color.parseColor(selectedHex[0]));
                }
            }
        });

        dialog.show();
    }
}
