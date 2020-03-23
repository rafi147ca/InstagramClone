package com.rafi.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtUsrNmSgnUp,edtPassSgnUp,edtUsrNmLgn,edtPassLgn;
    private Button btnSignUp,btnLogIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        //Initializing all the view resources
        edtUsrNmSgnUp =findViewById(R.id.edtUsrNmSgnUp);
        edtPassSgnUp =findViewById(R.id.edtPassSgnUp);
        edtUsrNmLgn=findViewById(R.id.edtUsrNmLgn);
        edtPassLgn=findViewById(R.id.edtPassLgn);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogIn=findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(SignUpLoginActivity.this);
        btnLogIn.setOnClickListener(SignUpLoginActivity.this);
    }

    @Override
    public void onClick(View btnViews) {
        switch (btnViews.getId()){
            case R.id.btnLogIn:
                ParseUser.logInInBackground(edtUsrNmLgn.getText().toString(), edtPassLgn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null){
                            FancyToast.makeText(SignUpLoginActivity.this," Login Successfull",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        }
                        else
                            FancyToast.makeText(SignUpLoginActivity.this,"Sign Up failure: "+e.toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                });

                break;
            case R.id.btnSignUp:
                final ParseUser appUser=new ParseUser();
                appUser.setUsername(edtUsrNmSgnUp.getText().toString());
                appUser.setPassword(edtPassSgnUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" Sign Up Successfull",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        }
                        else
                            FancyToast.makeText(SignUpLoginActivity.this,"Sign Up failure: "+e.toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                });
                break;
        }

    }
}
