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

public class DetailMoneyNote extends AppCompatActivity {
    int id;
    MoneyNotes detailMoneyNote;
    MoneyNotesDBHelper db;
    TextView namaPeng,jmlhPeng,tglPeng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail_money_note);

        //Hide the action bar
        getSupportActionBar().hide();

        namaPeng = findViewById(R.id.txtTampilNamaPengeluaranDetail);
        jmlhPeng = findViewById(R.id.txtTampilJmlhPengeluaranDetail);
        tglPeng = findViewById(R.id.txtTampilTglPengeluaranDetail);

        id = Integer.parseInt(getIntent().getStringExtra("idMoneyNote"));

        db = new MoneyNotesDBHelper(this);
        detailMoneyNote = db.getMoneyNote(id);

        namaPeng.setText(detailMoneyNote.getNama_pengeluaran());
        jmlhPeng.setText(detailMoneyNote.getJmlh_uang());
        tglPeng.setText(detailMoneyNote.getTanggal());

    }

    public void backToList(View view) {
        Intent backToList = new Intent(this,MoneyNotesActivity.class);
        startActivity(backToList);
        finish();
    }
}