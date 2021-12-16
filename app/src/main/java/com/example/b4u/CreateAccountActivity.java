package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {
    EditText fEmail,fName,fPassword,fPasswordAgain,fPhone;
    Button btnLogin;
    TextView fTerm,fLogin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    CheckBox fcheckBox;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //
        fName = findViewById(R.id.editTextTextPersonName);
        fEmail = findViewById(R.id.editTextEmail);
        fPassword = findViewById(R.id.editTextPassword);
        fPasswordAgain = findViewById(R.id.editTextConfirmPassword);
        fPhone = findViewById(R.id.editTextPhone);
        btnLogin = findViewById(R.id.login_button);
        fTerm = findViewById(R.id.textForgetPassword);
        fcheckBox = findViewById(R.id.checkBox);
        fLogin = findViewById(R.id.loginScreen);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        fLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fEmail.getText().toString().trim();
                String password = fPassword.getText().toString().trim();
                String passwordCheck = fPasswordAgain.getText().toString().trim();
                String name = fName.getText().toString().trim();
                String phone = fPhone.getText().toString().trim();
                if (!fcheckBox.isChecked())
                {
                    Toast.makeText(CreateAccountActivity.this,"Bạn chưa đồng ý điều khoản",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    fEmail.setError("Vui lòng nhập lại địa chỉ email");
                    return;
                }
                if(TextUtils.isEmpty(password) ){
                    fPassword.setError("Vui lòng nhập lại mật khẩu");
                    return;
                }
                if(password.length() < 6)
                {
                    fPassword.setError("Mật khẩu phải có trên 6 kí tự");
                    return;
                }
                if(!password.equals(passwordCheck))
                {
                    fPassword.setError("Mật khẩu bạn nhập phải trùng nhau");
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(CreateAccountActivity.this,"Đã tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",name);
                            user.put("fEmail",email);
                            user.put("fPhone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "Tạo tài khoản thành công: " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","Lỗi: " + e.toString() );
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        }
                        else {
                            Toast.makeText(CreateAccountActivity.this,"Lỗi hoặc tài khoản bạn tạo đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}