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

public class ForgotPassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.forgot), (v, insets) -> {
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
            String user = getSharedPreferences("USER_FILE", MODE_PRIVATE)
                    .getString("username", "");
            if(!username.equals(user)){
                Toast.makeText(this,"The account does not exist",Toast.LENGTH_SHORT).show();
                return;
            }
            if(username.isEmpty() || password.isEmpty() || password2.isEmpty()){
                Toast.makeText(this,"Please enter all the information!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.equals(password2)){
                getSharedPreferences("USER_FILE", MODE_PRIVATE)
                        .edit()
                        .putString("password", password)
                        .apply();
                Toast.makeText(this,"Password changed successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgotPassword.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
            }
        });
        Button edtButtonBack = findViewById(R.id.button4);
        edtButtonBack.setOnClickListener(v -> {
            finish();
        });
    }

}
