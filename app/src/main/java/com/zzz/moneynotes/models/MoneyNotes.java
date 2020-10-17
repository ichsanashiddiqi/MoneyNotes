package com.zzz.moneynotes.models;

public class MoneyNotes {
    public static final String TABLE_NAME = "moneyNotes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMAPENGELUARAN = "nama_pengeluaran";
    public static final String COLUMN_JMLHUANG = "jmlh_uang";
    public static final String COLUMN_TANGGAL = "tanggal";

    private int id;
    private String nama_pengeluaran,jmlh_uang,tanggal;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAMAPENGELUARAN + " TEXT,"
            + COLUMN_JMLHUANG + " TEXT,"
            + COLUMN_TANGGAL +" TEXT"
            + ")";

    public MoneyNotes() {
    }

    public MoneyNotes(int id, String nama_pengeluaran, String jmlh_uang, String tanggal) {
        this.id = id;
        this.nama_pengeluaran = nama_pengeluaran;
        this.jmlh_uang = jmlh_uang;
        this.tanggal = tanggal;
    }

    public String getNama_pengeluaran() { return nama_pengeluaran; }

    public void setNama_pengeluaran(String nama_pengeluaran) { this.nama_pengeluaran = nama_pengeluaran; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getJmlh_uang() { return jmlh_uang; }

    public void setJmlh_uang(String jmlh_uang) { this.jmlh_uang = jmlh_uang; }

    public String getTanggal() {  return tanggal; }

    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
}
