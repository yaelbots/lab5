package edu.utsa.cs3443.ibs074_lab5;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;


import edu.utsa.cs3443.ibs074_lab5.model.User;

public class ActActivity extends AppCompatActivity {

    public Map<String, List<String>> loadScenesFromAsset(String filename) {
        Map<String, List<String>> scenes = new HashMap<>();
        try {
            InputStream inputStream = getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    String[] roleNames = parts[1].split(",");
                    List<String> roles = new ArrayList<>();
                    for (String roleName : roleNames) {
                        roles.add(roleName.trim());
                    }
                    scenes.put(title, roles);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scenes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        int actNumber = getIntent().getIntExtra("act_number", 1);
        String username = getIntent().getStringExtra("username"); // get the username

        TextView actNumberTextView = findViewById(R.id.actNumberTextView);
        actNumberTextView.setText("Act " + actNumber);

        // Fetch the user's roles using the User class
        User user = User.fetchUserFromDatabaseOrFile(this, username);
        List<String> userRoles = user != null ? user.getRoles() : new ArrayList<>();

        // Load the scenes for the act
        Map<String, List<String>> scenes = loadScenesFromAsset("act" + actNumber + ".txt");

        // Display user's roles
        LinearLayout rolesLinearLayout = findViewById(R.id.rolesLinearLayout);
        for (Map.Entry<String, List<String>> entry : scenes.entrySet()) {
            List<String> matchingRoles = new ArrayList<>();
            for (String role : entry.getValue()) {
                if (userRoles.contains(role)) {
                    matchingRoles.add(role);
                }
            }
            if (!matchingRoles.isEmpty()) {
                TextView sceneTextView = new TextView(this);
                sceneTextView.setText(entry.getKey() + ": " + matchingRoles);
                sceneTextView.setTextSize(20);
                rolesLinearLayout.addView(sceneTextView);
            }
        }
    }
}
