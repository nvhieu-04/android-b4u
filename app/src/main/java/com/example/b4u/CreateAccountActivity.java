package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {
    EditText fEmail,fName,fPassword,fPasswordAgain;
    Button btnLogin;
    TextView fTerm;
    FirebaseAuth firebaseAuth;
    CheckBox fcheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //
        fName = findViewById(R.id.editTextTextPersonName);
        fEmail = findViewById(R.id.editTextEmail);
        fPassword = findViewById(R.id.editTextPassword);
        fPasswordAgain = findViewById(R.id.editTextConfirmPassword);
        btnLogin = findViewById(R.id.login_button);
        fTerm = findViewById(R.id.textForgetPassword);
        fcheckBox = findViewById(R.id.checkBox);
        firebaseAuth =FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fEmail.getText().toString().trim();
                String password = fPassword.getText().toString().trim();
                String passwordCheck = fPasswordAgain.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    fEmail.setError("Vui lòng nhập lại địa chỉ email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    fPassword.setError("Vui lòng nhập lại mật khẩu");
                    return;
                }
                if(password.length() < 6)
                {
                    fPassword.setError("Mật khẩu phải có trên 6 kí tự");
                }
                if(password != passwordCheck)
                {
                    fPassword.setError("Mật khẩu bạn nhập phải trùng nhau");
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(CreateAccountActivity.this,"Đã tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        }
                        else {
                            Toast.makeText(CreateAccountActivity.this,"Lỗi",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}