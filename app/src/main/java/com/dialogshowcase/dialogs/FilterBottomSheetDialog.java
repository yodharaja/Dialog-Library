package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.dialogshowcase.R;

public class FilterBottomSheetDialog {

    public interface OnFilterAppliedListener {
        void onApply(String sortOption);
        void onReset();
    }

    public static void show(Context context, final OnFilterAppliedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_filter, null);

        final ChipGroup chipGroup = view.findViewById(R.id.chip_group_sort);
        MaterialButton btnReset = view.findViewById(R.id.dialog_btn_reset);
        MaterialButton btnApply = view.findViewById(R.id.dialog_btn_apply);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onReset();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                String sortOption = "Most Popular";
                int checkedId = chipGroup.getCheckedChipId();
                if (checkedId == R.id.chip_newest) {
                    sortOption = "Newest First";
                } else if (checkedId == R.id.chip_highest_rated) {
                    sortOption = "Highest Rated";
                }
                if (listener != null) listener.onApply(sortOption);
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
