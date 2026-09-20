package com.dialogshowcase.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import java.util.List;

public class MaterialMultiChoiceDialog {

    public interface OnMultiChoiceSelectedListener {
        void onSelected(boolean[] checkedItems, List<String> selectedLabels);
    }

    public static void show(Context context, String title, final String[] items, final boolean[] checkedItems, final OnMultiChoiceSelectedListener listener) {
        final boolean[] currentChecked = checkedItems.clone();

        new MaterialAlertDialogBuilder(context)
                .setTitle(title != null ? title : "Select Categories")
                .setMultiChoiceItems(items, currentChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        currentChecked[which] = isChecked;
                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            List<String> selectedLabels = new ArrayList<>();
                            for (int i = 0; i < items.length; i++) {
                                if (currentChecked[i]) {
                                    selectedLabels.add(items[i]);
                                }
                            }
                            listener.onSelected(currentChecked, selectedLabels);
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
