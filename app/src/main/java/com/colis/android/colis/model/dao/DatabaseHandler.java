package com.colis.android.colis.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.colis.android.colis.model.data.User;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Colis";
    private static final int DATABASE_VERSION = 1;
    //table user
    private static final String TABLE_USER = "USER";
    private static final String KEY_ID ="ID";
    private static final String KEY_NOM = "NOM";
    private static final String KEY_PRENOM ="PRENOM";
    private static final String KEY_PHOTO ="PHOTO";
    private static final String KEY_KEYPUSH = "KEYPUSH";
    private static final String KEY_SEXE = "SEXE";
    private static final String KEY_VILLE = "VILLE";
    private static final String KEY_DATE_DE_NAISSANCE = "DATE_DE_NAISSANCE";
    private static final String KEY_LANGUE = "LANGUE";
    private static final String KEY_PAYS = "PAYS";
    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_TELEPHONE = "TELEPHONE";
    private static final String KEY_ETAT = "ETAT";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Création de la table user
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOM + " TEXT,"+KEY_PRENOM+" TEXT,"+KEY_PHOTO+" TEXT," +
                KEY_KEYPUSH+" TEXT,"+KEY_SEXE+" TEXT," +KEY_EMAIL+" TEXT,"+KEY_ETAT+" TEXT,"+
                KEY_VILLE+" TEXT,"+KEY_DATE_DE_NAISSANCE+" TEXT,"+KEY_LANGUE+" TEXT,"+KEY_TELEPHONE+" TEXT,"+KEY_PAYS+" TEXT);";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void addUSER(User profil) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, profil.getNOM());
        values.put(KEY_PRENOM, profil.getPRENOM());
        values.put(KEY_EMAIL, profil.getEMAIL());
        values.put(KEY_PHOTO, profil.getPHOTO());
        values.put(KEY_ID, profil.getID());
        values.put(KEY_KEYPUSH, profil.getKEYPUSH());
        values.put(KEY_SEXE, profil.getSEXE());
        values.put(KEY_VILLE, profil.getVILLE());
        values.put(KEY_DATE_DE_NAISSANCE, profil.getDATE_DE_NAISSANCE());
        values.put(KEY_LANGUE, profil.getLANGUE());
        values.put(KEY_TELEPHONE, profil.getTELEPHONE());
        values.put(KEY_PAYS, profil.getPAYS());


        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User getUSER(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID,
                        KEY_NOM,KEY_PRENOM, KEY_DATE_DE_NAISSANCE,KEY_SEXE,
                        KEY_EMAIL,KEY_PHOTO,KEY_KEYPUSH,KEY_LANGUE,KEY_ETAT,
                        KEY_PAYS,KEY_VILLE,KEY_TELEPHONE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getString(5),
                cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12));
        return user;
    }

    public void UpdatePhoto(int IDUSER,String photo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHOTO,photo);
        db.update(TABLE_USER,values, KEY_ID+"="+IDUSER, null);
    }

    public void UpdateKeyPush(int IDUSER,String keypush)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_KEYPUSH,keypush);
        db.update(TABLE_USER,values, KEY_ID+"="+IDUSER, null);
    }
}
