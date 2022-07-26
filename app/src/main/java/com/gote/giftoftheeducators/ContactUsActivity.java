package com.gote.giftoftheeducators;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        initialize("light");
    }

    private void initialize(String mode){
        String[] reasons = new String[] {"  -- Select an Email Subject --", "   Apply for Free Tuition", "   Apply for Position at GoTE", "   Other"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.light_spinner_item, reasons);
        Spinner subject = findViewById(R.id.spinner5);
        subject.setAdapter(adapter);
        subject.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
        subject.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
    }
}
