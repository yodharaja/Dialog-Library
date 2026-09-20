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


public class SetupPermissionWizardDialog {

    public interface OnPermissionWizardCompletedListener {
        void onCompleted();
    }

    public static void show(final Context context, final OnPermissionWizardCompletedListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_setup_permission_wizard, null);

        final TextView tvStepIndicator = view.findViewById(R.id.tv_perm_step_indicator);
        final View dot1 = view.findViewById(R.id.dot_perm_1);
        final View dot2 = view.findViewById(R.id.dot_perm_2);
        final View dot3 = view.findViewById(R.id.dot_perm_3);
        final ImageView ivIcon = view.findViewById(R.id.iv_perm_icon);
        final TextView tvTitle = view.findViewById(R.id.tv_perm_title);
        final TextView tvDescription = view.findViewById(R.id.tv_perm_description);
        final MaterialButton btnBack = view.findViewById(R.id.btn_perm_back);
        final MaterialButton btnNext = view.findViewById(R.id.btn_perm_next);

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
                int step = currentStep[0];
                tvStepIndicator.setText("Step " + step + " of 3: Permissions");

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
                    animateDot(dot1, step == 1, context);
                    animateDot(dot2, step == 2, context);
                    animateDot(dot3, step == 3, context);
                }

                final int targetIconRes;
                if (step == 1) {
                    btnBack.setVisibility(View.INVISIBLE);
                    btnNext.setText("Next");
                    btnNext.setIconResource(R.drawable.ic_arrow_forward);
                    targetIconRes = R.drawable.ic_image;
                    tvTitle.setText("Storage & Media Access");
                    tvDescription.setText("Required to save, view, and export dialog layouts and assets directly to your device storage.");
                } else if (step == 2) {
                    btnBack.setVisibility(View.VISIBLE);
                    btnNext.setText("Next");
                    btnNext.setIconResource(R.drawable.ic_arrow_forward);
                    targetIconRes = R.drawable.ic_notification;
                    tvTitle.setText("Push Notifications");
                    tvDescription.setText("Stay informed about new dialog releases, Material 3 updates, and template component drops.");
                } else {
                    btnBack.setVisibility(View.VISIBLE);
                    btnNext.setText("Finish Setup");
                    btnNext.setIconResource(R.drawable.ic_check_circle);
                    targetIconRes = R.drawable.ic_check_circle;
                    tvTitle.setText("All Set & Ready");
                    tvDescription.setText("Your setup is complete. You can now build high-converting dialog flows with full source control.");
                }

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

        ValueAnimator widthAnim = ValueAnimator.ofInt(currentWidth, targetWidth);
        widthAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dot.getLayoutParams().width = (Integer) animation.getAnimatedValue();
                dot.requestLayout();
            }
        });

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
