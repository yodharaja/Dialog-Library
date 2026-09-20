package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.dialogshowcase.R;


public class ShareBottomSheetDialog {

    public interface OnShareOptionSelectedListener {
        void onShareWhatsApp();
        void onShareTelegram();
        void onCopyLink();
    }

    public static void show(Context context, final OnShareOptionSelectedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_share, null);

        view.findViewById(R.id.share_whatsapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onShareWhatsApp();
            }
        });

        view.findViewById(R.id.share_telegram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onShareTelegram();
            }
        });

        view.findViewById(R.id.share_copy_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onCopyLink();
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
