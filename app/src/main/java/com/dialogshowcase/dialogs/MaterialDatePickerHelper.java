package com.dialogshowcase.dialogs;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MaterialDatePickerHelper {

    public interface OnDateSelectedListener {
        void onDateSelected(long timestamp, String formattedDate);
    }

    public static void show(AppCompatActivity activity, final OnDateSelectedListener listener) {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Target Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                if (listener != null && selection != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                    String formatted = sdf.format(new Date(selection));
                    listener.onDateSelected(selection, formatted);
                }
            }
        });

        datePicker.show(activity.getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
    }
}
