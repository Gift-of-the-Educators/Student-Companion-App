package com.gote.giftoftheeducators;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class PastPaperActivity extends AppCompatActivity {
    public SharedPreferences preferences;
    View divider;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_papers);
        Spinner grade = findViewById(R.id.spinner);
        Spinner subject = findViewById(R.id.spinner3);
        Spinner year = findViewById(R.id.spinner2);
        query = MainActivity.SQLQuery + " where grade LIKE '%' and subject LIKE '%' and year LIKE '%'";

        preferences = getSharedPreferences("UserPrefs", this.MODE_PRIVATE);
        initialize(preferences.getString("mode","light"));
        createList(preferences.getString("mode","light"));

        grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(query.contains("where grade LIKE '10'")){
                            query = query.replace("where grade LIKE '10'","where grade LIKE '%'");
                        }
                        else if(query.contains("where grade LIKE '11'")){
                            query = query.replace("where grade LIKE '11'","where grade LIKE '%'");
                        }
                        else if(query.contains("where grade LIKE '12'")){
                            query = query.replace("where grade LIKE '12'","where grade LIKE '%'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 1:
                        if(query.contains("where grade LIKE '%'")){
                            query = query.replace("where grade LIKE '%'","where grade LIKE '10'");
                        }
                        else if(query.contains("where grade LIKE '11'")){
                            query = query.replace("where grade LIKE '11'","where grade LIKE '10'");
                        }
                        else if(query.contains("where grade LIKE '12'")){
                            query = query.replace("where grade LIKE '12'","where grade LIKE '10'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 2:
                        if(query.contains("where grade LIKE '%'")){
                            query = query.replace("where grade LIKE '%'","where grade LIKE '11'");
                        }
                        else if(query.contains("where grade LIKE '10'")){
                            query = query.replace("where grade LIKE '10'","where grade LIKE '11'");
                        }
                        else if(query.contains("where grade LIKE '12'")){
                            query = query.replace("where grade LIKE '12'","where grade LIKE '11'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 3:
                        if(query.contains("where grade LIKE '%'")){
                            query = query.replace("where grade LIKE '%'","where grade LIKE '12'");
                        }
                        else if(query.contains("where grade LIKE '10'")){
                            query = query.replace("where grade LIKE '10'","where grade LIKE '12'");
                        }
                        else if(query.contains("where grade LIKE '11'")){
                            query = query.replace("where grade LIKE '11'","where grade LIKE '12'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(query.contains("and subject LIKE 'Mathematics'")){
                            query = query.replace("and subject LIKE 'Mathematics'","and subject LIKE '%'");
                        }
                        else if(query.contains("and subject LIKE 'Physical Science'")){
                            query = query.replace("and subject LIKE 'Physical Science'","and subject LIKE '%'");
                        }
                        else if(query.contains("and subject LIKE 'Accounting'")){
                            query = query.replace("and subject LIKE 'Accounting'","and subject LIKE '%'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 1:
                        if(query.contains("and subject LIKE '%'")){
                            query = query.replace("and subject LIKE '%'","and subject LIKE 'Mathematics'");
                        }
                        else if(query.contains("and subject LIKE 'Physical Science'")){
                            query = query.replace("and subject LIKE 'Physical Science'","and subject LIKE 'Mathematics'");
                        }
                        else if(query.contains("and subject LIKE 'Accounting'")){
                            query = query.replace("and subject LIKE 'Accounting'","and subject LIKE 'Mathematics'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 2:
                        if(query.contains("and subject LIKE '%'")){
                            query = query.replace("and subject LIKE '%'","and subject LIKE 'Physical Science'");
                        }
                        else if(query.contains("and subject LIKE 'Mathematics'")){
                            query = query.replace("and subject LIKE 'Mathematics'","and subject LIKE 'Physical Science'");
                        }
                        else if(query.contains("and subject LIKE 'Accounting'")){
                            query = query.replace("and subject LIKE 'Accounting'","and subject LIKE 'Physical Science'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 3:
                        if(query.contains("and subject LIKE '%'")){
                            query = query.replace("and subject LIKE '%'","and subject LIKE 'Accounting'");
                        }
                        else if(query.contains("and subject LIKE 'Mathematics'")){
                            query = query.replace("and subject LIKE 'Mathematics'","and subject LIKE 'Accounting'");
                        }
                        else if(query.contains("and subject LIKE 'Physical Science'")){
                            query = query.replace("and subject LIKE 'Physical Science'","and subject LIKE 'Accounting'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(query.contains("and year LIKE '2017'")){
                            query = query.replace("and year LIKE '2017'","and year LIKE '%'");
                        }
                        else if(query.contains("and year LIKE '2018'")){
                            query = query.replace("and year LIKE '2018'","and year LIKE '%'");
                        }
                        else if(query.contains("and year LIKE '2019'")){
                            query = query.replace("and year LIKE '2019'","and year LIKE '%'");
                        }
                        else if(query.contains("and year LIKE '2020'")){
                            query = query.replace("and year LIKE '2020'","and year LIKE '%'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 1:
                        if(query.contains("and year LIKE '%'")){
                            query = query.replace("and year LIKE '%'","and year LIKE '2017'");
                        }
                        else if(query.contains("and year LIKE '2018'")){
                            query = query.replace("and year LIKE '2018'","and year LIKE '2017'");
                        }
                        else if(query.contains("and year LIKE '2019'")){
                            query = query.replace("and year LIKE '2019'","and year LIKE '2017'");
                        }
                        else if(query.contains("and year LIKE '2020'")){
                            query = query.replace("and year LIKE '2020'","and year LIKE '2017'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 2:
                        if(query.contains("and year LIKE '%'")){
                            query = query.replace("and year LIKE '%'","and year LIKE '2018'");
                        }
                        else if(query.contains("and year LIKE '2017'")){
                            query = query.replace("and year LIKE '2017'","and year LIKE '2018'");
                        }
                        else if(query.contains("and year LIKE '2019'")){
                            query = query.replace("and year LIKE '2019'","and year LIKE '2018'");
                        }
                        else if(query.contains("and year LIKE '2020'")){
                            query = query.replace("and year LIKE '2020'","and year LIKE '2018'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 3:
                        if(query.contains("and year LIKE '%'")){
                            query = query.replace("and year LIKE '%'","and year LIKE '2019'");
                        }
                        else if(query.contains("and year LIKE '2017'")){
                            query = query.replace("and year LIKE '2017'","and year LIKE '2019'");
                        }
                        else if(query.contains("and year LIKE '2018'")){
                            query = query.replace("and year LIKE '2018'","and year LIKE '2019'");
                        }
                        else if(query.contains("and year LIKE '2020'")){
                            query = query.replace("and year LIKE '2020'","and year LIKE '2019'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                    case 4:
                        if(query.contains("and year LIKE '%'")){
                            query = query.replace("and year LIKE '%'","and year LIKE '2020'");
                        }
                        else if(query.contains("and year LIKE '2017'")){
                            query = query.replace("and year LIKE '2017'","and year LIKE '2020'");
                        }
                        else if(query.contains("and year LIKE '2018'")){
                            query = query.replace("and year LIKE '2018'","and year LIKE '2020'");
                        }
                        else if(query.contains("and year LIKE '2019'")){
                            query = query.replace("and year LIKE '2019'","and year LIKE '2020'");
                        }
                        createList(preferences.getString("mode","light"));
                        return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initialize(String mode) {
        ConstraintLayout main = findViewById(R.id.MainPastPaperLayout);
        Spinner grade = findViewById(R.id.spinner);
        Spinner subject = findViewById(R.id.spinner3);
        Spinner year = findViewById(R.id.spinner2);
        String[] grades = {" Grade "," Grade 10 ", " Grade 11 ", " Grade 12 "};
        String[] subjects = {" Subject ", " Mathematics ", " Physics ", " Accounting "};
        String[] years = {" Year "," 2017 ", " 2018 ", " 2019 "," 2020 "};

        if(mode.equals("light")){
            main.setBackgroundColor(getResources().getColor(R.color.white));
            grade.setPopupBackgroundDrawable(getResources().getDrawable(R.color.smokey_white));
            subject.setPopupBackgroundDrawable(getResources().getDrawable(R.color.smokey_white));
            year.setPopupBackgroundDrawable(getResources().getDrawable(R.color.smokey_white));
            grade.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            subject.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            year.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(this, R.layout.light_spinner_item, grades);
            ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this, R.layout.light_spinner_item, subjects);
            ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, R.layout.light_spinner_item, years);
            grade.setAdapter(gradeAdapter);
            subject.setAdapter(subjectAdapter);
            year.setAdapter(yearAdapter);
        }
        else{
            main.setBackgroundColor(getResources().getColor(R.color.background_black));
            grade.setPopupBackgroundDrawable(getResources().getDrawable(R.color.secondary_black));
            subject.setPopupBackgroundDrawable(getResources().getDrawable(R.color.secondary_black));
            year.setPopupBackgroundDrawable(getResources().getDrawable(R.color.secondary_black));
            grade.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            subject.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            year.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(this, R.layout.dark_spinner_item, grades);
            ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this, R.layout.dark_spinner_item, subjects);
            ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, R.layout.dark_spinner_item, years);
            grade.setAdapter(gradeAdapter);
            subject.setAdapter(subjectAdapter);
            year.setAdapter(yearAdapter);
        }
    }


    public void openPDF(String url){
        Intent openPDFViewer = new Intent(this, PDFViewerActivity.class);
        openPDFViewer.putExtra("PDFUrl",url);
        startActivity(openPDFViewer);
    }

    public void createList(String mode){
        LinearLayout pastPaperList = findViewById(R.id.PastPaperLayout);
        pastPaperList.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160);
        Cursor cursor = MainActivity.Papers.doQuery(query);
        while(cursor.moveToNext()){
            divider = new View(this);
            if(mode.equals("light")){
                divider.setBackground(ContextCompat.getDrawable(this, R.color.smokey_white));
            }
            else{
                divider.setBackground(ContextCompat.getDrawable(this, R.color.secondary_black));
            }
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(MainActivity.main.getWidth(), 4);
            String subjectType = cursor.getString(cursor.getColumnIndex("type"));
            String subjectName = cursor.getString(cursor.getColumnIndex("grade")) + " " + cursor.getString(cursor.getColumnIndex("year")) + " " + cursor.getString(cursor.getColumnIndex("subject"));
            PastPaperLayout paperLayout = new PastPaperLayout(PastPaperActivity.this,  cursor.getString(cursor.getColumnIndex("subject")));
            paperLayout.PaperName.setText(subjectName);
            paperLayout.setTag(cursor.getString(cursor.getColumnIndex("url")));
            paperLayout.PaperType.setText(subjectType);
            pastPaperList.addView(paperLayout, layoutParams);
            pastPaperList.addView(divider, dividerParams);
            paperLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPDF((String) paperLayout.getTag());
                }
            });
        }
        cursor.close();
    }

}
