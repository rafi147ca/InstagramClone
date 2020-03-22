package com.rafi.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private Button createOject;
    private EditText edName,edPuchSpeed,edPunchPower,edKickSpeed,edKickPower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EditText Initialization
        edName=findViewById(R.id.edName);
        edPuchSpeed=findViewById(R.id.edPuchSpeed);
        edPunchPower=findViewById(R.id.edPunchPower);
        edKickSpeed=findViewById(R.id.edKickSpeed);
        edKickPower=findViewById(R.id.edKickPower);

        createOject= findViewById(R.id.createObject);
        createOject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    ParseObject kickBoxer = new ParseObject("KickBoxer");
                    kickBoxer.put("name", edName.getText().toString());
                    kickBoxer.put("punchSpeed", Integer.parseInt(edPuchSpeed.getText().toString()));
                    kickBoxer.put("punchPower", Integer.parseInt(edPunchPower.getText().toString()));
                    kickBoxer.put("kickSpeed", Integer.parseInt(edKickSpeed.getText().toString()));
                    kickBoxer.put("kickPower", Integer.parseInt(edKickPower.getText().toString()));
                    kickBoxer.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //Toast.makeText(SignUp.this, edName.getText() + " Saved Successfully", Toast.LENGTH_LONG).show();
                                FancyToast.makeText(SignUp.this,edName.getText() + " Saved Successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            } else{
                                //Toast.makeText(SignUp.this, "Failure message" + e.toString(), Toast.LENGTH_LONG).show();
                                FancyToast.makeText(SignUp.this,"Failure message" + e.toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }

                        }
                    });
                }
                catch (Exception e) {
                    //Toast.makeText(SignUp.this, "Failure message" + e.toString(), Toast.LENGTH_LONG).show();
                    FancyToast.makeText(SignUp.this,"Failure message" + e.toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }
        });
    }

}
