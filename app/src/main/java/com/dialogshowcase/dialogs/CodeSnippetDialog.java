package com.dialogshowcase.dialogs;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.DialogResourceRegistry;
import com.dialogshowcase.R;

public class CodeSnippetDialog {

    public static void show(final Context context, String dialogTitle, final String codeSnippet) {
        show(context, dialogTitle, null, null, null, null, codeSnippet);
    }

    public static void show(final Context context,
                            final String dialogTitle,
                            String javaFile,
                            String layoutXml,
                            String drawables,
                            String animXml,
                            final String codeSnippet) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_code_preview, null);

        TextView tvTitle = view.findViewById(R.id.tv_dialog_code_title);
        final TextView tvStepIndicator = view.findViewById(R.id.tv_wizard_step_indicator);
        ImageView btnCloseHeader = view.findViewById(R.id.btn_close_header);

        // Step layouts
        final ScrollView layoutStep1 = view.findViewById(R.id.layout_step_1);
        final ScrollView layoutStep2 = view.findViewById(R.id.layout_step_2);
        final ScrollView layoutStep3 = view.findViewById(R.id.layout_step_3);
        final ScrollView layoutStep4 = view.findViewById(R.id.layout_step_4);
        final ScrollView layoutStep5 = view.findViewById(R.id.layout_step_5);

        // Stepper indicators
        final LinearLayout stepInd1 = view.findViewById(R.id.step_indicator_1);
        final LinearLayout stepInd2 = view.findViewById(R.id.step_indicator_2);
        final LinearLayout stepInd3 = view.findViewById(R.id.step_indicator_3);
        final LinearLayout stepInd4 = view.findViewById(R.id.step_indicator_4);
        final LinearLayout stepInd5 = view.findViewById(R.id.step_indicator_5);

        final TextView stepCircle1 = view.findViewById(R.id.step_circle_1);
        final TextView stepCircle2 = view.findViewById(R.id.step_circle_2);
        final TextView stepCircle3 = view.findViewById(R.id.step_circle_3);
        final TextView stepCircle4 = view.findViewById(R.id.step_circle_4);
        final TextView stepCircle5 = view.findViewById(R.id.step_circle_5);

        final TextView stepText1 = view.findViewById(R.id.step_text_1);
        final TextView stepText2 = view.findViewById(R.id.step_text_2);
        final TextView stepText3 = view.findViewById(R.id.step_text_3);
        final TextView stepText4 = view.findViewById(R.id.step_text_4);
        final TextView stepText5 = view.findViewById(R.id.step_text_5);

        final View stepLine1 = view.findViewById(R.id.step_line_1);
        final View stepLine2 = view.findViewById(R.id.step_line_2);
        final View stepLine3 = view.findViewById(R.id.step_line_3);
        final View stepLine4 = view.findViewById(R.id.step_line_4);

        // Content views
        TextView tvJava = view.findViewById(R.id.tv_java_file);
        TextView tvJavaInstructions = view.findViewById(R.id.tv_java_instructions);
        TextView tvLayout = view.findViewById(R.id.tv_layout_file);
        TextView tvDrawables = view.findViewById(R.id.tv_drawables_list);
        TextView tvAnims = view.findViewById(R.id.tv_anims_list);
        TextView tvAnimInstructions = view.findViewById(R.id.tv_anim_instructions);
        TextView tvCode = view.findViewById(R.id.tv_code_content);
        TextView tvCodeInstructions = view.findViewById(R.id.tv_code_instructions);

        // Navigation buttons
        final MaterialButton btnBack = view.findViewById(R.id.btn_wizard_back);
        final MaterialButton btnNext = view.findViewById(R.id.btn_wizard_next);

        // Auto-verify and fetch accurate resources from DialogResourceRegistry
        DialogResourceRegistry.ResourceInfo autoInfo =
                DialogResourceRegistry.getInfoByTitleOrJava(dialogTitle, javaFile);
        if (autoInfo != null) {
            if (drawables == null || drawables.isEmpty() || drawables.contains("bg_bottom_sheet") || drawables.contains("bg_otp_box_focused") || drawables.contains("bg_glass_card") || drawables.equals("None")) {
                drawables = autoInfo.drawables;
            }
            if (layoutXml == null || layoutXml.isEmpty() || layoutXml.contains("bottom_sheet_") || layoutXml.equals("dialog_custom.xml")) {
                layoutXml = autoInfo.layoutXml;
            }
            if (javaFile == null || javaFile.isEmpty() || javaFile.equals("CustomDialog.java")) {
                javaFile = autoInfo.javaFile;
            }
            if ((animXml == null || animXml.isEmpty() || animXml.equals("None")) && !autoInfo.animXml.equals("None")) {
                animXml = autoInfo.animXml;
            }
        }

        // Format code snippet: replace 'context' or 'activity' with 'YourActivity.this'
        String formattedSnippet = codeSnippet != null ? codeSnippet : "";
        if (!formattedSnippet.isEmpty()) {
            formattedSnippet = formattedSnippet.replaceAll("\\b(context|activity)\\b", "YourActivity.this");
        }
        final String finalCodeSnippet = formattedSnippet;

        final String currentPkg = (context != null && context.getPackageName() != null && !context.getPackageName().isEmpty())
                ? context.getPackageName()
                : "yourpackage";

        // Populate content
        if (dialogTitle != null) {
            tvTitle.setText(dialogTitle);
        }
        if (tvJava != null && javaFile != null) {
            tvJava.setText(javaFile);
        }
        if (tvJavaInstructions != null) {
            tvJavaInstructions.setText(
                    "• Java/Kotlin Manager -> Create or Import Java file\n" +
                    "• Check imports in your Java file:\n" +
                    "   import " + currentPkg + ".R;  (or yourpackage.R)\n" +
                    "• In your Activity, ensure you import dialogs:\n" +
                    "   import " + currentPkg + ".dialogs.*;  (or yourpackage.dialogs.*)");
        }
        if (tvLayout != null && layoutXml != null) {
            tvLayout.setText(layoutXml);
        }
        if (tvDrawables != null && drawables != null) {
            tvDrawables.setText(drawables);
        }
        if (tvAnims != null) {
            if (animXml != null && !animXml.trim().isEmpty() && !animXml.equalsIgnoreCase("None")) {
                tvAnims.setText(animXml);
            } else {
                tvAnims.setText("None (Native Material 3 window transition)");
                if (tvAnimInstructions != null) {
                    tvAnimInstructions.setText("• Native Material 3 animation used\n• No custom XMLs required");
                }
            }
        }
        if (tvCode != null) {
            tvCode.setText(finalCodeSnippet);
            tvCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null && finalCodeSnippet != null && !finalCodeSnippet.isEmpty()) {
                        ClipData clip = ClipData.newPlainText("Code Snippet", finalCodeSnippet);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(context, "Code snippet copied to clipboard!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if (tvCodeInstructions != null) {
            tvCodeInstructions.setText(
                    "• In Sketchware, use 'Add Source Directly' block (e.g. in onClick)\n" +
                    "• Replace 'YourActivity.this' with your actual Activity (e.g. MainActivity.this)\n" +
                    "• In your Activity imports, make sure to add:\n" +
                    "   import " + currentPkg + ".R;\n" +
                    "   import " + currentPkg + ".dialogs.*;");
        }

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        if (btnCloseHeader != null) {
            btnCloseHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        // Step Controller with smooth animations
        class StepManager {
            int currentStep = 1;

            void goToStep(final int targetStep, final boolean animate) {
                if (targetStep == currentStep && animate) return;
                final int prevStep = currentStep;
                currentStep = targetStep;

                // 1. Update Subtitle text with gentle fade
                final String stepTitle;
                switch (targetStep) {
                    case 1: stepTitle = "Step 1 of 5: Java Class"; break;
                    case 2: stepTitle = "Step 2 of 5: XML Layout"; break;
                    case 3: stepTitle = "Step 3 of 5: Drawables & Icons"; break;
                    case 4: stepTitle = "Step 4 of 5: Window Animations"; break;
                    case 5: default: stepTitle = "Step 5 of 5: Sketchware Logic"; break;
                }

                if (tvStepIndicator != null) {
                    if (animate) {
                        tvStepIndicator.animate().cancel();
                        tvStepIndicator.animate()
                                .alpha(0f)
                                .setDuration(100)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvStepIndicator.setText(stepTitle);
                                        tvStepIndicator.animate().alpha(1f).setDuration(160).start();
                                    }
                                }).start();
                    } else {
                        tvStepIndicator.setText(stepTitle);
                        tvStepIndicator.setAlpha(1f);
                    }
                }

                // 2. Animate step card content (Direction-aware slide & fade)
                ScrollView outgoing = getStepLayout(prevStep);
                ScrollView incoming = getStepLayout(targetStep);

                if (animate && outgoing != null && incoming != null) {
                    boolean movingForward = (targetStep > prevStep);

                    // Ensure any other views are hidden
                    for (int i = 1; i <= 5; i++) {
                        if (i != prevStep && i != targetStep) {
                            ScrollView other = getStepLayout(i);
                            if (other != null) {
                                other.setVisibility(View.GONE);
                            }
                        }
                    }

                    final View outView = outgoing;
                    outView.animate().cancel();
                    outView.animate()
                            .translationX(movingForward ? -dpToPx(context, 40) : dpToPx(context, 40))
                            .alpha(0f)
                            .setDuration(180)
                            .setInterpolator(new AccelerateInterpolator())
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    outView.setVisibility(View.GONE);
                                    outView.setTranslationX(0f);
                                    outView.setAlpha(1f);
                                }
                            }).start();

                    incoming.animate().cancel();
                    incoming.setVisibility(View.VISIBLE);
                    incoming.setAlpha(0f);
                    incoming.setTranslationX(movingForward ? dpToPx(context, 40) : -dpToPx(context, 40));
                    incoming.animate()
                            .translationX(0f)
                            .alpha(1f)
                            .setDuration(260)
                            .setInterpolator(new DecelerateInterpolator())
                            .start();
                } else {
                    for (int i = 1; i <= 5; i++) {
                        ScrollView layout = getStepLayout(i);
                        if (layout != null) {
                            layout.setVisibility(i == targetStep ? View.VISIBLE : View.GONE);
                            layout.setTranslationX(0f);
                            layout.setAlpha(1f);
                        }
                    }
                }

                // 3. Update stepper circles & labels with pulse and bounce
                updateIndicator(context, stepCircle1, stepText1, targetStep, 1, animate);
                updateIndicator(context, stepCircle2, stepText2, targetStep, 2, animate);
                updateIndicator(context, stepCircle3, stepText3, targetStep, 3, animate);
                updateIndicator(context, stepCircle4, stepText4, targetStep, 4, animate);
                updateIndicator(context, stepCircle5, stepText5, targetStep, 5, animate);

                // 4. Update connecting lines with smooth ArgbEvaluator color transition
                updateLine(stepLine1, targetStep > 1, context, animate);
                updateLine(stepLine2, targetStep > 2, context, animate);
                updateLine(stepLine3, targetStep > 3, context, animate);
                updateLine(stepLine4, targetStep > 4, context, animate);

                // 5. Update Back & Next buttons
                if (btnBack != null) {
                    if (targetStep == 1) {
                        if (animate && prevStep > 1) {
                            btnBack.animate().cancel();
                            btnBack.animate().alpha(0f).setDuration(150).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    btnBack.setVisibility(View.INVISIBLE);
                                }
                            }).start();
                        } else {
                            btnBack.setVisibility(View.INVISIBLE);
                            btnBack.setAlpha(0f);
                        }
                    } else {
                        if (animate && prevStep == 1) {
                            btnBack.setVisibility(View.VISIBLE);
                            btnBack.setAlpha(0f);
                            btnBack.animate().cancel();
                            btnBack.animate().alpha(1f).setDuration(200).start();
                        } else {
                            btnBack.setVisibility(View.VISIBLE);
                            btnBack.setAlpha(1f);
                        }
                    }
                }

                if (btnNext != null) {
                    if (targetStep < 5) {
                        btnNext.setText("Next");
                        btnNext.setIconResource(R.drawable.ic_arrow_forward);
                    } else {
                        btnNext.setText("Done");
                        btnNext.setIcon(null);
                    }
                }
            }

            private ScrollView getStepLayout(int step) {
                switch (step) {
                    case 1: return layoutStep1;
                    case 2: return layoutStep2;
                    case 3: return layoutStep3;
                    case 4: return layoutStep4;
                    case 5: default: return layoutStep5;
                }
            }
        }

        final StepManager stepManager = new StepManager();

        // Stepper indicator clicks (Tap any step to navigate directly)
        View.OnClickListener stepClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                int target = 1;
                if (id == R.id.step_indicator_1) target = 1;
                else if (id == R.id.step_indicator_2) target = 2;
                else if (id == R.id.step_indicator_3) target = 3;
                else if (id == R.id.step_indicator_4) target = 4;
                else if (id == R.id.step_indicator_5) target = 5;
                stepManager.goToStep(target, true);
            }
        };

        if (stepInd1 != null) stepInd1.setOnClickListener(stepClickListener);
        if (stepInd2 != null) stepInd2.setOnClickListener(stepClickListener);
        if (stepInd3 != null) stepInd3.setOnClickListener(stepClickListener);
        if (stepInd4 != null) stepInd4.setOnClickListener(stepClickListener);
        if (stepInd5 != null) stepInd5.setOnClickListener(stepClickListener);

        // Back button
        if (btnBack != null) {
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stepManager.currentStep > 1) {
                        stepManager.goToStep(stepManager.currentStep - 1, true);
                    }
                }
            });
        }

        // Next / Done button
        if (btnNext != null) {
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stepManager.currentStep < 5) {
                        stepManager.goToStep(stepManager.currentStep + 1, true);
                    } else {
                        // Copy code snippet to clipboard and dismiss
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        if (clipboard != null && finalCodeSnippet != null && !finalCodeSnippet.isEmpty()) {
                            ClipData clip = ClipData.newPlainText("Code Snippet", finalCodeSnippet);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(context, "Code snippet copied to clipboard!", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }
            });
        }

        // Initialize at Step 1
        stepManager.goToStep(1, false);
        dialog.show();
    }

    private static void updateIndicator(Context context, TextView circle, TextView label, int currentStep, int targetStep, boolean animate) {
        if (circle == null || label == null) return;

        if (currentStep == targetStep) {
            // Active Step
            circle.setBackgroundResource(R.drawable.bg_step_circle_active);
            circle.setTextColor(ContextCompat.getColor(context, R.color.colorOnPrimary));
            circle.setText(String.valueOf(targetStep));

            label.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            label.setTypeface(null, Typeface.BOLD);

            if (animate) {
                circle.animate().cancel();
                circle.animate()
                        .scaleX(1.22f)
                        .scaleY(1.22f)
                        .setDuration(280)
                        .setInterpolator(new OvershootInterpolator(2.2f))
                        .start();
                label.animate().cancel();
                label.animate().scaleX(1.05f).scaleY(1.05f).setDuration(200).start();
            } else {
                circle.setScaleX(1.22f);
                circle.setScaleY(1.22f);
                label.setScaleX(1.05f);
                label.setScaleY(1.05f);
            }
        } else if (currentStep > targetStep) {
            // Completed Step (keep step number, do not use tick icon)
            circle.setBackgroundResource(R.drawable.bg_step_circle_completed);
            circle.setTextColor(ContextCompat.getColor(context, R.color.colorOnPrimary));
            circle.setText(String.valueOf(targetStep));

            label.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
            label.setTypeface(null, Typeface.NORMAL);

            if (animate) {
                circle.animate().cancel();
                circle.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
                label.animate().cancel();
                label.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
            } else {
                circle.setScaleX(1.0f);
                circle.setScaleY(1.0f);
                label.setScaleX(1.0f);
                label.setScaleY(1.0f);
            }
        } else {
            // Upcoming Step
            circle.setBackgroundResource(R.drawable.bg_step_circle_inactive);
            circle.setTextColor(ContextCompat.getColor(context, R.color.colorTextSecondary));
            circle.setText(String.valueOf(targetStep));

            label.setTextColor(ContextCompat.getColor(context, R.color.colorTextSecondary));
            label.setTypeface(null, Typeface.NORMAL);

            if (animate) {
                circle.animate().cancel();
                circle.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
                label.animate().cancel();
                label.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
            } else {
                circle.setScaleX(1.0f);
                circle.setScaleY(1.0f);
                label.setScaleX(1.0f);
                label.setScaleY(1.0f);
            }
        }
    }

    private static void updateLine(final View line, boolean isActive, Context context, boolean animate) {
        if (line == null) return;
        int activeColor = ContextCompat.getColor(context, R.color.colorPrimary);
        int inactiveColor = ContextCompat.getColor(context, R.color.colorSurfaceBorder);
        int targetColor = isActive ? activeColor : inactiveColor;

        Boolean previousState = (Boolean) line.getTag();
        line.setTag(isActive);

        if (!animate || previousState == null || previousState == isActive) {
            ViewCompat.setBackgroundTintList(line, ColorStateList.valueOf(targetColor));
            return;
        }

        int startColor = isActive ? inactiveColor : activeColor;
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, targetColor);
        colorAnim.setDuration(250);
        colorAnim.setInterpolator(new DecelerateInterpolator());
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int c = (Integer) animation.getAnimatedValue();
                ViewCompat.setBackgroundTintList(line, ColorStateList.valueOf(c));
            }
        });
        colorAnim.start();
    }

    private static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
