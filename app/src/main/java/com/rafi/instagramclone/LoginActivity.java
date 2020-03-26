package com.rafi.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEmlLoginActivity,edtPwdLoginActivity;
    private Button btnLoginActivity,btnSingUpLoginActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        edtEmlLoginActivity=findViewById(R.id.edtEmlLoginActivity);
        edtPwdLoginActivity=findViewById(R.id.edtPwdLoginActivity);

        btnLoginActivity=findViewById(R.id.btnLoginActivity);
        btnSingUpLoginActivity=findViewById(R.id.btnSingUpLoginActivity);

        btnLoginActivity.setOnClickListener(LoginActivity.this);
        btnSingUpLoginActivity.setOnClickListener(LoginActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginActivity:
                if(edtEmlLoginActivity.getText().equals("") || edtPwdLoginActivity.getText().equals("")) {
                    FancyToast.makeText(LoginActivity.this,"EmailId/Password is null",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }
                else{
                    final ParseUser parseUser = new ParseUser();
                    final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage("Loging in  ");
                    pd.show();
                    parseUser.logInInBackground(edtEmlLoginActivity.getText().toString(), edtPwdLoginActivity.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " Logged In Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                edtEmlLoginActivity.getText().clear();
                                edtPwdLoginActivity.getText().clear();
                                transitionToSociaMediaActivity();
                            } else
                                FancyToast.makeText(LoginActivity.this, "Failure message" + e.toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                    });
                }
                break;
            case R.id.btnSingUpLoginActivity:
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;

        }
    }
    public void loginConstntTapped(View v){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSociaMediaActivity(){
        Intent intent=new Intent(LoginActivity.this,SocialMediaActivty.class);
        startActivity(intent);
    }
}
