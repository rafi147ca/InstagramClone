package com.rafi.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEnterEmailMain,edtUserNameMain,edtPasswordMain;
    private Button btnSignUpMain,btnLoginMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign Up");
        //EditText Initialization
        edtEnterEmailMain=findViewById(R.id.edtEnterEmailMain);
        edtUserNameMain=findViewById(R.id.edtUserNameMain);
        edtPasswordMain=findViewById(R.id.edtPasswordMain);
        edtPasswordMain.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    Log.i("mytag","in the onkey method");
                    FancyToast.makeText(SignUp.this,"IN the onKey method",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                    onClick(btnSignUpMain);
                }
                return false;
            }
        });


        //Button Initialization
        btnSignUpMain=findViewById(R.id.btnSignUpMain);
        btnLoginMain=findViewById(R.id.btnLoginMain);

        btnSignUpMain.setOnClickListener(SignUp.this);
        btnLoginMain.setOnClickListener(SignUp.this);
        if(ParseUser.getCurrentUser()!=null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSociaMediaActivity();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUpMain:
                if(edtEnterEmailMain.getText().toString().equals("") ||
                        edtUserNameMain.getText().toString().equals("")||
                        edtPasswordMain.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,"EmailId, UserName, Password is null",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                }
                else {
                    ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEnterEmailMain.getText().toString());
                    appUser.setUsername(edtUserNameMain.getText().toString());
                    appUser.setPassword(edtPasswordMain.getText().toString());
                    final ProgressDialog pd = new ProgressDialog(SignUp.this);
                    pd.setMessage("Signing Up " + edtUserNameMain.getText().toString());
                    pd.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, edtUserNameMain.getText() + " Signed Up Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                edtEnterEmailMain.getText().clear();
                                edtUserNameMain.getText().clear();
                                edtPasswordMain.getText().clear();
                                transitionToSociaMediaActivity();
                            } else
                                FancyToast.makeText(SignUp.this, "Failure message" + e.toString(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            pd.dismiss();
                        }
                    });

                }
                break;
            case R.id.btnLoginMain:
                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
                break;

        }

    }
    public void rootLayoutTapped(View v){
        try {
            InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSociaMediaActivity(){
        Intent intent=new Intent(SignUp.this,SocialMediaActivty.class);
        startActivity(intent);
    }

}
