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
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;


public class InputPromptDialog {

    public interface OnInputSubmittedListener {
        void onSubmit(String input);
    }

    public static void show(Context context, String title, String defaultText, final OnInputSubmittedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_prompt, null);

        TextView tvTitle = view.findViewById(R.id.dialog_title);
        final TextInputEditText editText = view.findViewById(R.id.dialog_input_edit_text);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);
        MaterialButton btnSave = view.findViewById(R.id.dialog_btn_save);

        if (title != null) tvTitle.setText(title);
        if (defaultText != null) editText.setText(defaultText);

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText() != null ? editText.getText().toString().trim() : "";
                if (input.isEmpty()) {
                    editText.setError("Input cannot be empty");
                    return;
                }
                dialog.dismiss();
                if (listener != null) {
                    listener.onSubmit(input);
                }
            }
        });

        dialog.show();
    }
}
