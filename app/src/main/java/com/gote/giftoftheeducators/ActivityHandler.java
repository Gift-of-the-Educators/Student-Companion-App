package com.gote.giftoftheeducators;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityHandler extends View {


    public ActivityHandler(Context context) {
        super(context);
    }

    public void changeActivity(View view, Context currentContext, Class newContext){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentContext, newContext);
                getContext().startActivity(intent);
            }
        });
    }
}
