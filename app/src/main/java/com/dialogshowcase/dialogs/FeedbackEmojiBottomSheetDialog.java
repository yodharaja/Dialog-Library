package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.dialogshowcase.R;


public class FeedbackEmojiBottomSheetDialog {

    public interface OnFeedbackSubmittedListener {
        void onSubmitted(String emoji, String comment);
    }

    public static void show(final Context context, final OnFeedbackSubmittedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_feedback_emoji, null);

        final TextView emojiAmazing = view.findViewById(R.id.emoji_amazing);
        final TextView emojiGood = view.findViewById(R.id.emoji_good);
        final TextView emojiNeutral = view.findViewById(R.id.emoji_neutral);
        final TextView emojiBad = view.findViewById(R.id.emoji_bad);
        final TextInputEditText etComment = view.findViewById(R.id.et_emoji_comment);
        MaterialButton btnSend = view.findViewById(R.id.btn_send_emoji_feedback);

        final String[] selectedEmoji = {"😍"};
        final TextView[] emojis = new TextView[]{emojiAmazing, emojiGood, emojiNeutral, emojiBad};

        final Runnable updateSelection = new Runnable() {
            @Override
            public void run() {
                for (TextView tv : emojis) {
                    if (tv.getText().toString().equals(selectedEmoji[0])) {
                        tv.setBackgroundResource(R.drawable.bg_badge_rounded);
                        tv.setBackgroundTintList(context.getColorStateList(R.color.colorPrimaryContainer));
                    } else {
                        tv.setBackgroundResource(R.drawable.bg_badge_rounded);
                        tv.setBackgroundTintList(context.getColorStateList(R.color.colorSurfaceVariant));
                    }
                }
            }
        };

        for (final TextView tv : emojis) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedEmoji[0] = tv.getText().toString();
                    updateSelection.run();
                }
            });
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) {
                    String comment = etComment.getText() != null ? etComment.getText().toString().trim() : "";
                    listener.onSubmitted(selectedEmoji[0], comment);
                }
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
