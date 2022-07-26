package com.gote.giftoftheeducators;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.google.android.youtube.player.YouTubeThumbnailView;

public class YoutubeThumbnailLayout extends LinearLayout {

    TextView videoName;
    YouTubeThumbnailView thumbnail;
    View dividerBottom;

    public YoutubeThumbnailLayout(Context context, int width) {
        super(context);

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        videoName = new TextView(context);
        thumbnail = new YouTubeThumbnailView(context);
        dividerBottom = new View(context);

        int height = (int) (width * 0.5625);
        LinearLayout.LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams thumbnailParams = new LayoutParams(width, height);
        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 8);

        SharedPreferences preferences = context.getSharedPreferences("UserPrefs", context.MODE_PRIVATE);
        initialize(preferences.getString("mode", "light"), context);

        addView(thumbnail, thumbnailParams);
        addView(videoName, textParams);
        addView(dividerBottom, dividerParams);
    }

    public void initialize(String mode, Context context){
        videoName.setTypeface(ResourcesCompat.getFont(context, R.font.righteous));
        videoName.setTextSize(18);
        videoName.setPadding(8,8, 0, 8);

        thumbnail.setScaleType(ImageView.ScaleType.FIT_XY);

        dividerBottom.setPadding(0,0, 0, 8);

        if (mode.equals("light")){
            setBackgroundColor(getResources().getColor(R.color.white));
            videoName.setTextColor(getResources().getColor(R.color.black));
            dividerBottom.setBackgroundColor(getResources().getColor(R.color.smokey_white));
        }
        else{
            setBackgroundColor(getResources().getColor(R.color.background_black));
            videoName.setTextColor(getResources().getColor(R.color.white));
            dividerBottom.setBackgroundColor(getResources().getColor(R.color.secondary_black));
        }
    }
}
