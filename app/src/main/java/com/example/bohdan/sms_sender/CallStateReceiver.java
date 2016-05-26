package com.example.bohdan.sms_sender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class CallStateReceiver extends BroadcastReceiver {
    private  boolean incomingCall = false;
    private String phoneState;
    private WindowManager windowManager;
    private ViewGroup windowLayout;
    private Class c;
    private Method m;
//    private com.android.internal.telephony.ITelephony telephonyService;
    private Intent intent;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")){
            phoneState= intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context,"RING",Toast.LENGTH_SHORT).show();
//                showWindow(context, phoneNumber);


            } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

                if (incomingCall) {
                    Intent i = new Intent(context,MainActivity.class);
                    i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                    Toast.makeText(context,"offhook",Toast.LENGTH_SHORT).show();

                    incomingCall = false;
                }
            } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Intent i = new Intent(context,MainActivity.class);
                i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                if (incomingCall) {
                    Toast.makeText(context,"lol",Toast.LENGTH_SHORT).show();
                   context.startActivity(new Intent(context,MainActivity.class));
                    incomingCall = false;
                }
            }
        }
    }


    /*private void showWindow(final Context context, String phone) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP;

        windowLayout = (ViewGroup) layoutInflater.inflate(R.layout.call_screen, null);

        TextView textViewNumber=(TextView) windowLayout.findViewById(R.id.textViewNumber);
        Button buttonClose=(Button) windowLayout.findViewById(R.id.btn_close);
        Button buttonAccept=(Button) windowLayout.findViewById(R.id.btn_accept);
        Button buttonDecline=(Button) windowLayout.findViewById(R.id.btn_decline);

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
                buttonUp.putExtra(Intent.EXTRA_KEY_EVENT,
                        new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
                context.sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");

            }
        });
        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    endCall(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        textViewNumber.setText(phone);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeWindow();
            }
        });

        windowManager.addView(windowLayout, params);
    }*/

    private void closeWindow() {
        if (windowLayout !=null){
            windowManager.removeView(windowLayout);
            windowLayout =null;
        }
    }
    public void endCall (Context context)throws Exception{
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Class c = Class.forName(tm.getClass().getName());
        Method m = c.getDeclaredMethod("getITelephony");
        m.setAccessible(true);
        Object telephonyService = m.invoke(tm); // Get the internal ITelephony object
        c = Class.forName(telephonyService.getClass().getName()); // Get its class
        m = c.getDeclaredMethod("endCall"); // Get the "endCall()" method
        m.setAccessible(true); // Make it accessible
        m.invoke(telephonyService); // invoke endCall()
        closeWindow();
    }
}
