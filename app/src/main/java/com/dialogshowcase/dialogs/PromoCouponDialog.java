package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;


public class PromoCouponDialog {

    public interface OnPromoAppliedListener {
        void onApplied(String couponCode, int discountPercent);
    }

    public static void show(Context context, final OnPromoAppliedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_promo_coupon, null);

        final TextInputEditText etPromoCode = view.findViewById(R.id.et_promo_code);
        MaterialButton btnApply = view.findViewById(R.id.btn_promo_apply);
        final LinearLayout layoutFeedback = view.findViewById(R.id.layout_promo_feedback);
        final TextView tvFeedback = view.findViewById(R.id.tv_promo_feedback);
        MaterialButton btnCancel = view.findViewById(R.id.btn_promo_cancel);
        MaterialButton btnRedeem = view.findViewById(R.id.btn_promo_redeem);

        final int[] appliedDiscount = {0};

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etPromoCode.getText() != null ? etPromoCode.getText().toString().trim().toUpperCase() : "";
                if (code.isEmpty()) {
                    etPromoCode.setError("Enter a promo code");
                    return;
                }

                // Simulate validation
                appliedDiscount[0] = 20;
                layoutFeedback.setVisibility(View.VISIBLE);
                tvFeedback.setText("🎉 Code " + code + " applied! 20% discount unlocked.");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etPromoCode.getText() != null ? etPromoCode.getText().toString().trim().toUpperCase() : "";
                if (code.isEmpty()) {
                    etPromoCode.setError("Please enter a code");
                    return;
                }
                if (appliedDiscount[0] == 0) {
                    appliedDiscount[0] = 10; // Default discount if not tested
                }

                dialog.dismiss();
                if (listener != null) {
                    listener.onApplied(code, appliedDiscount[0]);
                }
            }
        });

        dialog.show();
    }
}
