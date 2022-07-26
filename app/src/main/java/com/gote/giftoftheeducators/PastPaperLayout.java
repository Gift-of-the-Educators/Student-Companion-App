package com.gote.giftoftheeducators;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class PastPaperLayout extends LinearLayout {
    SharedPreferences preferences;
    View SubjectDivider;
    LinearLayout TextLayout;
    TextView PaperName;
    TextView PaperType;

    public PastPaperLayout(Context context, String subject) {
        super(context);
        setOrientation(HORIZONTAL);
        preferences = context.getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE);
        String mode = preferences.getString("mode", "light");
        LayoutParams dividerParams = new LayoutParams(10, 150);
        dividerParams.setMargins(16, 4,0,4);
        LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        SubjectDivider = new View(context);
        TextLayout = new LinearLayout(context);
        PaperName = new TextView(context);
        PaperType = new TextView(context);
        PaperName.setTextColor(getResources().getColor(R.color.red_001));
        PaperName.setTypeface(ResourcesCompat.getFont(context, R.font.righteous));
        PaperName.setTextSize(18);
        PaperType.setTypeface(ResourcesCompat.getFont(context, R.font.open_sans_semibold));
        PaperName.setPadding(16,16,0,0);
        PaperType.setPadding(16,8,8,8);
        TextLayout.setOrientation(VERTICAL);
        if(mode.equals("light")){
            PaperType.setTextColor(Color.BLACK);
            setBackgroundColor(getResources().getColor(R.color.white));
        }
        else{
            PaperType.setTextColor(Color.WHITE);
            setBackgroundColor(getResources().getColor(R.color.background_black));
        }
        PaperType.setTextSize(14);
        if(subject.equals("Physical Science")){
            SubjectDivider.setBackgroundColor(getResources().getColor(R.color.red_001));
        }
        else if(subject.equals("Mathematics")){
            SubjectDivider.setBackgroundColor(getResources().getColor(R.color.red_002));
        }
        else{
            SubjectDivider.setBackgroundColor(getResources().getColor(R.color.red_003));
        }
        addView(SubjectDivider, dividerParams);
        TextLayout.addView(PaperName,textParams);
        TextLayout.addView(PaperType,textParams);
        addView(TextLayout, layoutParams);
    }
}
