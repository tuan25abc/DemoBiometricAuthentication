# DemoBiometricAuthentication
Demo BiometricPrompt Dialog - New Feature in Android 9 Pie

## Code
```
BiometricPrompt.Builder(this)
                .setTitle("This is Biometric Dialog Title")
                .setSubtitle("This is Biometric Dialog Subtitle")
                .setDescription("This is Biometric Dialog Description")
                .setNegativeButton("This is Cancel button", this.mainExecutor,
                        DialogInterface.OnClickListener { _, _ ->
                            callback.onAuthenticationFailed()
                        })
                .build()
                .authenticate(CancellationSignal(), this.mainExecutor, callback)
```

![Screenshot](screenshot.jpg)

## Notes

The device/emulator is running Android 9 Pie, has a fingerprint sensor.
The user has registered at least one fingerprint and enabled screen look on device/emulator.


## References

* [BiometricPrompt Android](https://developer.android.com/reference/android/hardware/biometrics/BiometricPrompt)


## Authors

* **Tuan Nguyen** - Gmail: *tuan25abc@gmail.com*