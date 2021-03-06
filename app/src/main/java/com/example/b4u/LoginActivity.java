package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText fEmail,fPassword;
    TextView fCreateAccount,fForgetPassword;
    Button fLogin;
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
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            overridePendingTransition(R.anim.anim_move_right,R.anim.anim_move_left);
            finish();
        }

        fLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = fEmail.getText().toString().trim();
                String password = fPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    fEmail.setError("Vui l??ng nh???p l???i ?????a ch??? email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    fPassword.setError("Vui l??ng nh???p l???i m???t kh???u");
                    return;
                }
                if(password.length() < 6)
                {
                    fPassword.setError("M???t kh???u ph???i c?? tr??n 6 k?? t???");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Ch??o m???ng b???n quay tr??? l???i. Ch??c b???n m???t ng??y t???t l??nh!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            overridePendingTransition(R.anim.anim_move_right,R.anim.anim_move_left);

                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Sai email ho???c m???t kh???u vui l??ng ki???m tra l???i.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        fCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CreateAccountActivity.class));
                overridePendingTransition(R.anim.anim_move_right,R.anim.anim_move_left);
            }
        });
        fForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordReset = new AlertDialog.Builder(view.getContext());
                passwordReset.setTitle("Qu??n M???t Kh???u");
                passwordReset.setMessage("H??y nh???p ?????a ch??? email c???a b???n ph??a d?????i nh???");
                passwordReset.setView(resetMail);
                passwordReset.setPositiveButton("G???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this,"B???n vui l??ng ki???m tra email c???a m??nh!",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Th??ng tin b???n v???a nh???p kh??ng t???n t???i. Vui l??ng ki???m tra l???i!",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordReset.setNegativeButton("Quay L???i", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordReset.create().show();

            }
        });

    }
}