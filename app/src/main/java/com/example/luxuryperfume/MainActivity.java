package com.example.luxuryperfume;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText edtUserName = findViewById(R.id.editTextText);
        EditText edtPassWord = findViewById(R.id.editTextTextPassword);
        Button edtButtonSignIn = findViewById(R.id.button);

        edtButtonSignIn.setOnClickListener(v -> {
            String username = edtUserName.getText().toString().trim();
            String password = edtPassWord.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please enter all information!",Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra đăng nhập từ Database
            if(dbHelper.checkUser(username, password)){
                int id = Integer.parseInt(dbHelper.getUserId(username));
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this,"Incorrect username or password!",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        });

        findViewById(R.id.textView).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ForgotPassword.class));
        });
    }
}
