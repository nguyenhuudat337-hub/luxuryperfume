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
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot);
        
        dbHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.forgot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText edtUserName = findViewById(R.id.editTextText);
        EditText edtPassWord = findViewById(R.id.editTextTextPassword);
        EditText edtPassWord2 = findViewById(R.id.editTextTextPassword2);
        Button btnReset = findViewById(R.id.button2);

        btnReset.setOnClickListener(v -> {
            String username = edtUserName.getText().toString().trim();
            String password = edtPassWord.getText().toString().trim();
            String password2 = edtPassWord2.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty() || password2.isEmpty()){
                Toast.makeText(this,"Please enter all information!",Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra xem username có tồn tại trong database không
            if(!dbHelper.checkUsername(username)){
                Toast.makeText(this,"The account does not exist",Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.equals(password2)){
                // Cập nhật mật khẩu mới vào database
                boolean isUpdated = dbHelper.updatePassword(username, password);
                if(isUpdated) {
                    Toast.makeText(this,"Password changed successfully",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this,"Update failed!",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button4).setOnClickListener(v -> finish());
    }
}
