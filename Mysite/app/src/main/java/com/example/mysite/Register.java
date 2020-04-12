package com.example.mysite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.mysite.R.layout.register;

public class Register  extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mfullname, memail, mpassword;
    Button mregister;
    TextView mlogin;
    FirebaseAuth fauth;
    FirebaseFirestore firestore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(register);
        mfullname = findViewById(R.id.username);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mregister = findViewById(R.id.ulogin);
        mlogin = findViewById(R.id.aaaa);
        fauth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        mregister.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            final String email = memail.getText().toString().trim();
                                            String password = mpassword.getText().toString().trim();
                                            String fullname = mfullname.getText().toString().trim();

                                            if (TextUtils.isEmpty(email)) {
                                                memail.setError("Email is required");
                                            }
                                            if (TextUtils.isEmpty(password)) {
                                                mpassword.setError("password is required");
                                            }
                                            if(password.length()<6)
                                            {
                                                mpassword.setError("password must be more than 6 characters");
                                                return;
                                            }
                                            fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                                                        userID=fauth.getCurrentUser().getUid();
                                                        DocumentReference documentReference=firestore.collection("users").document(userID);
                                                        Map<String,Object> user=new HashMap<>();
                                                        user.put("fname",mfullname);
                                                        user.put("email",email);
                                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Log.d(TAG,"User profile is created for"+userID);
                                                            }
                                                        });
                                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(Register.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
        );
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }

}
