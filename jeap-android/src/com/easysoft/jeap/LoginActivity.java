package com.easysoft.jeap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void login(View view){
        EditText usernameText = (EditText)findViewById(R.id.username);
        if(usernameText.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "请输入账号!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            return;
        }
        EditText passwordText = (EditText)findViewById(R.id.password);
        if(passwordText.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "请输入密码!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            return;
        }

        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        this.startActivity(intent);
    }
}
