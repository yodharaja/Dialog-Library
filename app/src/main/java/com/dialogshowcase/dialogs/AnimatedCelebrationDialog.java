package com.dialogshowcase.dialogs; //Your Package 

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R; //Your Package

public class AnimatedCelebrationDialog {

    public interface OnCelebrationDismissedListener {
        void onDismissed();
    }

    public static void show(Context context, final OnCelebrationDismissedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_animated_celebration, null);

        final View iconContainer = view.findViewById(R.id.anim_icon_container);
        final View icon = view.findViewById(R.id.anim_icon);
        MaterialButton btnContinue = view.findViewById(R.id.anim_btn_continue);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onDismissed();
                }
            }
        });

        dialog.show();

        //smooth scale & bounce animation
        iconContainer.setScaleX(0.2f);
        iconContainer.setScaleY(0.2f);
        iconContainer.setAlpha(0f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iconContainer, "scaleX", 0.2f, 1.15f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iconContainer, "scaleY", 0.2f, 1.15f, 1.0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iconContainer, "alpha", 0f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY, alpha);
        set.setDuration(700);
        set.setInterpolator(new OvershootInterpolator(2.5f));
        set.start();
    }
}
