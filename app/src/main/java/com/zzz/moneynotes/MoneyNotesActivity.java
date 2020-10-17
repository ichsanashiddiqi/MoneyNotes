package com.zzz.moneynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zzz.moneynotes.DBHelpers.MoneyNotesDBHelper;
import com.zzz.moneynotes.adapters.MoneyNotesAdapter;
import com.zzz.moneynotes.models.MoneyNotes;

import java.util.ArrayList;
import java.util.List;

public class MoneyNotesActivity extends AppCompatActivity {

    private MoneyNotesDBHelper db;
    private List<MoneyNotes> moneyNotesList = new ArrayList<>();
    private RecyclerView rv;
    private MoneyNotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_money_notes);
        //Hide the action bar
        getSupportActionBar().hide();

        //set RecyclerView
        rv = findViewById(R.id.rv_moneyNotes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        //set DBHelper
        db = new MoneyNotesDBHelper(this);
        moneyNotesList.addAll(db.getAllMoneyNotes());

        //set RecyclerView Adapter
        adapter = new MoneyNotesAdapter(this, moneyNotesList);
        rv.setAdapter(adapter);
    }

    public void backToMenu(View view) {
        Intent backMenu =  new Intent(this,MainMenu.class);
        startActivity(backMenu);
        finish();
    }
}