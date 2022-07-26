package com.gote.giftoftheeducators;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActivityHandler handler = new ActivityHandler(this);
        preferences = getSharedPreferences("UserPrefs", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        initialize(preferences.getString("mode", "light"));

        Switch themeMode = findViewById(R.id.switchThemeMode);
        ImageView aboutUs = findViewById(R.id.imgAboutUs);
        ImageView contactUs = findViewById(R.id.imgContactUs);

        handler.changeActivity(contactUs, SettingsActivity.this, ContactUsActivity.class);
        handler.changeActivity(aboutUs, SettingsActivity.this, AboutUsActivity.class);

        themeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(themeMode.isChecked()){
                    editor.putString("mode", "dark");
                }
                else{
                    editor.putString("mode", "light");
                }
                editor.commit();
                initialize(preferences.getString("mode", "light"));
            }
        });
    }

    public void initialize(String mode){
        ConstraintLayout main = findViewById(R.id.MainSettingsLayout);

        CardView overview = findViewById(R.id.OverviewCard);
        CardView details = findViewById(R.id.DetailsCard);
        CardView settings = findViewById(R.id.SettingsCard);

        TextView overviewTitle = findViewById(R.id.lblOverview);
        TextView name = findViewById(R.id.lblOverviewName);
        TextView grade = findViewById(R.id.lblOverviewGrade);
        TextView account = findViewById(R.id.lblOverviewAccount);
        TextView email = findViewById(R.id.lblOverviewEmail);
        TextView detailsTitle = findViewById(R.id.lblDetails);
        TextView viewAccount = findViewById(R.id.lblViewAccount);
        TextView editAccount = findViewById(R.id.lblEditAccount);
        TextView viewBills = findViewById(R.id.lblBillingInformation);
        TextView editBills = findViewById(R.id.lblEditBillingInformation);
        TextView settingsTitle = findViewById(R.id.lblSettings);
        TextView theme = findViewById(R.id.lblThemeMode);
        TextView FAQ = findViewById(R.id.lblFAQ);
        TextView aboutUs = findViewById(R.id.lblAboutUs);
        TextView contactUs = findViewById(R.id.lblContactUs);

        Switch themeMode = findViewById(R.id.switchThemeMode);

        if(mode.equals("light")){
            main.setBackgroundColor(getResources().getColor(R.color.white));

            overview.setCardBackgroundColor(getResources().getColor(R.color.smokey_white));
            details.setCardBackgroundColor(getResources().getColor(R.color.smokey_white));
            settings.setCardBackgroundColor(getResources().getColor(R.color.smokey_white));

            overviewTitle.setTextColor(getResources().getColor(R.color.black));
            name.setTextColor(getResources().getColor(R.color.black));
            grade.setTextColor(getResources().getColor(R.color.black));
            account.setTextColor(getResources().getColor(R.color.black));
            email.setTextColor(getResources().getColor(R.color.black));
            detailsTitle.setTextColor(getResources().getColor(R.color.black));
            viewAccount.setTextColor(getResources().getColor(R.color.black));
            editAccount.setTextColor(getResources().getColor(R.color.black));
            viewBills.setTextColor(getResources().getColor(R.color.black));
            editBills.setTextColor(getResources().getColor(R.color.black));
            settingsTitle.setTextColor(getResources().getColor(R.color.black));
            theme.setTextColor(getResources().getColor(R.color.black));
            FAQ.setTextColor(getResources().getColor(R.color.black));
            aboutUs.setTextColor(getResources().getColor(R.color.black));
            contactUs.setTextColor(getResources().getColor(R.color.black));

            themeMode.setChecked(false);
            themeMode.setTrackDrawable(getResources().getDrawable(R.drawable.track_light));
        }
        else{
            main.setBackgroundColor(getResources().getColor(R.color.background_black));

            overview.setCardBackgroundColor(getResources().getColor(R.color.secondary_black));
            details.setCardBackgroundColor(getResources().getColor(R.color.secondary_black));
            settings.setCardBackgroundColor(getResources().getColor(R.color.secondary_black));

            overviewTitle.setTextColor(getResources().getColor(R.color.white));
            name.setTextColor(getResources().getColor(R.color.white));
            grade.setTextColor(getResources().getColor(R.color.white));
            account.setTextColor(getResources().getColor(R.color.white));
            email.setTextColor(getResources().getColor(R.color.white));
            detailsTitle.setTextColor(getResources().getColor(R.color.white));
            viewAccount.setTextColor(getResources().getColor(R.color.white));
            editAccount.setTextColor(getResources().getColor(R.color.white));
            viewBills.setTextColor(getResources().getColor(R.color.white));
            editBills.setTextColor(getResources().getColor(R.color.white));
            settingsTitle.setTextColor(getResources().getColor(R.color.white));
            theme.setTextColor(getResources().getColor(R.color.white));
            FAQ.setTextColor(getResources().getColor(R.color.white));
            aboutUs.setTextColor(getResources().getColor(R.color.white));
            contactUs.setTextColor(getResources().getColor(R.color.white));

            themeMode.setChecked(true);
            themeMode.setTrackDrawable(getResources().getDrawable(R.drawable.track_dark));
        }
    }
}
