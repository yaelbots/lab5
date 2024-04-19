package edu.utsa.cs3443.ibs074_lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // Example data - replace with actual data
        String userName = "John Doe";
        String[] roles = {"Role 1", "Role 2"};

        // Display user name and roles
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        userNameTextView.setText(userName);

        TextView rolesTextView = findViewById(R.id.rolesTextView);
        StringBuilder rolesText = new StringBuilder();
        for (String role : roles) {
            rolesText.append(role).append("\n");
        }
        rolesTextView.setText(rolesText);

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
