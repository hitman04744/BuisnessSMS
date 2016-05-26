package com.example.bohdan.sms_sender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SMSActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTemplateName;
    TextView mTemplateText;
    EditText mEditTemplate;
    EditText mEditTemplateName;
    Button mButtonSave;
    Button mButtonPreview;
    String sms_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        mEditTemplate = (EditText) findViewById(R.id.editText_SMS);
        mEditTemplateName = (EditText) findViewById(R.id.editText_NAME_SMS);
        mButtonSave = (Button) findViewById(R.id.button_Save);
        mButtonPreview = (Button) findViewById(R.id.button_Preview);
        mTemplateName = (TextView) findViewById(R.id.textView_Name_SMS) ;
        mTemplateText = (TextView) findViewById(R.id.textView_SMSTExt);
        mButtonSave.setOnClickListener(this);
        mButtonPreview.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.button_Save):
                break;
            case (R.id.button_Preview):
//                Toast.makeText(this,"LOLO",Toast.LENGTH_SHORT).show();
                sms_Text = mEditTemplate.getText().toString();
                Log.d("MY",sms_Text);
                break;
        }
    }
}
