package com.example.handymandemop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

EditText edtVerifyOTP;
Button btnVerifyOTp;
FirebaseAuth mAuth;
ProgressBar progressBar;
String number;
String verificationid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        
        edtVerifyOTP=findViewById(R.id.edtVerifyOTP);
        btnVerifyOTp=findViewById(R.id.btnVerifyOTP);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar2);
        
        Intent intent=getIntent();
        number=intent.getStringExtra("phoneNo");

        sendVerificationCode(number);
        
        btnVerifyOTp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String code=edtVerifyOTP.getText().toString();
                if (code.isEmpty() || code.length()<6)
                {
                    edtVerifyOTP.setError("Enter code...");
                }
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationid,code);
        signWithCredential(credential);
    }

    private void signWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent=new Intent(VerifyOTP.this,SignUP.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(VerifyOTP.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void sendVerificationCode(String number)
    {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                VerifyOTP.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationid=s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                        String code=phoneAuthCredential.getSmsCode();
                        edtVerifyOTP.setText(code);
                        verifyCode(code);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}











  /*      edtVerifyOTP=findViewById(R.id.edtVerifyOTP);
        btnVerify=findViewById(R.id.btnVerifyOTP);
        progressBar=findViewById(R.id.progressBar2);

        mAuth=FirebaseAuth.getInstance();


        Intent intent=getIntent();
        number=intent.getStringExtra("phoneNo");

        sendVerificationCode(number);


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=edtVerifyOTP.getText().toString();
                if(code.isEmpty() || code.length()<6);
                {
                    edtVerifyOTP.setError("Enter code...");
                }
                verifyCode(code);
            }

        });
    }

    private void verifyCode(String code) {

        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationid,code);
        signINWithCredential(credential);
    }

    private void signINWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent1=new Intent(VerifyOTP.this,SignUP.class);
                    startActivity(intent1);
                }else {
                    Toast.makeText(VerifyOTP.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendVerificationCode(String number)
    {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                VerifyOTP.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationid=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code=phoneAuthCredential.getSmsCode();
                        edtVerifyOTP.setText(code);
                        verifyCode(code);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}*/
