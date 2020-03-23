package com.rafi.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private Button createOject,btnGetAll,btnSwitchActvty;
    private EditText edName,edPuchSpeed,edPunchPower,edKickSpeed,edKickPower;
    private TextView txtGetData;
    String allBoxers="";
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

        txtGetData= findViewById(R.id.txtGetData);
        btnGetAll=findViewById(R.id.btnGetAll);
        btnSwitchActvty=findViewById(R.id.btnSwitchActvty);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery= ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("3yzxSAaHOD", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null){
                            txtGetData.setText(object.get("name").toString());
                        }
                    }
                });
            }
        });
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBoxers="";
                ParseQuery<ParseObject> queryAll= ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size()>0){
                                for (ParseObject parseObject: objects){
                                    allBoxers=allBoxers+parseObject.get("name").toString()+"\n";
                                }
                            }
                        }
                        FancyToast.makeText(SignUp.this,allBoxers+"",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    }
                });
            }
        });

        //Swith to Next Activity
        btnSwitchActvty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,SignUpLoginActivity.class);
                startActivity(intent);

            }
        });

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
