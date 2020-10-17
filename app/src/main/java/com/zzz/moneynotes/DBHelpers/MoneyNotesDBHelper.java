package com.zzz.moneynotes.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zzz.moneynotes.models.MoneyNotes;

import java.util.ArrayList;
import java.util.List;

public class MoneyNotesDBHelper extends SQLiteOpenHelper {

    //init static variable for DB_NAME AND DB_VER
    public static final String DB_NAME = "SpentDB";
    private static final int DB_VER = 1;

    public MoneyNotesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MoneyNotes.CREATE_TABLE);
    }

    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older Table
        db.execSQL("DROP TABLE IF EXISTS " + MoneyNotes.TABLE_NAME);
        //create table again
        onCreate(db);
    }

    //Inserting Task to Database
    public void insertMoneyNote(String insertNamaPengeluaran,String insertJmlhUang,String insertTgl){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` will be inserted automatically.
        // no need to add them
        values.put(MoneyNotes.COLUMN_NAMAPENGELUARAN, insertNamaPengeluaran);
        values.put(MoneyNotes.COLUMN_JMLHUANG, insertJmlhUang);
        values.put(MoneyNotes.COLUMN_TANGGAL, insertTgl);

        // insert row
        db.insert(MoneyNotes.TABLE_NAME, null, values);

        // close db connection
        db.close();

    }

    //getting money note by id from database
    public MoneyNotes getMoneyNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(MoneyNotes.TABLE_NAME,
                new String[]{MoneyNotes.COLUMN_ID, MoneyNotes.COLUMN_NAMAPENGELUARAN, MoneyNotes.COLUMN_JMLHUANG, MoneyNotes.COLUMN_TANGGAL},
                MoneyNotes.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        MoneyNotes moneyNotes = new MoneyNotes(
                cursor.getInt(cursor.getColumnIndex(MoneyNotes.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(MoneyNotes.COLUMN_NAMAPENGELUARAN)),
                cursor.getString(cursor.getColumnIndex(MoneyNotes.COLUMN_JMLHUANG)),
                cursor.getString(cursor.getColumnIndex(MoneyNotes.COLUMN_TANGGAL)));

        // close the db connection
        cursor.close();

        return moneyNotes;
    }

    //Get All Task from database
    public List<MoneyNotes> getAllMoneyNotes() {
        List<MoneyNotes> allMoneyNotes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MoneyNotes.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MoneyNotes itemMoney = new MoneyNotes();
                itemMoney.setId(cursor.getInt(cursor.getColumnIndex(MoneyNotes.COLUMN_ID)));
                itemMoney.setNama_pengeluaran(cursor.getString(cursor.getColumnIndex(MoneyNotes.COLUMN_NAMAPENGELUARAN)));
                itemMoney.setJmlh_uang(cursor.getString(cursor.getColumnIndex(MoneyNotes.COLUMN_JMLHUANG)));
                itemMoney.setTanggal(cursor.getString(cursor.getColumnIndex(MoneyNotes.COLUMN_TANGGAL)));
                allMoneyNotes.add(itemMoney);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return allMoneyNotes;
    }

    //Delete task from database
    public void deleteMoneyNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MoneyNotes.TABLE_NAME, MoneyNotes.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
