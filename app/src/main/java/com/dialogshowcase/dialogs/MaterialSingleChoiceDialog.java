package com.dialogshowcase.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MaterialSingleChoiceDialog {

    public interface OnItemSelectedListener {
        void onSelected(int index, String item);
    }

    public static void show(Context context, String title, final String[] items, int defaultIndex, final OnItemSelectedListener listener) {
        final int[] selectedIndex = {defaultIndex};

        new MaterialAlertDialogBuilder(context)
                .setTitle(title != null ? title : "Select an Option")
                .setSingleChoiceItems(items, defaultIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex[0] = which;
                    }
                })
                .setPositiveButton("Select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null && selectedIndex[0] >= 0 && selectedIndex[0] < items.length) {
                            listener.onSelected(selectedIndex[0], items[selectedIndex[0]]);
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
