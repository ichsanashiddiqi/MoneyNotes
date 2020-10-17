package com.zzz.moneyspentnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zzz.moneyspentnotes.DBHelpers.MoneyNotesDBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMoneyNotes extends AppCompatActivity {
    EditText namaPengeluaran,jmlhPengeluaran;
    private MoneyNotesDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_money_notes);

        //Hide the action bar
        getSupportActionBar().hide();

        namaPengeluaran = findViewById(R.id.edtNamaPengeluaran);
        jmlhPengeluaran = findViewById(R.id.edtJumlahPengeluaran);

        //set DBHelper
        db = new MoneyNotesDBHelper(this);
    }

    public void saveMoneyNote(View view) {
        String namaPeng = namaPengeluaran.getText().toString();
        String jmlhPeng = jmlhPengeluaran.getText().toString();
        String date = getCurrentDate();

        if(validateInput(namaPeng,jmlhPeng)){
            // inserting moneyNote in db
            // newly inserted moneynote id
            db.insertMoneyNote(namaPeng,jmlhPeng,date);
            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            Intent mainMenu = new Intent(this,MainMenu.class);
            startActivity(mainMenu);
            finish();
        }
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }

    private boolean validateInput(String namaPeng, String jmlhPeng) {
        if(namaPeng.isEmpty() && jmlhPeng.isEmpty()){
            Toast.makeText(this, "Nama Pengeluaran dan Jumlah Pengeluaran tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        } else if(namaPeng.isEmpty()){
            Toast.makeText(this, "Nama Pengeluaran tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        } else if(jmlhPeng.isEmpty()){
            Toast.makeText(this, "Jumlah Pengeluaran tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!validateAlphaAndSpace(namaPeng)){
            Toast.makeText(this, "Nama Pengeluaran harus berupa huruf", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!validateNumber(jmlhPeng)){
            Toast.makeText(this, "Jumlah Pengeluaran harus berupa angka", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private boolean validateAlphaAndSpace(String namaPeng) {
        return namaPeng != null && namaPeng.matches("[a-zA-Z\\s\'\"]+");
    }

    private boolean validateNumber(String jmlhPeng) {
        try {
            Double.parseDouble(jmlhPeng);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}