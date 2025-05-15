// File: app/src/main/java/com/asiah/formfit/EmergencyActivity.java
package com.asiah.formfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.asiah.formfit.main.LoginActivity;
import com.asiah.formfit.main.ExerciseSetupActivity;

/**
 * Emergency fallback activity that displays when there are compilation issues.
 * This allows the app to at least run so that the work can be viewed.
 */
public class EmergencyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simple message explaining the situation
        TextView tvAppName = findViewById(R.id.tvAppName);
        TextView tvAppTagline = findViewById(R.id.tvAppTagline);

        if (tvAppName != null) {
            tvAppName.setText("FormFit");
        }

        if (tvAppTagline != null) {
            tvAppTagline.setText("Some components couldn't be loaded due to compilation issues. This is a fallback view.");
        }

        // Add a button to try loading the main functionality
        Button btnTryLogin = new Button(this);
        btnTryLogin.setText("Try to Access App");
        btnTryLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToLaunchMainScreen();
            }
        });

        // Create detailed explanation
        TextView explanationText = new TextView(this);
        explanationText.setPadding(50, 100, 50, 50);
        explanationText.setTextSize(16);
        explanationText.setText("FormFit: AI-Powered Exercise Form Analysis\n\n" +
                "By: Asiah Abdisalam Hussein\n\n" +
                "Module: CMP6213 Mobile and Wearable Application Development\n\n" +
                "This prototype demonstrates an application that uses AI to analyze exercise form and provide feedback to users in real-time.\n\n" +
                "The prototype implementation encountered technical issues with package structure and class references that could not be resolved before the submission deadline. Some fixes have been applied but further refactoring is needed.");

        setContentView(explanationText);
    }

    /**
     * Try to launch the main functionality of the app
     * We attempt several activities in case some are working
     */
    private void tryToLaunchMainScreen() {
        try {
            // Try to launch LoginActivity
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // If LoginActivity fails, try ExerciseSetupActivity
            Intent setupIntent = new Intent(this, ExerciseSetupActivity.class);
            startActivity(setupIntent);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If all attempts fail, show a message
        TextView errorText = new TextView(this);
        errorText.setPadding(50, 100, 50, 50);
        errorText.setTextSize(16);
        errorText.setText("Unable to launch any activities. Please check the LogCat for details about the compilation errors.");
        setContentView(errorText);
    }
}