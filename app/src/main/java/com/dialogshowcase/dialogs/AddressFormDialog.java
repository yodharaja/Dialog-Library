package com.dialogshowcase.dialogs; //Your Package

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R; //Your Package


public class AddressFormDialog {

    public interface OnAddressSavedListener {
        void onAddressSaved(String street, String city, String state, String zip, String country);
    }

    public static void show(Context context, final OnAddressSavedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_address_form, null);

        final TextInputEditText etStreet = view.findViewById(R.id.et_address_street);
        final TextInputEditText etCity = view.findViewById(R.id.et_address_city);
        final TextInputEditText etState = view.findViewById(R.id.et_address_state);
        final TextInputEditText etZip = view.findViewById(R.id.et_address_zip);
        final TextInputEditText etCountry = view.findViewById(R.id.et_address_country);
        MaterialButton btnCancel = view.findViewById(R.id.btn_address_cancel);
        MaterialButton btnSave = view.findViewById(R.id.btn_address_save);

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
                String street = etStreet.getText() != null ? etStreet.getText().toString().trim() : "";
                String city = etCity.getText() != null ? etCity.getText().toString().trim() : "";
                String state = etState.getText() != null ? etState.getText().toString().trim() : "";
                String zip = etZip.getText() != null ? etZip.getText().toString().trim() : "";
                String country = etCountry.getText() != null ? etCountry.getText().toString().trim() : "";

                if (street.isEmpty()) {
                    etStreet.setError("Street is required");
                    return;
                }
                if (city.isEmpty()) {
                    etCity.setError("City is required");
                    return;
                }

                dialog.dismiss();
                if (listener != null) {
                    listener.onAddressSaved(street, city, state, zip, country);
                }
            }
        });

        dialog.show();
    }
}
