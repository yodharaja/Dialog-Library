package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.dialogshowcase.R;


public class MediaPickerBottomSheetDialog {

    public interface OnMediaOptionSelectedListener {
        void onCamera();
        void onGallery();
        void onDocuments();
        void onAudio();
    }

    public static void show(Context context, final OnMediaOptionSelectedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_media_picker, null);

        view.findViewById(R.id.media_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onCamera();
            }
        });

        view.findViewById(R.id.media_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onGallery();
            }
        });

        view.findViewById(R.id.media_documents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onDocuments();
            }
        });

        view.findViewById(R.id.media_audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) listener.onAudio();
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
