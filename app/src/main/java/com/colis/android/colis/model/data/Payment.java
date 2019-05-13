package com.colis.android.colis.model.data;

import android.content.Context;

public class Payment {

    private String Libelle;
    private Context context;
    private int ID;

    public Payment(Context context,String libelle,int id) {
        this.Libelle = libelle;
        this.ID = id;
        this.context = context;
    }

    public Payment() {

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }
}
