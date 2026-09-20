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


public class RewardUnlockDialog {

    public interface OnRewardClaimedListener {
        void onClaim();
    }

    public static void show(Context context, final OnRewardClaimedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_reward_unlock, null);

        MaterialButton btnClaim = view.findViewById(R.id.dialog_btn_claim);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) listener.onClaim();
            }
        });

        dialog.show();
    }
}
