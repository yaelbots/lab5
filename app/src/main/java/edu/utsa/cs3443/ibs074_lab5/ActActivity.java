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
import java.util.List;
import edu.utsa.cs3443.ibs074_lab5.model.Role;
import edu.utsa.cs3443.ibs074_lab5.model.Scene;
import edu.utsa.cs3443.ibs074_lab5.model.User;

public class ActActivity extends AppCompatActivity {

    public List<Scene> loadScenesFromAsset(String filename, List<String> userRoles) {
        List<Scene> scenesWithUserRoles = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    String[] roleNames = parts[1].split(",");
                    List<Role> roles = new ArrayList<>();
                    for (String roleName : roleNames) {
                        roleName = roleName.trim();
                        final String finalRoleName = roleName; // Declare a final variable
                        // Modify to check if the user roles contain the role name without character name
                        if (userRoles.stream().anyMatch(userRole -> userRole.contains(finalRoleName))) {
                            roles.add(new Role(roleName));
                        }
                    }
                    if (!roles.isEmpty()) {
                        scenesWithUserRoles.add(new Scene(title, roles));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scenesWithUserRoles;
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

        Log.d("ActActivity", "Username: " + username);
        Log.d("ActActivity", "User roles: " + userRoles);

        // Load the scenes for the act
        List<Scene> scenesWithUserRoles = loadScenesFromAsset("act" + actNumber + ".txt", userRoles);

        Log.d("ActActivity", "Scenes with user roles: " + scenesWithUserRoles.size());

        // Display scenes with roles
        LinearLayout rolesLinearLayout = findViewById(R.id.rolesLinearLayout);
        for (Scene scene : scenesWithUserRoles) {
            Log.d("ActActivity", "Scene: " + scene.getTitle());
            TextView sceneTextView = new TextView(this);
            sceneTextView.setText(scene.getTitle());
            sceneTextView.setTextSize(20);
            rolesLinearLayout.addView(sceneTextView);

            for (Role role : scene.getRoles()) {
                Log.d("ActActivity", "Role: " + role.getRoleName());
                TextView roleTextView = new TextView(this);
                roleTextView.setText(role.getRoleName());
                roleTextView.setTextSize(16); // adjust size as needed
                rolesLinearLayout.addView(roleTextView);
            }
        }
    }


}
