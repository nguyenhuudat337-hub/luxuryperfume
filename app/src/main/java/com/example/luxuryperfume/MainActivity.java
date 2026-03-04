package com.example.luxuryperfume;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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
            //lưu ý: chỉ tạo được dữ liệu của 1 ngời dùng, tạo mới thì đè cũ
            String user = getSharedPreferences("USER_FILE", MODE_PRIVATE)
                    .getString("username", "");

            String pass = getSharedPreferences("USER_FILE", MODE_PRIVATE)
                    .getString("password", "");

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please enter all the information!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(username.equals(user) && password.equals(pass)){
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this,"Incorrect account or password!",Toast.LENGTH_SHORT).show();
            }
        });

        Button edtButtonSignUp = findViewById(R.id.button2);
        edtButtonSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        TextView edtTextForgot = findViewById(R.id.textView);
        edtTextForgot.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
            startActivity(intent);
        });
    }

}