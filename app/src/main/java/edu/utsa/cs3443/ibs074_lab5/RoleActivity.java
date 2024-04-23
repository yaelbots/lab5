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

import edu.utsa.cs3443.ibs074_lab5.model.User;

/**
 * The RoleActivity class represents the activity where the user can select an act to view their roles.
 * @author Yael Reyes ibs074
 */
public class RoleActivity extends AppCompatActivity {

    // A map to store the roles of each user
    private Map<String, List<String>> userRoles = new HashMap<>();

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // Load roles from CSV file
        loadRoles();
        Button act1Button = findViewById(R.id.act1Button);
        Button act2Button = findViewById(R.id.act2Button);
        Button logoutButton = findViewById(R.id.logoutButton);

        String username = getIntent().getStringExtra("username");

        // Set the onClickListener for each button
        act1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("act_number", 1);
                intent.putExtra("username", username); // pass the username
                startActivity(intent);
            }
        });

        act2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, ActActivity.class);
                intent.putExtra("act_number", 2);
                intent.putExtra("username", username); // pass the username
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });

        // Fetch the user's real name using the User class
        User user = User.fetchUserFromDatabaseOrFile(this, username);
        String realName = user != null ? user.getRealName() : "Unknown";

        // Find the TextView and set its text to the real name
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        userNameTextView.setText(realName);

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
            Toast.makeText(this, "Error: User has no roles", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Loads the roles of each user from a CSV file.
     */
    private void loadRoles() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("users.csv")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 4) {
                    String username = values[0]; // Use the first column as the username
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