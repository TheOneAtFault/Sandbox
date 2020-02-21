package com.ody.sandbox.IntentService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ody.sandbox.MainActivity;
import com.ody.sandbox.R;

public class BobActivity extends AppCompatActivity {
    public static final String FILTER_ACTION_KEY = "any_key";
   // private MyReceiver myReceiver;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bob);

        Button button = findViewById(R.id.btn_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BobActivity.this, AService.class);
                intent.putExtra("message", "I is from the past");
                startService(intent);
                finish();
            }
        });

        textView = findViewById(R.id.textView2);
    }


}
