package edu.utsa.cs3443.ibs074_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // Retrieve user's real name and roles from intent extras
        String realName = getIntent().getStringExtra("real_name");
        String[] roles = getIntent().getStringArrayExtra("roles");

        // Check if realName or roles is null
        if (realName != null && roles != null) {
            // Display user's real name and roles
            TextView userNameTextView = findViewById(R.id.userNameTextView);
            userNameTextView.setText(realName);

            TextView rolesTextView = findViewById(R.id.rolesTextView);
            StringBuilder rolesText = new StringBuilder();
            for (String role : roles) {
                rolesText.append(role).append("\n");
            }
            rolesTextView.setText(rolesText);
        } else {
            // Handle null values
            Toast.makeText(this, "Error: Intent extras are null", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity to prevent further issues
        }

        // Initialize logout button
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // Initialize Act I button
        Button act1Button = findViewById(R.id.act1Button);
        act1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAct(1);
            }
        });

        // Initialize Act II button
        Button act2Button = findViewById(R.id.act2Button);
        act2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAct(2);
            }
        });
    }

    private void logout() {
        // Clear login credentials and navigate back to MainActivity
        // (Implement logic to clear credentials as per your application design)
        finish();
    }

    private void viewAct(int actNumber) {
        // Navigate to ActActivity with act number
        Intent intent = new Intent(RoleActivity.this, ActActivity.class);
        intent.putExtra("act_number", actNumber);
        startActivity(intent);
    }
}
