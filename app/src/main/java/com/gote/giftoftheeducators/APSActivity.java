package com.gote.giftoftheeducators;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class APSActivity extends AppCompatActivity {

    SharedPreferences preferences;
    ArrayList<Spinner> subjects;
    CheckBox engHL, engFAL, mathLit;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aps);

        preferences = getSharedPreferences("UserPrefs", this.MODE_PRIVATE);
        initialize(preferences.getString("mode", "light"));

        Spinner spinnerHL = findViewById(R.id.sprHomeLanguage);
        Spinner spinnerFAL = findViewById(R.id.sprFirstAdditionalLanguage);
        Spinner spinnerMath = findViewById(R.id.sprMathematics);
        Spinner spinnerLO = findViewById(R.id.sprLifeOrienatation);
        Spinner spinnerSubject1 = findViewById(R.id.sprFirstSubject);
        Spinner spinnerSubject2 = findViewById(R.id.sprSecondSubject);
        Spinner spinnerSubject3 = findViewById(R.id.sprThirdSubject);

        subjects = new ArrayList<>();
        subjects.add(spinnerHL);
        subjects.add(spinnerFAL);
        subjects.add(spinnerMath);
        subjects.add(spinnerLO);
        subjects.add(spinnerSubject1);
        subjects.add(spinnerSubject2);
        subjects.add(spinnerSubject3);


        calculate = findViewById(R.id.btnCalculateAps);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAPS(subjects);
            }
        });

        engHL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockCheckbox(0);
            }
        });

        engFAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockCheckbox(1);
            }
        });
    }

    public void initialize(String mode){
        String[] marks = new String[]{"-- Select Percentage --","90% - 100%","80% - 89%","70% - 79%","60% - 69%","50% - 59%","40% - 49%","30% - 39%","0% - 29%"};
        ArrayAdapter<String> adapter;

        LinearLayout main = findViewById(R.id.ScrollLayout);
        CardView cardSubjects = findViewById(R.id.SubjectCard);
        CardView cardHL = findViewById(R.id.HomeLanguageCard);
        CardView cardFAL = findViewById(R.id.FirstAdditionalLanguageCard);
        CardView cardMath = findViewById(R.id.MathematicsCard);
        CardView cardLO = findViewById(R.id.LifeOrientationCard);
        CardView cardSubject1 = findViewById(R.id.FirstSubjectCard);
        CardView cardSubject2 = findViewById(R.id.SecondSubjectCard);
        CardView cardSubject3 = findViewById(R.id.ThirdSubjectCard);
        CardView cardScore = findViewById(R.id.ScoreCard);

        Spinner spinnerHL = findViewById(R.id.sprHomeLanguage);
        Spinner spinnerFAL = findViewById(R.id.sprFirstAdditionalLanguage);
        Spinner spinnerMath = findViewById(R.id.sprMathematics);
        Spinner spinnerLO = findViewById(R.id.sprLifeOrienatation);
        Spinner spinnerSubject1 = findViewById(R.id.sprFirstSubject);
        Spinner spinnerSubject2 = findViewById(R.id.sprSecondSubject);
        Spinner spinnerSubject3 = findViewById(R.id.sprThirdSubject);

        TextView HL = findViewById(R.id.lblHomeLanguage);
        TextView FAL = findViewById(R.id.lblFirstAdditionalLanguage);
        TextView Math = findViewById(R.id.lblMathematics);
        TextView LO = findViewById(R.id.lblLifeOrienatation);
        TextView subject1 = findViewById(R.id.lblFirstSubject);
        TextView subject2 = findViewById(R.id.lblSecondSubject);
        TextView subject3 = findViewById(R.id.lblThirdSubject);
        TextView subjects = findViewById(R.id.lblSubjectMarks);
        TextView scores = findViewById(R.id.lblScores);
        TextView note = findViewById(R.id.lblNote);

        engHL = findViewById(R.id.cbxEnglishHL);
        engFAL = findViewById(R.id.cbxEnglishFAL);
        mathLit = findViewById(R.id.cbxMathematicalLiteracy);

        calculate = findViewById(R.id.btnCalculateAps);

        if (mode.equals("light")){
            main.setBackgroundColor(getResources().getColor(R.color.white));

            cardSubjects.setCardBackgroundColor(getResources().getColor(R.color.smokey_white));
            cardScore.setCardBackgroundColor(getResources().getColor(R.color.smokey_white));
            cardHL.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardFAL.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardMath.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardLO.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardSubject1.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardSubject2.setCardBackgroundColor(getResources().getColor(R.color.white));
            cardSubject3.setCardBackgroundColor(getResources().getColor(R.color.white));

            adapter = new ArrayAdapter<String>(this, R.layout.light_spinner_item, marks);

            spinnerHL.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
            spinnerFAL.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
            spinnerMath.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
            spinnerLO.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
            spinnerSubject1.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
            spinnerSubject2.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));
            spinnerSubject3.setPopupBackgroundDrawable(getResources().getDrawable(R.color.white));

            HL.setTextColor(getResources().getColor(R.color.black));
            FAL.setTextColor(getResources().getColor(R.color.black));
            Math.setTextColor(getResources().getColor(R.color.black));
            LO.setTextColor(getResources().getColor(R.color.black));
            subject1.setTextColor(getResources().getColor(R.color.black));
            subject2.setTextColor(getResources().getColor(R.color.black));
            subject3.setTextColor(getResources().getColor(R.color.black));
            subjects.setTextColor(getResources().getColor(R.color.black));
            scores.setTextColor(getResources().getColor(R.color.black));
            note.setTextColor(getResources().getColor(R.color.black));

            engHL.setTextColor(getResources().getColor(R.color.black));
            engFAL.setTextColor(getResources().getColor(R.color.black));
            mathLit.setTextColor(getResources().getColor(R.color.black));

            calculate.setTextColor(getResources().getColor(R.color.black));
        }
        else{
            main.setBackgroundColor(getResources().getColor(R.color.background_black));

            cardSubjects.setCardBackgroundColor(getResources().getColor(R.color.secondary_black));
            cardScore.setCardBackgroundColor(getResources().getColor(R.color.secondary_black));
            cardHL.setCardBackgroundColor(getResources().getColor(R.color.background_black));
            cardFAL.setCardBackgroundColor(getResources().getColor(R.color.background_black));
            cardMath.setCardBackgroundColor(getResources().getColor(R.color.background_black));
            cardLO.setCardBackgroundColor(getResources().getColor(R.color.background_black));
            cardSubject1.setCardBackgroundColor(getResources().getColor(R.color.background_black));
            cardSubject2.setCardBackgroundColor(getResources().getColor(R.color.background_black));
            cardSubject3.setCardBackgroundColor(getResources().getColor(R.color.background_black));

            adapter = new ArrayAdapter<String>(this, R.layout.dark_spinner_item, marks);

            spinnerHL.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));
            spinnerFAL.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));
            spinnerMath.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));
            spinnerLO.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));
            spinnerSubject1.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));
            spinnerSubject2.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));
            spinnerSubject3.setPopupBackgroundDrawable(getResources().getDrawable(R.color.background_black));

            HL.setTextColor(getResources().getColor(R.color.white));
            FAL.setTextColor(getResources().getColor(R.color.white));
            Math.setTextColor(getResources().getColor(R.color.white));
            LO.setTextColor(getResources().getColor(R.color.white));
            subject1.setTextColor(getResources().getColor(R.color.white));
            subject2.setTextColor(getResources().getColor(R.color.white));
            subject3.setTextColor(getResources().getColor(R.color.white));
            subjects.setTextColor(getResources().getColor(R.color.white));
            scores.setTextColor(getResources().getColor(R.color.white));
            note.setTextColor(getResources().getColor(R.color.white));

            engHL.setTextColor(getResources().getColor(R.color.white));
            engFAL.setTextColor(getResources().getColor(R.color.white));
            mathLit.setTextColor(getResources().getColor(R.color.white));

            calculate.setTextColor(getResources().getColor(R.color.white));
        }

        spinnerHL.setAdapter(adapter);
        spinnerFAL.setAdapter(adapter);
        spinnerMath.setAdapter(adapter);
        spinnerLO.setAdapter(adapter);
        spinnerSubject1.setAdapter(adapter);
        spinnerSubject2.setAdapter(adapter);
        spinnerSubject3.setAdapter(adapter);
        spinnerHL.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
        spinnerFAL.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
        spinnerMath.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
        spinnerLO.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
        spinnerSubject1.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
        spinnerSubject2.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
        spinnerSubject3.getBackground().setColorFilter(getResources().getColor(R.color.red_003), PorterDuff.Mode.SRC_ATOP);
    }

    public void calculateAPS(ArrayList<Spinner> subjects){
        TextView APS = findViewById(R.id.lblStandardScore);
        TextView WITS = findViewById(R.id.lblWitsScore);
        engHL = findViewById(R.id.cbxEnglishHL);
        engFAL = findViewById(R.id.cbxEnglishFAL);
        mathLit = findViewById(R.id.cbxMathematicalLiteracy);

        int standardAPS = 0;
        int witsAPS = 0;
        for(Spinner subject: subjects){
            boolean englishHL = subject.equals(findViewById(R.id.sprHomeLanguage)) && engHL.isChecked();
            boolean englishFAL = subject.equals(findViewById(R.id.sprFirstAdditionalLanguage)) && engFAL.isChecked();
            boolean math = subject.equals(findViewById(R.id.sprMathematics)) && !mathLit.isChecked();
            int index = subject.getSelectedItemPosition();
            if(subject.equals(findViewById(R.id.sprLifeOrienatation))){
                switch (index){
                    case 0:
                        Toast.makeText(this,"Please Select All Marks", Toast.LENGTH_LONG).show();
                        return;
                    case 1:
                        witsAPS += 4;
                        continue;
                    case 2:
                        witsAPS += 3;
                        continue;
                    case 3:
                        witsAPS += 2;
                        continue;
                    case 4:
                        witsAPS += 1;
                        continue;
                }
            }
            switch (index){
                case 0:
                    Toast.makeText(this,"Please Select All Marks", Toast.LENGTH_LONG).show();
                    return;
                case 1:
                    standardAPS += 7;
                    witsAPS += 8;
                    if(englishHL || englishFAL || math){
                        witsAPS += 2;
                    }
                    continue;
                case 2:
                    standardAPS += 7;
                    witsAPS += 7;
                    if(englishHL || englishFAL || math){
                        witsAPS += 2;
                    }
                    continue;
                case 3:
                    standardAPS += 6;
                    witsAPS += 6;
                    if(englishHL || englishFAL || math){
                        witsAPS += 2;
                    }
                    continue;
                case 4:
                    standardAPS += 5;
                    witsAPS += 5;
                    if(englishHL || englishFAL || math){
                        witsAPS += 2;
                    }
                    continue;
                case 5:
                    standardAPS += 4;
                    witsAPS += 4;
                    continue;
                case 6:
                    standardAPS += 3;
                    witsAPS += 3;
                    continue;
                case 7:
                    standardAPS += 2;
                    continue;
                case 8:
                    standardAPS += 1;
                    continue;
            }
        }
        APS.setText("Standard APS: " + standardAPS);
        WITS.setText("WITS APS: " + witsAPS);
    }

    public void lockCheckbox(int flag){
        if(flag == 0){
            if(engFAL.isChecked()){
                engFAL.setChecked(false);
            }
        }
        else {
            if(engHL.isChecked()){
                engHL.setChecked(false);
            }
        }
    }
}
