package com.dialogshowcase.dialogs;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.dialogshowcase.R;


public class GitHubCommunityDialog {

    public static final String DEFAULT_GITHUB_PROFILE = "https://github.com/yodharaja";
    public static final String DEFAULT_PROJECT_REPO = "https://github.com/yodharaja/Dialog-Library";

    public static void show(final Context context, final String githubProfileUrl, final String projectRepoUrl) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_github_community, null);

        MaterialButton btnFollow = view.findViewById(R.id.btn_github_follow);
        MaterialButton btnRepo = view.findViewById(R.id.btn_github_repo);
        MaterialButton btnCopy = view.findViewById(R.id.btn_github_copy);

        final String profileUrl = (githubProfileUrl != null && !githubProfileUrl.isEmpty()) ? githubProfileUrl : DEFAULT_GITHUB_PROFILE;
        final String repoUrl = (projectRepoUrl != null && !projectRepoUrl.isEmpty()) ? projectRepoUrl : DEFAULT_PROJECT_REPO;

        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(true)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(context, profileUrl);
                dialog.dismiss();
            }
        });

        btnRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(context, repoUrl);
                dialog.dismiss();
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("GitHub Repo", repoUrl);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, "Repository link copied to clipboard!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private static void openUrl(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Unable to open browser: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
