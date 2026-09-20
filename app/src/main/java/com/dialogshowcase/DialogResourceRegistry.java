package com.dialogshowcase;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry containing exact resource mappings (Java file, Layout XML, Drawables, Anim XML)
 * for all 56 dialogs in the Dialog Showcase library.
 *
 * Used by CodeSnippetDialog to show accurate file requirements in Sketchware.
 */
public class DialogResourceRegistry {

    public static class ResourceInfo {
        public final String javaFile;
        public final String layoutXml;
        public final String drawables;
        public final String animXml;

        public ResourceInfo(String javaFile, String layoutXml, String drawables, String animXml) {
            this.javaFile = javaFile;
            this.layoutXml = layoutXml;
            this.drawables = drawables;
            this.animXml = animXml;
        }
    }

    private static final Map<String, ResourceInfo> REGISTRY = new HashMap<>();

    static {
        // 1. Bottom Sheets
        reg("sheet_media_picker", "MediaPickerBottomSheetDialog.java", "dialog_sheet_media_picker.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_camera.xml, ic_image.xml, ic_description.xml, ic_sparkle.xml", "None");
        reg("sheet_user_list", "UserListBottomSheetDialog.java", "dialog_sheet_user_list.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_person.xml", "None");
        reg("sheet_payment", "PaymentMethodBottomSheetDialog.java", "dialog_sheet_payment.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_dialog_card.xml, ic_payment.xml, ic_sparkle.xml", "None");
        reg("sheet_feedback_emoji", "FeedbackEmojiBottomSheetDialog.java", "dialog_sheet_feedback_emoji.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml", "None");
        reg("sheet_expandable_details", "ExpandableDetailsBottomSheetDialog.java", "dialog_sheet_expandable_details.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml", "None");
        reg("sheet_action", "ActionBottomSheetDialog.java", "dialog_sheet_action.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_edit.xml, ic_share.xml, ic_copy.xml, ic_delete.xml", "None");
        reg("sheet_share", "ShareBottomSheetDialog.java", "dialog_sheet_share.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_share.xml, ic_email.xml, ic_copy.xml", "None");
        reg("sheet_filter", "FilterBottomSheetDialog.java", "dialog_sheet_filter.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml", "None");

        // 2. Wizards & Steppers
        reg("otp_verify", "OtpVerificationDialog.java", "dialog_otp_verification.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_lock.xml, bg_otp_box.xml", "None");
        reg("step_wizard", "StepWizardDialog.java", "dialog_step_wizard.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_sparkle.xml, ic_code.xml, ic_trophy.xml", "None");
        reg("wizard_permissions", "SetupPermissionWizardDialog.java", "dialog_setup_permission_wizard.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_arrow_forward.xml, ic_image.xml, ic_notification.xml, ic_check_circle.xml, ic_arrow_back.xml", "None");
        reg("wizard_account", "AccountSetupWizardDialog.java", "dialog_account_setup_wizard.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_arrow_forward.xml, ic_person.xml, ic_palette.xml, ic_check_circle.xml, ic_sparkle.xml, ic_arrow_back.xml", "None");

        // 3. Animations
        reg("anim_celebration", "AnimatedCelebrationDialog.java", "dialog_animated_celebration.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_trophy.xml",
                "ObjectAnimator bounce & particle burst (res/anim/ not required)");
        reg("anim_popup", "PopupZoomDialog.java", "dialog_anim_popup.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_sparkle.xml",
                "dialog_popup_enter.xml, dialog_popup_exit.xml\nStyle: @style/DialogAnimation.Popup");
        reg("anim_fade", "SmoothFadeDialog.java", "dialog_anim_fade.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_info.xml",
                "dialog_fade_enter.xml, dialog_fade_exit.xml\nStyle: @style/DialogAnimation.Fade");
        reg("anim_slide_up", "SlideUpDialog.java", "dialog_anim_slide_up.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_check_circle.xml",
                "dialog_slide_up_enter.xml, dialog_slide_up_exit.xml\nStyle: @style/DialogAnimation.SlideUp");
        reg("anim_bounce", "BounceSpringDialog.java", "dialog_anim_bounce.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_trophy.xml",
                "dialog_bounce_enter.xml, dialog_bounce_exit.xml\nStyle: @style/DialogAnimation.Bounce");
        reg("anim_rotate", "RotateScaleDialog.java", "dialog_anim_rotate.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, bg_circle_container.xml, ic_palette.xml",
                "dialog_rotate_enter.xml, dialog_rotate_exit.xml\nStyle: @style/DialogAnimation.Rotate");

        // 4. Sweet Alerts
        reg("sweet_success", "SweetSuccessDialog.java", "dialog_sweet_success.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_check_circle.xml", "None");
        reg("sweet_warning", "SweetWarningDialog.java", "dialog_sweet_warning.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_warning.xml", "None");
        reg("sweet_error", "SweetErrorDialog.java", "dialog_sweet_error.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_error.xml", "None");
        reg("sweet_info", "SweetInfoDialog.java", "dialog_sweet_info.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_info.xml", "None");
        reg("sweet_question", "SweetQuestionDialog.java", "dialog_sweet_question.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_help.xml", "None");

        // 5. Material 3 Native Dialogs
        reg("mat_basic", "MaterialBasicDialog.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");
        reg("mat_destructive", "MaterialDestructiveDialog.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");
        reg("mat_single_choice", "MaterialSingleChoiceDialog.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");
        reg("mat_multi_choice", "MaterialMultiChoiceDialog.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");
        reg("mat_scrollable", "MaterialScrollableDialog.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");

        // 6. Inputs & Forms
        reg("input_prompt", "InputPromptDialog.java", "dialog_input_prompt.xml",
                "bg_dialog_surface.xml", "None");
        reg("input_pin", "PinSecurityDialog.java", "dialog_pin_security.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_lock.xml", "None");
        reg("input_rating", "RatingFeedbackDialog.java", "dialog_rating_feedback.xml",
                "bg_dialog_surface.xml, ic_star.xml, ic_star_border.xml", "None");
        reg("input_stepper", "NumberStepperDialog.java", "dialog_number_stepper.xml",
                "bg_dialog_surface.xml", "None");
        reg("input_subscribe", "EmailSubscribeDialog.java", "dialog_email_subscribe.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_email.xml", "None");
        reg("input_login", "LoginFormDialog.java", "dialog_login_form.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_person.xml, ic_email.xml, ic_lock.xml", "None");
        reg("input_register", "RegisterFormDialog.java", "dialog_register_form.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_sparkle.xml, ic_person.xml, ic_email.xml, ic_lock.xml", "None");
        reg("input_change_pw", "ChangePasswordDialog.java", "dialog_change_password.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_lock.xml", "None");
        reg("input_address", "AddressFormDialog.java", "dialog_address_form.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_location.xml", "None");
        reg("input_support", "ContactSupportDialog.java", "dialog_contact_support.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_support.xml", "None");
        reg("input_coupon", "PromoCouponDialog.java", "dialog_promo_coupon.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_discount.xml, ic_check_circle.xml", "None");

        // 7. Progress & Status
        reg("progress_determinate", "ProgressDeterminateDialog.java", "dialog_progress_determinate.xml",
                "bg_dialog_surface.xml", "None");
        reg("progress_spinner", "LoadingSpinnerDialog.java", "dialog_loading_spinner.xml",
                "bg_dialog_surface.xml", "None");
        reg("progress_multistep", "MultiStepLoaderDialog.java", "dialog_multistep_loader.xml",
                "bg_dialog_surface.xml, ic_check_circle.xml", "None");

        // 8. Pickers
        reg("picker_date", "MaterialDatePickerHelper.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");
        reg("picker_time", "MaterialTimePickerHelper.java", "Built-in MaterialAlertDialogBuilder (No XML layout required)",
                "None (Native Material 3 theme)", "None");
        reg("picker_color", "ColorPickerDialog.java", "dialog_color_picker.xml",
                "bg_dialog_surface.xml, bg_circle_container.xml", "None");

        // 9. Custom & System
        reg("github_community", "GitHubCommunityDialog.java", "dialog_github_community.xml",
                "bg_dialog_surface.xml, bg_circle_container.xml, ic_github.xml, ic_star.xml, ic_copy.xml", "None");
        reg("custom_glass", "GlassmorphicDialog.java", "dialog_glassmorphic.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_sparkle.xml", "None");
        reg("custom_whatisnew", "WhatIsNewDialog.java", "dialog_what_is_new.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_sparkle.xml, ic_check_circle.xml", "None");
        reg("custom_reward", "RewardUnlockDialog.java", "dialog_reward_unlock.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_trophy.xml", "None");
        reg("custom_offline", "NoInternetDialog.java", "dialog_no_internet.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_wifi_off.xml", "None");
        reg("custom_profile", "DeveloperProfileDialog.java", "dialog_developer_profile.xml",
                "bg_dialog_surface.xml, bg_circle_container.xml, ic_person.xml", "None");
        reg("custom_delete", "DeleteConfirmDialog.java", "dialog_delete_confirm.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_delete.xml", "None");
        reg("custom_permission", "PermissionRequestDialog.java", "dialog_permission_request.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_lock.xml, ic_check_circle.xml", "None");
        reg("custom_notification", "CustomNotificationDialog.java", "dialog_custom_notification.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_notification.xml", "None");
        reg("custom_language", "LanguageSelectDialog.java", "dialog_language_select.xml",
                "bg_dialog_surface.xml, ic_language.xml", "None");
        reg("custom_battery", "BatterySaverDialog.java", "dialog_battery_saver.xml",
                "bg_dialog_surface.xml, bg_badge_rounded.xml, ic_battery.xml", "None");
    }

