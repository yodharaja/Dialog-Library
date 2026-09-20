package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R;

public class LanguageSelectDialog {

    public interface OnLanguageSelectedListener {
        void onSelected(String languageCode, String languageName);
    }

    public static void show(Context context, final OnLanguageSelectedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_language_select, null);

        final RadioGroup radioGroup = view.findViewById(R.id.language_radio_group);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnApply = view.findViewById(R.id.dialog_btn_apply);

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

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    String code = "en";
                    String name = "English";

                    if (checkedId == R.id.radio_es) {
                        code = "es";
                        name = "Español";
                    } else if (checkedId == R.id.radio_fr) {
                        code = "fr";
                        name = "Français";
                    } else if (checkedId == R.id.radio_hi) {
                        code = "hi";
                        name = "हिन्दी";
                    }

                    listener.onSelected(code, name);
                }
            }
        });

        dialog.show();
    }
}
