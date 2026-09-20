package com.dialogshowcase.dialogs;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R;


public class StepWizardDialog {

    public interface OnWizardCompletedListener {
        void onCompleted();
    }

    public static void show(final Context context, final OnWizardCompletedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_step_wizard, null);

        final TextView tvStepIndicator = view.findViewById(R.id.tv_wizard_step_indicator);
        final View dot1 = view.findViewById(R.id.dot_step_1);
        final View dot2 = view.findViewById(R.id.dot_step_2);
        final View dot3 = view.findViewById(R.id.dot_step_3);
        final ImageView ivIcon = view.findViewById(R.id.iv_wizard_icon);
        final TextView tvTitle = view.findViewById(R.id.tv_wizard_title);
        final TextView tvDescription = view.findViewById(R.id.tv_wizard_description);
        final MaterialButton btnBack = view.findViewById(R.id.btn_wizard_back);
        final MaterialButton btnNext = view.findViewById(R.id.btn_wizard_next);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        final int[] currentStep = {1};
        final boolean[] isFirstRun = {true};

        final Runnable updateStepUi = new Runnable() {
            @Override
            public void run() {
                tvStepIndicator.setText("Step " + currentStep[0] + " of 3");

                if (isFirstRun[0]) {
                    isFirstRun[0] = false;
                    int activeColor = ContextCompat.getColor(context, R.color.colorPrimary);
                    int inactiveColor = ContextCompat.getColor(context, R.color.colorSurfaceBorder);

                    dot1.getLayoutParams().width = dpToPx(context, 22);
                    dot2.getLayoutParams().width = dpToPx(context, 8);
                    dot3.getLayoutParams().width = dpToPx(context, 8);
                    dot1.requestLayout();
                    dot2.requestLayout();
                    dot3.requestLayout();

                    ViewCompat.setBackgroundTintList(dot1, ColorStateList.valueOf(activeColor));
                    ViewCompat.setBackgroundTintList(dot2, ColorStateList.valueOf(inactiveColor));
                    ViewCompat.setBackgroundTintList(dot3, ColorStateList.valueOf(inactiveColor));
                } else {
                    // Animate dots transition smoothly
                    animateDot(dot1, currentStep[0] == 1, context);
                    animateDot(dot2, currentStep[0] == 2, context);
                    animateDot(dot3, currentStep[0] == 3, context);
                }

                final int targetIconRes;
                if (currentStep[0] == 1) {
                    btnBack.setVisibility(View.INVISIBLE);
                    btnNext.setText("Next");
                    targetIconRes = R.drawable.ic_sparkle;
                    tvTitle.setText("Welcome to Dialogs Pro");
                    tvDescription.setText("Explore 35+ production-ready dialogs with full source code for Sketchware and Android Studio.");
                } else if (currentStep[0] == 2) {
                    btnBack.setVisibility(View.VISIBLE);
                    btnNext.setText("Next");
                    targetIconRes = R.drawable.ic_code;
                    tvTitle.setText("Modular Architecture");
                    tvDescription.setText("Every single dialog has its own dedicated Java and XML file. Just copy 2 files and call .show()!");
                } else {
                    btnBack.setVisibility(View.VISIBLE);
                    btnNext.setText("Get Started");
                    targetIconRes = R.drawable.ic_trophy;
                    tvTitle.setText("Ready to Build!");
                    tvDescription.setText("Star the project on GitHub and start integrating modern Material 3 dialogs into your apps today.");
                }

                // Smooth icon transition
                ivIcon.animate()
                        .alpha(0.2f)
                        .scaleX(0.75f)
                        .scaleY(0.75f)
                        .setDuration(120)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                ivIcon.setImageResource(targetIconRes);
                                ivIcon.animate()
                                        .alpha(1f)
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(220)
                                        .setInterpolator(new OvershootInterpolator())
                                        .start();
                            }
                        }).start();
            }
        };

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep[0] > 1) {
                    currentStep[0]--;
                    updateStepUi.run();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep[0] < 3) {
                    currentStep[0]++;
                    updateStepUi.run();
                } else {
                    dialog.dismiss();
                    if (listener != null) {
                        listener.onCompleted();
                    }
                }
            }
        });

        updateStepUi.run();
        dialog.show();
    }

    private static void animateDot(final View dot, boolean isActive, Context context) {
        int targetWidth = dpToPx(context, isActive ? 22 : 8);
        int activeColor = ContextCompat.getColor(context, R.color.colorPrimary);
        int inactiveColor = ContextCompat.getColor(context, R.color.colorSurfaceBorder);
        int targetColor = isActive ? activeColor : inactiveColor;

        int currentWidth = dot.getLayoutParams().width > 0 ? dot.getLayoutParams().width : dpToPx(context, 8);

        // Animate width
        ValueAnimator widthAnim = ValueAnimator.ofInt(currentWidth, targetWidth);
        widthAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dot.getLayoutParams().width = (Integer) animation.getAnimatedValue();
                dot.requestLayout();
            }
        });

        // Animate tint color
        int currentColor = isActive ? inactiveColor : activeColor;
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), currentColor, targetColor);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int c = (Integer) animation.getAnimatedValue();
                ViewCompat.setBackgroundTintList(dot, ColorStateList.valueOf(c));
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(widthAnim, colorAnim);
        set.setDuration(250);
        set.setInterpolator(new DecelerateInterpolator());
        set.start();
    }

    private static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}

