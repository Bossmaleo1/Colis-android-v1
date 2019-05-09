package com.colis.android.colis.model.data;

import android.content.Context;

public class Annonce {

    private int ID;
    private int ID_USER;
    private String PHOTO_USER;
    private String NOM_USER;
    private String PHONE_USER;
    private String DATE_ANNONCE;
    private String DATE_ANNONCE_VOYAGE;
    private String PRIX;
    private String LIEUX_DEPART;
    private String LIEUX_ARRIVEE;
    private String HEURE_DEPART;
    private String HEURE_ARRIVEE;
    private String NOMBRE_KILO;
    private Context context;

    public Annonce(Context context,int ID,int ID_USER,String PHOTO_USER,String NOM_USER
            ,String PHONE_USER,String DATE_ANNONCE,String DATE_ANNONCE_VOYAGE,String PRIX
            ,String LIEUX_DEPART,String LIEUX_ARRIVEE,String HEURE_DEPART,String HEURE_ARRIVEE
            ,String NOMBRE_KILO) {
        this.context = context;
        this.ID = ID;
        this.ID_USER = ID_USER;
        this.PHOTO_USER = PHOTO_USER;
        this.NOM_USER = NOM_USER;
        this.PHONE_USER = PHONE_USER;
        this.DATE_ANNONCE = DATE_ANNONCE;
        this.DATE_ANNONCE_VOYAGE = DATE_ANNONCE_VOYAGE;
        this.PRIX = PRIX;
        this.LIEUX_DEPART = LIEUX_DEPART;
        this.LIEUX_ARRIVEE = LIEUX_ARRIVEE;
        this.HEURE_DEPART = HEURE_DEPART;
        this.HEURE_ARRIVEE = HEURE_ARRIVEE;
        this.NOMBRE_KILO = NOMBRE_KILO;
    }

    public int getID() {
        return ID;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public String getDATE_ANNONCE() {
        return DATE_ANNONCE;
    }

    public String getNOM_USER() {
        return NOM_USER;
    }

    public String getDATE_ANNONCE_VOYAGE() {
        return DATE_ANNONCE_VOYAGE;
    }

    public String getLIEUX_ARRIVEE() {
        return LIEUX_ARRIVEE;
    }

    public String getPHONE_USER() {
        return PHONE_USER;
    }

    public String getLIEUX_DEPART() {
        return LIEUX_DEPART;
    }

    public String getPHOTO_USER() {
        return PHOTO_USER;
    }

    public String getHEURE_DEPART() {
        return HEURE_DEPART;
    }

    public void setDATE_ANNONCE(String DATE_ANNONCE) {
        this.DATE_ANNONCE = DATE_ANNONCE;
    }

    public String getPRIX() {
        return PRIX;
    }

    public void setDATE_ANNONCE_VOYAGE(String DATE_ANNONCE_VOYAGE) {
        this.DATE_ANNONCE_VOYAGE = DATE_ANNONCE_VOYAGE;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public void setLIEUX_DEPART(String LIEUX_DEPART) {
        this.LIEUX_DEPART = LIEUX_DEPART;
    }

    public void setNOM_USER(String NOM_USER) {
        this.NOM_USER = NOM_USER;
    }

    public void setPHONE_USER(String PHONE_USER) {
        this.PHONE_USER = PHONE_USER;
    }

    public void setHEURE_DEPART(String HEURE_DEPART) {
        this.HEURE_DEPART = HEURE_DEPART;
    }

    public void setLIEUX_ARRIVEE(String LIEUX_ARRIVEE) {
        this.LIEUX_ARRIVEE = LIEUX_ARRIVEE;
    }

    public void setPHOTO_USER(String PHOTO_USER) {
        this.PHOTO_USER = PHOTO_USER;
    }

    public void setPRIX(String PRIX) {
        this.PRIX = PRIX;
    }

    public String getHEURE_ARRIVEE() {
        return HEURE_ARRIVEE;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setHEURE_ARRIVEE(String HEURE_ARRIVEE) {
        this.HEURE_ARRIVEE = HEURE_ARRIVEE;
    }

    public String getNOMBRE_KILO() {
        return NOMBRE_KILO;
    }

    public void setNOMBRE_KILO(String NOMBRE_KILO) {
        this.NOMBRE_KILO = NOMBRE_KILO;
    }
    
}
