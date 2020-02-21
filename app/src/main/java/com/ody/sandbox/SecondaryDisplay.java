package com.ody.sandbox;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;

public class SecondaryDisplay extends Presentation {
    public SecondaryDisplay(Context context, Display display){
        super(context,display);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_display);
    }
}
