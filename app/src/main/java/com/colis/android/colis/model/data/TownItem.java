package com.colis.android.colis.model.data;

public class TownItem {

    private int ID;
    private String Libelle;

    public TownItem()
    {

    }

    public TownItem(int id,String libelle)
    {
        this.ID = id;
        this.Libelle = libelle;
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
