package com.example.tuan25abc.demobiometricauthentication

import android.annotation.TargetApi
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.tuan25abc.demobiometricauthentication.BiometricUtils.Companion.isBiometricPromptEnabled
import com.example.tuan25abc.demobiometricauthentication.BiometricUtils.Companion.isFingerprintEnrolled
import com.example.tuan25abc.demobiometricauthentication.BiometricUtils.Companion.isHardwareSupported
import com.example.tuan25abc.demobiometricauthentication.BiometricUtils.Companion.isPermissionGranted
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // currently, minSdkVersion is 28 :P
            if (isPermissionGranted(this)
                    && isHardwareSupported(this)
                    && isBiometricPromptEnabled()
                    && isFingerprintEnrolled(this)) {
                displayBiometricPrompt(createAuthenticationCallback(rootView))
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    // currently, minSdkVersion is 28 :P
    private fun displayBiometricPrompt(callback: BiometricPrompt.AuthenticationCallback) {
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
    }

    // Create an Authentication Callback to listen for authentication events from the user
    // I only handle Success and Failed events.
    // Because Biometric Dialog will handle error while user touch sensor (display error message and help message)
    private fun createAuthenticationCallback(rootView: View): BiometricPrompt.AuthenticationCallback {
        return object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                // You can doSomething here, such as Analytic how many times user failed to auth (my crazy idea :P)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Snackbar.make(rootView, "Success", Snackbar.LENGTH_LONG).show()
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
                // You can doSomething here as well, like ... (You wouldn't want to hear my next crazy idea)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Snackbar.make(rootView, "Failed", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
