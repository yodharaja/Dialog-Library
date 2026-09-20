package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.dialogshowcase.R;

public class PaymentMethodBottomSheetDialog {

    public interface OnPaymentConfirmedListener {
        void onPaymentConfirmed(String method, String amount);
    }

    public static void show(Context context, final OnPaymentConfirmedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_payment, null);

        final RadioButton rbCard = view.findViewById(R.id.rb_payment_card);
        final RadioButton rbGPay = view.findViewById(R.id.rb_payment_gpay);
        MaterialButton btnPayNow = view.findViewById(R.id.btn_pay_now);

        final String[] chosenMethod = {"Credit Card (•••• 4242)"};

        view.findViewById(R.id.payment_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbCard.setChecked(true);
                rbGPay.setChecked(false);
                chosenMethod[0] = "Credit Card (•••• 4242)";
            }
        });

        view.findViewById(R.id.payment_gpay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbCard.setChecked(false);
                rbGPay.setChecked(true);
                chosenMethod[0] = "Google Pay / UPI";
            }
        });

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) {
                    listener.onPaymentConfirmed(chosenMethod[0], "$49.00 USD");
                }
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
