package com.example.luxuryperfume;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        
        dbHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText edtUserName = findViewById(R.id.editTextText);
        EditText edtPassWord = findViewById(R.id.editTextTextPassword);
        EditText edtPassWord2 = findViewById(R.id.editTextTextPassword2);
        Button edtButtonSignUp = findViewById(R.id.button2);

        edtButtonSignUp.setOnClickListener(v -> {
            String username = edtUserName.getText().toString().trim();
            String password = edtPassWord.getText().toString().trim();
            String password2 = edtPassWord2.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty() || password2.isEmpty()){
                Toast.makeText(this,"Please enter all the information!",Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.checkUsername(username)) {
                Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.equals(password2)){
                // Sử dụng lớp User.java
                User newUser = new User(username, password, "", "", "","");
                boolean success = dbHelper.addUser(newUser);
                
                if (success) {
                    Toast.makeText(this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this,"Registration failed!",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button3).setOnClickListener(v -> finish());
    }
}