    private static void reg(String id, String java, String layout, String drawables, String anim) {
        REGISTRY.put(id, new ResourceInfo(java, layout, drawables, anim));
    }

    public static ResourceInfo getInfo(String id) {
        ResourceInfo info = REGISTRY.get(id);
        if (info != null) return info;
        return new ResourceInfo("CustomDialog.java", "dialog_custom.xml", "bg_dialog_surface.xml, bg_badge_rounded.xml", "None");
    }

    public static ResourceInfo getInfoByTitleOrJava(String title, String java) {
        if (java != null && !java.isEmpty() && !java.equals("CustomDialog.java")) {
            for (ResourceInfo info : REGISTRY.values()) {
                if (java.equalsIgnoreCase(info.javaFile) || info.javaFile.contains(java) || java.contains(info.javaFile)) {
                    return info;
                }
            }
        }
        if (title != null && !title.isEmpty()) {
            String cleanTitle = title.toLowerCase().replaceAll("[^a-z0-9]", "");
            for (ResourceInfo info : REGISTRY.values()) {
                String cleanJava = info.javaFile.toLowerCase().replaceAll("[^a-z0-9]", "");
                if (cleanTitle.contains(cleanJava.replace("dialog", "").replace(".java", "")) ||
                    cleanJava.contains(cleanTitle)) {
                    return info;
                }
            }
        }
        return null;
    }

    public static String getJavaFile(String id) {
        return getInfo(id).javaFile;
    }

    public static String getLayoutXml(String id) {
        return getInfo(id).layoutXml;
    }

    public static String getDrawables(String id) {
        return getInfo(id).drawables;
    }

    public static String getAnimXml(String id) {
        return getInfo(id).animXml;
    }
}
