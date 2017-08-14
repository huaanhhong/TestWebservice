package com.example.huaanhhong.testwebservice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity  {



    private Button mBtnLogin,mBtnSignup;
    private EditText mEdtemail,mEdtPass;
    private final String SERVER_URL="http://anhhong.somee.com/";
    private final String URL_REGISRTER="http://anhhong.somee.com/api/Account/Register";
    private final String URL_LOGIN="http://anhhong.somee.com/api/Account/LoginUser";

    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnLogin=(Button) findViewById(R.id.btn_sign_in_button);
        mBtnSignup=(Button) findViewById(R.id.btn_sign_up_button);
        mEdtemail=(EditText) findViewById(R.id.edt_email);
        mEdtPass=(EditText) findViewById(R.id.edt_password);

        signup();
        login();

    }

    private void login() {
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }

    private void signup() {

        mBtnSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //lay doi tuong dang ki
//                    User user=new User(mEdtemail.getText().toString(),mEdtPass.getText().toString(),mEdtPass.getText().toString());
                    User user=new User("anhhong@gmail.com","_aA123456","_aA123456");
                    Gson gs=new Gson();
                    String js=gs.toJson(user);
                    new register().execute(URL_REGISRTER,js);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class register extends AsyncTask<String,Void,Boolean>{

        ProgressDialog progressdialog=new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressdialog.setMessage("dang dang ki");
            progressdialog.setCancelable(false);
            progressdialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            boolean result=false;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(60000);
                connection.setReadTimeout(60000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json");
                String json=strings[1];
                OutputStream os=connection.getOutputStream();
                os.write(json.getBytes());
                os.close();
                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    result=true;

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressdialog.dismiss();
            if(aBoolean){
                Toast.makeText(LoginActivity.this,"dang ki thanh cong",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(LoginActivity.this,"co loi xay ra",Toast.LENGTH_LONG).show();
            }
        }
    }

}

