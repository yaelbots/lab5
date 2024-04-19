package edu.utsa.cs3443.ibs074_lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        // Get act number from intent extras
        int actNumber = getIntent().getIntExtra("act_number", 1);

        // Display the act number
        TextView actNumberTextView = findViewById(R.id.actNumberTextView);
        actNumberTextView.setText("Act " + actNumber);
    }
}