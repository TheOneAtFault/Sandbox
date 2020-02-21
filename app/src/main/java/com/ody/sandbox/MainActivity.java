package com.ody.sandbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mswipe.paycorptransactlib.TransactionFactory;
import com.ody.sandbox.InputTextToFile.TextToFile;

import java.net.InetAddress;


public class MainActivity extends AppCompatActivity {
    public DisplayManager displayManager = null;
    public Display[] presentationDisplays = null;
    public static final String FILTER_ACTION_KEY = "any_key";
    public Context context;
    private TextView textView;
    private SharedPreferences mPrefs;
    private TransactionFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        textView = findViewById(R.id.tv_external);

        displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        if (displayManager != null) {
            presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
            if (presentationDisplays.length > 0) {
                SecondaryDisplay secondaryDisplay = new SecondaryDisplay(MainActivity.this, presentationDisplays[0]);
                secondaryDisplay.show();
            }
        }
        App.setContext(this);

        Button bob = findViewById(R.id.button2);
        Button progress = findViewById(R.id.btn_progress);
        bob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MainActivity.this, AService.class);
                intent.putExtra("user", "mj@point-of-sale.co.za");
                intent.putExtra("pass", "Odyssey@123");
                startService(intent);
                textView.setText("pending...");*/
                new InterWeebs().execute();
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent progress = new Intent(MainActivity.this, TextToFile.class);
                startActivity(progress);
            }
        });
    }


    public class InterWeebs extends AsyncTask<Void, Void, Boolean> {
        public InterWeebs asyncObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            asyncObject = this;
            new CountDownTimer(3000,3000){
                public void onTick(long timeToFinish){

                }
                public void onFinish(){
                    if (asyncObject.getStatus() == Status.RUNNING){
                        asyncObject.cancel(false);
                    }
                }
            }.start();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(context, "false",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... strings) {
            try {
                InetAddress ipAddress = InetAddress.getByName("google.com");
                return !ipAddress.equals("");
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Toast.makeText(context, aBoolean+"",Toast.LENGTH_SHORT).show();
        }
    }
}
