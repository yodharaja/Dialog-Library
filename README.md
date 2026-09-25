

# Dialog Library - Modern Android Dialogs

<p align="center">
  <img src="https://img.shields.io/badge/Android_Studio-IDE-3DDC84?labelColor=0D1117&logo=androidstudio&logoColor=white" alt="Android Studio" />
  <img src="https://img.shields.io/badge/Sketchware-Pro-FF6F00?labelColor=0D1117&logo=android&logoColor=white" alt="Sketchware Pro" />
  <img src="https://img.shields.io/badge/Java-8%2B%20%2F%2011%2B-ED8B00?labelColor=0D1117&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/XML-Layouts-E06C75?labelColor=0D1117&logo=xml&logoColor=white" alt="XML Layouts" />
  <img src="https://img.shields.io/badge/API-21%2B-00B0FF?labelColor=0D1117&logo=android&logoColor=white" alt="API 21+" />
  <img src="https://img.shields.io/badge/Material-Design%203-792EE5?labelColor=0D1117&logo=materialdesign&logoColor=white" alt="Material 3" />
</p>

**Dialog Library** is a comprehensive showcase and modular repository of **56+ beautiful, modern Android dialogs**. Designed specifically for developers using **Sketchware**, **Sketchware Pro**, and **Android Studio**.

