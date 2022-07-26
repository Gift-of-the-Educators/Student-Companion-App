package com.gote.giftoftheeducators;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static LinearLayout main;
    public static SQLite Papers;
    public static String SQLQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("UserPrefs", this.MODE_PRIVATE);
        initialize(preferences.getString("mode", "light"));

        SQLQuery = "SELECT * from papers";

        MainActivity.this.deleteDatabase("app.db");
        Papers = new SQLite(MainActivity.this, "app");

        InternetRequest databases = new InternetRequest();
        databases.doRequest("https://gote.org.za/GOTE_App/v1/getPastPapers.php", this, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    JSONArray pastPapers = new JSONArray(response);
                    createDatabase(pastPapers);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageView menu = findViewById(R.id.imgMenu);
        Button btnPastPapers = findViewById(R.id.btnPastPapers);
        Button btnAPS = findViewById(R.id.btnAPS);
        Button btnYoutube = findViewById(R.id.btnYoutube);

        ActivityHandler handler = new ActivityHandler(this);
        handler.changeActivity(menu, MainActivity.this, SettingsActivity.class);
        handler.changeActivity(btnPastPapers, MainActivity.this, PastPaperActivity.class);
        handler.changeActivity(btnAPS, MainActivity.this, APSActivity.class);
        handler.changeActivity(btnYoutube, MainActivity.this, YoutubeActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize(preferences.getString("mode", "light"));
    }

    public void initialize(String mode){
        main = findViewById(R.id.MainHomeLayout);
        TextView title = findViewById(R.id.lblTitle);
        TextView subtitle = findViewById(R.id.lblSubtitle);
        CardView navigation = findViewById(R.id.NavigationCard);
        View divider = findViewById(R.id.ConstraintDivider);
        Button pastPapers = findViewById(R.id.btnPastPapers);
        Button recorded = findViewById(R.id.btnRecorded);
        Button youtube = findViewById(R.id.btnYoutube);
        Button aps = findViewById(R.id.btnAPS);
        Button quizzes = findViewById(R.id.btnQuizzes);
        Button calendar = findViewById(R.id.btnCalendar);
        Button forums = findViewById(R.id.btnForums);
        Button wellBeing = findViewById(R.id.btnWellbeing);

        if (mode.equals("light")){
            main.setBackgroundColor(getResources().getColor(R.color.white));
            title.setTextColor(getResources().getColor(R.color.black));
            subtitle.setTextColor(getResources().getColor(R.color.black));
            navigation.setCardBackgroundColor(getResources().getColor(R.color.smokey_white));
            divider.setBackgroundColor(getResources().getColor(R.color.smokey_white));
            pastPapers.setTextColor(getResources().getColor(R.color.black));
            recorded.setTextColor(getResources().getColor(R.color.black));
            youtube.setTextColor(getResources().getColor(R.color.black));
            aps.setTextColor(getResources().getColor(R.color.black));
            quizzes.setTextColor(getResources().getColor(R.color.black));
            calendar.setTextColor(getResources().getColor(R.color.black));
            forums.setTextColor(getResources().getColor(R.color.black));
            wellBeing.setTextColor(getResources().getColor(R.color.black));
        }
        else{
            main.setBackgroundColor(getResources().getColor(R.color.background_black));
            title.setTextColor(getResources().getColor(R.color.white));
            subtitle.setTextColor(getResources().getColor(R.color.white));
            navigation.setCardBackgroundColor(getResources().getColor(R.color.secondary_black));
            divider.setBackgroundColor(getResources().getColor(R.color.secondary_black));
            pastPapers.setTextColor(getResources().getColor(R.color.white));
            recorded.setTextColor(getResources().getColor(R.color.white));
            youtube.setTextColor(getResources().getColor(R.color.white));
            aps.setTextColor(getResources().getColor(R.color.white));
            quizzes.setTextColor(getResources().getColor(R.color.white));
            calendar.setTextColor(getResources().getColor(R.color.white));
            forums.setTextColor(getResources().getColor(R.color.white));
            wellBeing.setTextColor(getResources().getColor(R.color.white));
        }
    }

    public void createDatabase(JSONArray list){
        for(int i = 0; i < list.length(); i++){
            try {
                JSONObject jsonObject = new JSONObject(list.getString(i));
                //String paperID = String.valueOf(jsonObject.getInt("past_paper_id"));
                String paperUrl = jsonObject.getString("past_paper_url");
                String paperSubject= jsonObject.getString("past_paper_subject");
                String paperYear = jsonObject.getString("past_paper_year");
                String paperType = jsonObject.getString("past_paper_type");
                String paperGrade = jsonObject.getString("past_paper_grade");
                String[] values = {paperUrl, paperSubject, paperYear, paperType, paperGrade};
                Papers.doUpdate("Insert into papers(url, subject, year, type, grade) values (?,?,?,?,?);", values);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}