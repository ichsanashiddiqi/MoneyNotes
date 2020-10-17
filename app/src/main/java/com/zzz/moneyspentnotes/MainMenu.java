package com.zzz.moneyspentnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zzz.moneyspentnotes.DBHelpers.MoneyNotesDBHelper;
import com.zzz.moneyspentnotes.models.MoneyNotes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainMenu extends AppCompatActivity {
    private MoneyNotesDBHelper db;
    private List<MoneyNotes> moneyNotesList = new ArrayList<>();
    private TextView totalPengHariIni;
    private int totalPengHariIniSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);

        //Hide the action bar
        getSupportActionBar().hide();

        totalPengHariIni = findViewById(R.id.txtJmlhPengeluaranHrIni);

        //set DBHelper
        db = new MoneyNotesDBHelper(this);
        moneyNotesList.addAll(db.getAllMoneyNotes());

        for(int i=0;i < moneyNotesList.size();i++){
            if(moneyNotesList.get(i).getTanggal().equals(getCurrentDate())){
                totalPengHariIniSum = totalPengHariIniSum+Integer.parseInt(moneyNotesList.get(i).getJmlh_uang());
            }
        }
        totalPengHariIni.setText(String.valueOf(totalPengHariIniSum));
    }

    public void addMoneyNote(View view) {
        Intent addMoneyNote = new Intent(this,AddMoneyNotes.class);
        startActivity(addMoneyNote);
        finish();
    }

    public void ListMoneyNotes(View view) {
        Intent listMoneyNotes = new Intent(this,MoneyNotesActivity.class);
        startActivity(listMoneyNotes);
        finish();
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }
}