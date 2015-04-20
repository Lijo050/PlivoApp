package com.example.lijo.plivoapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    private Button callBtn;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (EditText) findViewById(R.id.phoneNumber);
        callBtn = (Button) findViewById(R.id.call);


        callBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String from = number.getText().toString();
                new APICall().execute(new String[]{from});
            }
        });


    }

    class APICall extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            return PlivoAPIClient.executePostCall(params[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}


