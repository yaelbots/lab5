package edu.utsa.cs3443.ibs074_lab5;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.utsa.cs3443.ibs074_lab5.model.User;

/**
 * Activity to display scenes of a particular act based on user's roles.
 * @author Yael Reyes ibs074
 */
public class ActActivity extends AppCompatActivity {

    /**
     * Loads scenes from the asset file.
     *
     * @param filename The name of the asset file containing scenes.
     * @return A map containing scene titles as keys and their corresponding roles as values.
     */
    public Map<String, List<String>> loadScenesFromAsset(String filename) {
        Map<String, List<String>> scenes = new HashMap<>();
        try {
            Log.d("ActActivity", "Loading scenes from file: " + filename);
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
            Log.e("ActActivity", "Error loading scenes from file: " + e.getMessage());
            e.printStackTrace();
        }

        // Log the loaded scenes
        Log.d("ActActivity", "Loaded scenes: " + scenes);

        return scenes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        int actNumber = getIntent().getIntExtra("act_number", 1);
        Log.d("ActActivity", "Act number: " + actNumber); // Log the act number

        String username = getIntent().getStringExtra("username"); // get the username

        TextView actNumberTextView = findViewById(R.id.actNumberTextView);
        actNumberTextView.setText("Act " + actNumber);

        // Fetch the user's roles using the User class
        User user = User.fetchUserFromDatabaseOrFile(this, username);
        List<String> userRoles = new ArrayList<>();
        if (user != null) {
            for (String role : user.getRoles()) {
                // Split the roles by comma and trim the spaces
                String[] roles = role.split(",");
                for (String roleName : roles) {
                    // Extract the part in parentheses
                    int startIndex = roleName.indexOf('(');
                    int endIndex = roleName.indexOf(')');
                    if (startIndex != -1 && endIndex != -1) {
                        userRoles.add(roleName.substring(startIndex + 1, endIndex).trim());
                    }
                }
            }
        }

        // Log the user's roles
        Log.d("ActActivity", "User's roles: " + userRoles);

        // Load the scenes for the act
        Map<String, List<String>> scenes = loadScenesFromAsset("act" + actNumber + ".txt");
        Log.d("ActActivity", "Number of scenes: " + scenes.size()); // Log the number of scenes

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
