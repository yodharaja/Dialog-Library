package com.dialogshowcase.dialogs;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.util.Locale;


public class MaterialTimePickerHelper {

    public interface OnTimeSelectedListener {
        void onTimeSelected(int hour, int minute, String formattedTime);
    }

    public static void show(AppCompatActivity activity, final OnTimeSelectedListener listener) {
        final MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(10)
                .setMinute(30)
                .setTitleText("Select Reminder Time")
                .build();

        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String amPm = hour >= 12 ? "PM" : "AM";
                int displayHour = hour % 12;
                if (displayHour == 0) displayHour = 12;

                String formatted = String.format(Locale.getDefault(), "%02d:%02d %s", displayHour, minute, amPm);
                if (listener != null) {
                    listener.onTimeSelected(hour, minute, formatted);
                }
            }
        });

        timePicker.show(activity.getSupportFragmentManager(), "MATERIAL_TIME_PICKER");
    }
}
