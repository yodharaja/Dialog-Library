package com.dialogshowcase.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MaterialScrollableDialog {

    public interface OnAcceptListener {
        void onAccept();
    }

    public static void show(Context context, String title, String longText, final OnAcceptListener listener) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title != null ? title : "Open Source Licenses")
                .setMessage(longText != null ? longText : 
                        "Apache License 2.0\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\nhttp://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and limitations under the License.")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) listener.onAccept();
                    }
                })
                .setNegativeButton("Close", null)
                .show();
    }
}
