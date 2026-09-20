package com.dialogshowcase.dialogs; //Your Package

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.dialogshowcase.R; //Your Package 


public class ActionBottomSheetDialog {

    public interface OnActionSelectedListener {
        void onEdit();
        void onShare();
        void onCopy();
        void onDelete();
    }

    public static void show(Context context, final OnActionSelectedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_action, null);

        view.findViewById(R.id.action_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onEdit();
            }
        });

        view.findViewById(R.id.action_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onShare();
            }
        });

        view.findViewById(R.id.action_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onCopy();
            }
        });

        view.findViewById(R.id.action_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onDelete();
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
