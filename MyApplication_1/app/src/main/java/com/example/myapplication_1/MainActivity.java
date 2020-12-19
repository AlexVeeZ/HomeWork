package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnSearchClick(View v){
        TextView textView = (TextView)findViewById(R.id.city);
        EditText editText = (EditText)findViewById(R.id.search_city);
        Button button = (Button)findViewById(R.id.btn_search);

        textView.setText("\t\t"+editText.getText());
    }

    public void onBtnLangClick(View v){
        TextView textCity = (TextView)findViewById(R.id.city);
        TextView textGraph = (TextView)findViewById(R.id.graph);

        TextView textScale = (TextView)findViewById(R.id.scale);
        TextView textScaleMon = (TextView)findViewById(R.id.scale_mon);
        TextView textScaleTue = (TextView)findViewById(R.id.scale_tue);
        TextView textScaleWed = (TextView)findViewById(R.id.scale_wed);
        TextView textScaleThu = (TextView)findViewById(R.id.scale_thu);
        TextView textScaleFri = (TextView)findViewById(R.id.scale_fri);
        TextView textScaleSat = (TextView)findViewById(R.id.scale_sat);
        TextView textScaleSun = (TextView)findViewById(R.id.scale_sun);

        TextView textDateMon = (TextView)findViewById(R.id.mon);
        TextView textDateTue = (TextView)findViewById(R.id.tue);
        TextView textDateWed = (TextView)findViewById(R.id.wed);
        TextView textDateThu = (TextView)findViewById(R.id.thu);
        TextView textDateFri = (TextView)findViewById(R.id.fri);
        TextView textDateSat = (TextView)findViewById(R.id.sat);
        TextView textDateSun = (TextView)findViewById(R.id.sun);

        EditText editText = (EditText)findViewById(R.id.search_city);
        Button button = (Button)findViewById(R.id.btn_search);

        TextView[] array = new TextView[]{textGraph, textDateMon, textDateTue, textDateWed,
                textDateThu, textDateFri, textDateSat, textDateSun, textScale, textScaleMon, textScaleTue,
                textScaleWed, textScaleThu, textScaleFri, textScaleSat, textScaleSun};
        for (int i = 0; i < array.length; i++) {
            array[i].setText("°F");
            array[0].setText("\t\tWeekly schedule");
            array[1].setText("Mo");
            array[2].setText("Tu");
            array[3].setText("We");
            array[4].setText("Th");
            array[5].setText("Fr");
            array[6].setText("Sa");
            array[7].setText("Su");

        }

        editText.setHint("SEARCH...");
        button.setText("FIND");

    }


}