package com.example.bohdan.sms_sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button runSMS_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runSMS_Button = (Button) findViewById(R.id.button_Run);
        runSMS_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.button_Run):
                Intent i = new Intent(this,SMSActivity.class);
                startActivity(i);
                break;
        }
    }
}
