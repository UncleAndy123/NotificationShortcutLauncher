# NotificationShortcutLauncher

A minimal stub APK that adds a **Notifications** tile to [Headwind MDM](https://h-mdm.com/) launcher grids on Kyocera Android devices. Tapping the tile opens Kyocera's built-in D-pad-friendly notification screen (`com.android.systemui.kc.notification.NotificationActivity`), which is fully navigable via hardware directional keys.
Specifically designed for use with [Purespeech MDM](https://github.com/UncleAndy123/Pure-speech-hmdm-android) on Kyocera flip phone devices.
---

## Why This Exists

Headwind MDM locks the Android status bar in kiosk/managed mode, making the standard pull-down notification shade inaccessible. Kyocera rugged devices ship with a dedicated notification Activity inside `com.android.systemui` designed for D-pad navigation, but there is no built-in way to launch it from a custom MDM launcher. This APK provides a zero-overhead bridge — it appears in the HMDM app grid like any other app, launches the Kyocera notification screen on tap, and immediately exits so the back stack stays clean.

---

## Compatibility

| Attribute | Value |
|---|---|
| Target device | Kyocera DuraXV LTE E4810 / E4610 and similar Kyocera Android devices |
| Android version | 7.1+ (API 25+) |
| MDM | Headwind MDM Community Edition (self-hosted) |
| Google Play Services | Not required |

> **Note:** The `com.android.systemui.kc.notification.NotificationActivity` class is Kyocera-specific and will not exist on non-Kyocera devices. The app will silently do nothing on unsupported hardware.

---

## How It Works

The APK contains a single transparent Activity (`Theme.NoDisplay`) that:

1. Fires an explicit Intent targeting `com.android.systemui.kc.notification.NotificationActivity`
2. Calls `finish()` immediately so no stub screen appears in the back stack

No permissions are required. No background services run.

---

## Building

Clone the repo and open in Android Studio. The project has no dependencies beyond the Android SDK.

```bash
git clone https://github.com/YourUsername/NotificationShortcutLauncher.git
```

Build a release APK via **Build → Generate Signed APK**, or for testing:

```bash
./gradlew assembleDebug
```

The output APK will be at `app/build/outputs/apk/debug/app-debug.apk`.

---

## HMDM Configuration

### 1. Upload the APK

1. Open your Headwind MDM admin panel
2. Go to **Applications → All Applications**
3. Click **Add Application** (the `+` button)
4. Set the fields:

| Field | Value |
|---|---|
| **Name** | `Notifications` |
| **Package name** | `com.purespeech.notificationshortcutlauncher` |
| **Version** | `1.0` Add 0 for Pre-installed apps |
| **Type** | `Android application (APK)` |

5. Upload the APK file
6. Click **Save**

---

### 2. Add to a Configuration

1. Go to **Configurations** and open the configuration applied to your devices
2. Scroll to the **Applications** section and click **Add Application**
3. Search for `Notifications` and select it
4. Set the following options:

| Option | Setting |
|---|---|
| **Show on device** | ✅ Enabled |
| **Remove after install** | ❌ Disabled |
| **Run at boot** | ❌ Disabled |

5. Optionally set an **Icon URL** pointing to a bell icon image hosted on your HMDM server or any accessible URL. If left blank, HMDM will use the app's built-in icon.

---

### 3. Push to Devices

1. Save the configuration
2. Go to **Devices**, select your target device(s)
3. Click **Send configuration** (or it will sync automatically on the next check-in)

The **Notifications** tile will appear in the launcher grid alongside your other managed apps. It is fully navigable by D-pad.

---

## Customizing the Icon

To use a custom icon, host a PNG image (recommended: 192×192 px) on your HMDM server or any HTTPS URL accessible from the device, then paste the URL into the **Icon URL** field in the application settings within HMDM.

---

## License

MIT — do whatever you like with it.
