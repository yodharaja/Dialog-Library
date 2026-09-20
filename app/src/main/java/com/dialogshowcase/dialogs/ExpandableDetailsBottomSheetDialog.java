package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.dialogshowcase.R;

public class ExpandableDetailsBottomSheetDialog {

    public static void show(Context context) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_expandable_details, null);

        MaterialButton btnDismiss = view.findViewById(R.id.btn_dismiss_details);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
