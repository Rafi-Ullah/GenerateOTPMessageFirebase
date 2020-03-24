package com.example.handymandemop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {


EditText edtEnterpHoneNO;
Button btnGenerateOTP;
CountryCodePicker countryCodePicker;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtEnterpHoneNO=findViewById(R.id.edtEnterPN);
        btnGenerateOTP=findViewById(R.id.btnGenerateOTP);
        countryCodePicker=findViewById(R.id.countryCodePicker);

        mAuth=FirebaseAuth.getInstance();

        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PHoneNUm="+" +countryCodePicker.getSelectedCountryCode() +edtEnterpHoneNO.getText().toString();

                if (PHoneNUm.isEmpty() || PHoneNUm.length()<10)
                {
                    edtEnterpHoneNO.setError("please proper phone number");
                    return;
                }else {


                    Intent intent = new Intent(MainActivity.this, VerifyOTP.class);
                    intent.putExtra("phoneNo", PHoneNUm);
                    startActivity(intent);
                }

            }
        });
    }
}






















      /*  edtEnterPhoneNumber=findViewById(R.id.edtEnterPN);
        btnGenerateOTP=findViewById(R.id.btnGenerateOTP);
        countryCodePicker=findViewById(R.id.countryCodePicker);

        mAuth= FirebaseAuth.getInstance();

        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNO="+" +countryCodePicker.getSelectedCountryCode()+edtEnterPhoneNumber.getText().toString();

                if (phoneNO.isEmpty() || phoneNO.length()<10)
                {
                    edtEnterPhoneNumber.setError("Please Enter Phone Number");
                    return;
                }else {

                    Intent intent = new Intent(MainActivity.this, VerifyOTP.class);
                    intent.putExtra("phoneNo", phoneNO);
                    startActivity(intent);
                }
            }
        });
    }
}
*/
/*

    EditText edtEnterPhoneNumber;
    Button btnGenerateOTP;
    CountryCodePicker countryCodePicker;
    FirebaseAuth mAuth;*/