Every single dialog is **100% modular and standalone**:
- ❌ **No heavy multi-megabyte AARs or dependencies**
- ❌ **No messy build errors or conflicting libraries**
- ✅ **Simply pick any dialog you like: Copy 1 Java file + 1 XML layout into your project!**
####  Quick Downloads
> - 📱 **APK File**: [Click Here to Download](https://github.com/yodharaja/Dialog-Library/releases/latest/download/DialogLibrary.apk)
> - 📦 **SWB File**: [Click Here to Download](https://github.com/yodharaja/Dialog-Library/releases/latest/download/DialogLibrary.swb)

---
## Visual Showcase & Screenshots

Explore the sleek Material 3 surfaces, SweetAlert states, modern bottom sheets, multi-step wizards, and smooth hardware-accelerated animations:

<p align="center">
  <img src="https://raw.githubusercontent.com/yodharaja/Dialog-Library/main/app/src/main/assets/screenshot1.png" width="18%" alt="Screenshot 1 - Overview" />
  <img src="https://raw.githubusercontent.com/yodharaja/Dialog-Library/main/app/src/main/assets/screenshot2.png" width="18%" alt="Screenshot 2 - Sweet Alerts & Material 3" />
  <img src="https://raw.githubusercontent.com/yodharaja/Dialog-Library/main/app/src/main/assets/screenshot3.png" width="18%" alt="Screenshot 3 - Bottom Sheets & Wizards" />
  <img src="https://raw.githubusercontent.com/yodharaja/Dialog-Library/main/app/src/main/assets/screenshot4.png" width="18%" alt="Screenshot 4 - Input Forms & Loaders" />
  <img src="https://raw.githubusercontent.com/yodharaja/Dialog-Library/main/app/src/main/assets/screenshot5.png" width="18%" alt="Screenshot 5 - Code Snippet Generator" />
</p>


## How to Use Any Dialog in Sketchware / Sketchware Pro

Follow these steps to integrate any of the 56+ dialogs into your Sketchware Pro project:

#### Step 1: Add the Dialog Java File
1. From the Three Dots (⋮) menu, select **Java/Kotlin Manager**.
2. Tap the floating **`+`** (Create or Import) button.
3. Enter the class name (for example: `SweetSuccessDialog`).
4. Copy the code from the corresponding dialog file in this repository (e.g. `app/src/main/java/com/sketchware/dialogshowcase/dialogs/SweetSuccessDialog.java`) and paste it.
5. ⚠️ **Match Your Package Name**: Change the very first line of the Java file to match your Sketchware package:
   ```java
   // Change from:
   package com.sketchware.dialogshowcase.dialogs;

   // To your actual project package:
   package com.my.app;
   ```

#### Step 3: Add the XML Layout
1. Tap the Three Dots (⋮) -> **Resource Manager** ( place XML into `res/layout/`).
2. Create or paste the corresponding XML file (e.g. `dialog_sweet_success.xml`).
3. *(Note: For Material 3 native dialogs such as `MaterialBasicDialog.java`, no custom XML layout is needed!)*

#### Step 4: Add Required Drawables
1. Tap the Three Dots (⋮) -> **Resource Manager** ( place vector XMLs into `res/drawable/`).
2. Add the reusable drawables referenced by the dialog:
   - `bg_dialog_surface.xml` (Card container with 24dp rounded corners)
   - `bg_badge_rounded.xml` (Pill/icon container)
   - The specific icon (e.g. `ic_check_circle.xml`, `ic_lock.xml`, `ic_trophy.xml`, etc.)

#### Step 5: Enable AppCompat & Material Components
1. Tap the Three Dots (⋮) -> **Library Manager**.
2. Toggle ON **AppCompat and Design / Material3 Manager**.

#### Step 6: Trigger the Dialog with an "Add Source Directly" Block
1. Open any Event logic (e.g., `button1 onClick`, `onItemClicked`, or `onCreate`).
2. Drag and drop an **`add source directly`** code block into your event.
3. Paste the one-line invocation code:
   ```java
   SweetSuccessDialog.show(MainActivity.this, "Awesome!", "Operation completed successfully.", new SweetSuccessDialog.OnConfirmListener() {
       @Override
       public void onConfirm() {
           // Your code when user clicks OK
           showMessage("Confirmed!");
       }
   });
   ```
4. Click **Run** to build your app. That's it! 🚀

##  How to Use in Android Studio

1. **Copy Java File**: Drop the dialog's `.java` file into your `app/src/main/java/<your_package>/dialogs/`.
2. **Copy XML & Drawables**:
   - Layout: Copy `dialog_*.xml` into `res/layout/`.
   - Drawables: Copy required drawables into `res/drawable/`.
   - Animations (if using animated dialogs): Copy enter/exit files into `res/anim/`.
3. **Call `.show(...)`**:
   ```java
   SweetSuccessDialog.show(MainActivity.this, "Order Confirmed", "Your receipt has been sent.", null);
   ```

## Global Colors & Surface Styling

To ensure colors and rounded corners look identical in your app, copy these standard styling tokens once:

1. **`res/values/colors.xml`**:
   ```xml
   <resources>
       <color name="colorPrimary">#2563EB</color>
       <color name="colorPrimaryContainer">#EFF6FF</color>
       <color name="colorSuccess">#10B981</color>
       <color name="colorWarning">#F59E0B</color>
       <color name="colorError">#EF4444</color>
       <color name="colorInfo">#06B6D4</color>
       <color name="colorSurface">#FFFFFF</color>
       <color name="colorTextPrimary">#1E293B</color>
       <color name="colorTextSecondary">#64748B</color>
   </resources>
   ```

2. **`res/drawable/bg_dialog_surface.xml`** (24dp rounded white background):
   ```xml
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
       <solid android:color="@android:color/white" />
       <corners android:radius="24dp" />
   </shape>
   ```

3. **`res/drawable/bg_badge_rounded.xml`** (Icon badge container):
   ```xml
   <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
       <solid android:color="#F1F5F9" />
       <corners android:radius="14dp" />
   </shape>
   ```
## Required Dependencies

Add Google Material Components to your `app/build.gradle`:

```groovy
dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.1'
    implementation 'com.google.android.material:material:1.12.0'
}
```

In **Sketchware Pro**:
- Open **Library Manager** -> Enable **AppCompat and Design / Material3**.

## License & Attribution

```text
MIT License

Copyright (c) 2026 yodharaja

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

⭐ **Star this repository on GitHub**: [https://github.com/yodharaja/Dialog-Library](https://github.com/yodharaja/Dialog-Library)
