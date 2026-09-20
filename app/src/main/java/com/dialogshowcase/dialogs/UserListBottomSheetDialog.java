package com.dialogshowcase.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.dialogshowcase.R;

public class UserListBottomSheetDialog {

    public interface OnUserSelectedListener {
        void onUserSelected(String userName, String userRole);
    }

    public static void show(Context context, final OnUserSelectedListener listener) {
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet_user_list, null);

        final RadioButton rb1 = view.findViewById(R.id.rb_user_1);
        final RadioButton rb2 = view.findViewById(R.id.rb_user_2);
        final RadioButton rb3 = view.findViewById(R.id.rb_user_3);
        MaterialButton btnAssign = view.findViewById(R.id.btn_assign_user);

        final String[] selectedName = {"Alex Rivera"};
        final String[] selectedRole = {"Lead Android Developer"};

        view.findViewById(R.id.user_item_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb1.setChecked(true);
                rb2.setChecked(false);
                rb3.setChecked(false);
                selectedName[0] = "Alex Rivera";
                selectedRole[0] = "Lead Android Developer";
            }
        });

        view.findViewById(R.id.user_item_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb1.setChecked(false);
                rb2.setChecked(true);
                rb3.setChecked(false);
                selectedName[0] = "Elena Vance";
                selectedRole[0] = "UI/UX Motion Designer";
            }
        });

        view.findViewById(R.id.user_item_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(true);
                selectedName[0] = "Raja Dev";
                selectedRole[0] = "Sketchware Pro Architect";
            }
        });

        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
                if (listener != null) {
                    listener.onUserSelected(selectedName[0], selectedRole[0]);
                }
            }
        });

        bottomSheet.setContentView(view);
        bottomSheet.show();
    }
}
