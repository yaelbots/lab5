package edu.utsa.cs3443.ibs074_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.ibs074_lab5.model.User;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText fields
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Initialize login button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validate user credentials and fetch user details including roles
        User user = User.validateAndGetUser(getApplicationContext(), username, password);
        if (user != null) {
            // Retrieve roles from user object
            List<String> rolesList = user.getRoles();
            String[] roles = rolesList.toArray(new String[0]);
            // Successful login, navigate to RoleActivity with real name, roles, and username
            Intent intent = new Intent(MainActivity.this, RoleActivity.class);
            intent.putExtra("real_name", user.getRealName());
            intent.putExtra("roles", roles);
            intent.putExtra("username", username); // Add this line
            startActivity(intent);
        } else {
            // Invalid credentials, show toast message
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
