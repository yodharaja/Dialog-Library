package com.dialogshowcase.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.dialogshowcase.R;

public class ProgressDeterminateDialog {

    public interface OnProgressListener {
        void onComplete();
        void onCancel();
    }

    public static void show(Context context, final OnProgressListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_determinate, null);

        final LinearProgressIndicator progressBar = view.findViewById(R.id.progress_bar);
        final TextView tvPercent = view.findViewById(R.id.tv_progress_percent);
        final TextView tvCount = view.findViewById(R.id.tv_progress_count);
        MaterialButton btnCancel = view.findViewById(R.id.dialog_btn_cancel);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        final Handler handler = new Handler(Looper.getMainLooper());
        final int[] progress = {0};
        final boolean[] isCancelled = {false};

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isCancelled[0] || !dialog.isShowing()) return;
                progress[0] += 5;
                if (progress[0] > 100) progress[0] = 100;

                progressBar.setProgress(progress[0]);
                tvPercent.setText(progress[0] + "%");
                tvCount.setText(progress[0] + " / 100 MB");

                if (progress[0] < 100) {
                    handler.postDelayed(this, 120);
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                                if (listener != null) listener.onComplete();
                            }
                        }
                    }, 400);
                }
            }
        };

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelled[0] = true;
                dialog.dismiss();
                if (listener != null) listener.onCancel();
            }
        });

        dialog.show();
        handler.post(runnable);
    }
}
