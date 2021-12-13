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

public class LoginActivity extends AppCompatActivity {
    EditText fEmail,fPassword;
    TextView fCreateAccount,fForgetPassword;
    Button fLogin;
    CheckBox fcheckBox;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        fEmail = findViewById(R.id.editTextEmail);
        fPassword = findViewById(R.id.editTextPassword);
        fCreateAccount = findViewById(R.id.createAccount);
        fForgetPassword = findViewById(R.id.textForgetPassword);
        fLogin = findViewById(R.id.login_button);
        fcheckBox = findViewById(R.id.checkBox);
        firebaseAuth = FirebaseAuth.getInstance();

        fLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fEmail.getText().toString().trim();
                String password = fPassword.getText().toString().trim();
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

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Sai email hoặc mật khẩu vui lòng kiểm tra lại.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        fCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CreateAccountActivity.class));
            }
        });

    }
}