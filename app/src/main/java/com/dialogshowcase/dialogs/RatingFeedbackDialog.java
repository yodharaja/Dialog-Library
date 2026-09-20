package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;

public class RatingFeedbackDialog {

    public interface OnRatingSubmittedListener {
        void onSubmit(int stars, String comment);
    }

    public static void show(Context context, final OnRatingSubmittedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_rating_feedback, null);

        final ImageView[] starViews = new ImageView[]{
                view.findViewById(R.id.star_1),
                view.findViewById(R.id.star_2),
                view.findViewById(R.id.star_3),
                view.findViewById(R.id.star_4),
                view.findViewById(R.id.star_5)
        };

        final int[] currentRating = {4};

        final Runnable updateStars = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < starViews.length; i++) {
                    if (i < currentRating[0]) {
                        starViews[i].setImageResource(R.drawable.ic_star);
                    } else {
                        starViews[i].setImageResource(R.drawable.ic_star_border);
                    }
                }
            }
        };

        for (int i = 0; i < starViews.length; i++) {
            final int starIndex = i + 1;
            starViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentRating[0] = starIndex;
                    updateStars.run();
                }
            });
        }

        final TextInputEditText editFeedback = view.findViewById(R.id.feedback_edit_text);
        MaterialButton btnSubmit = view.findViewById(R.id.dialog_btn_submit);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    String comment = editFeedback.getText() != null ? editFeedback.getText().toString().trim() : "";
                    listener.onSubmit(currentRating[0], comment);
                }
            }
        });

        dialog.show();
    }
}
