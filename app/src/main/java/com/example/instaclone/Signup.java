package com.example.instaclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etLogname;
    private EditText etLogpassword;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etLogname = findViewById(R.id.etLogname);
        etLogpassword = findViewById(R.id.etLogpassword);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Clicked signup");
                String usename = etLogname.getText().toString();
                String pwd = etLogpassword.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(usename);
                user.setPassword(pwd);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(Signup.this, "You are registered " + usename, Toast.LENGTH_SHORT).show();
                            goLogin();
                        } else {
                            Log.e(TAG, "Issue with sign up", e);
                            return;
                        }
                    }
                });
            }
        });
    }

    private void goLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}