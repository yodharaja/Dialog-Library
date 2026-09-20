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
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;

public class ContactSupportDialog {

    public interface OnSupportTicketSubmittedListener {
        void onSubmit(String subject, String category, String description);
    }

    public static void show(Context context, final OnSupportTicketSubmittedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_contact_support, null);

        final TextInputEditText etSubject = view.findViewById(R.id.et_support_subject);
        final RadioGroup rgCategory = view.findViewById(R.id.rg_support_category);
        final TextInputEditText etDesc = view.findViewById(R.id.et_support_desc);
        MaterialButton btnCancel = view.findViewById(R.id.btn_support_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_support_submit);

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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = etSubject.getText() != null ? etSubject.getText().toString().trim() : "";
                String desc = etDesc.getText() != null ? etDesc.getText().toString().trim() : "";

                if (subject.isEmpty()) {
                    etSubject.setError("Subject is required");
                    return;
                }
                if (desc.isEmpty()) {
                    etDesc.setError("Please describe the issue");
                    return;
                }

                int checkedId = rgCategory.getCheckedRadioButtonId();
                String category = "Bug";
                if (checkedId != -1) {
                    RadioButton rb = view.findViewById(checkedId);
                    if (rb != null) {
                        category = rb.getText().toString();
                    }
                }

                dialog.dismiss();
                if (listener != null) {
                    listener.onSubmit(subject, category, desc);
                }
            }
        });

        dialog.show();
    }
}
