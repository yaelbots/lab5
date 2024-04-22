package edu.utsa.cs3443.ibs074_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class RoleActivity extends AppCompatActivity {

    private Map<String, List<String>> userRoles = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // Load roles from CSV file
        loadRoles();
        Button act1Button = findViewById(R.id.act1Button);
        Button act2Button = findViewById(R.id.act2Button);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Set the onClickListener for each button
        act1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the Act I button is clicked
                // For example, start a new Activity
                Intent intent = new Intent(RoleActivity.this, Act1Activity.class);
                startActivity(intent);
            }
        });

        act2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the Act II button is clicked
                // For example, start a new Activity
                Intent intent = new Intent(RoleActivity.this, Act2Activity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the Logout button is clicked
                // For example, return to the login screen
                Intent intent = new Intent(RoleActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Retrieve user's username from intent extras
        String username = getIntent().getStringExtra("username");

        // Display user's roles
        if (userRoles.containsKey(username)) {
            LinearLayout rolesLinearLayout = findViewById(R.id.rolesLinearLayout);
            for (String role : userRoles.get(username)) {
                TextView roleTextView = new TextView(this);
                roleTextView.setText(role);
                roleTextView.setTextSize(20);
                rolesLinearLayout.addView(roleTextView);
            }
        } else {
            // Handle case where user has no roles
            Toast.makeText(this, "Error: User has no roles", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadRoles() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("users.csv")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 4) {
                    String username = values[1]; // Use the second column as the username
                    List<String> roles = new ArrayList<>();
                    for (int i = 3; i < values.length; i++) {
                        roles.add(values[i]);
                    }
                    userRoles.put(username, roles);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}