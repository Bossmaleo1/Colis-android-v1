package com.colis.android.colis.model.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Annonce implements Parcelable {

    private int ID;
    private int ID_USER;
    private String PHOTO_USER;
    private String NOM_USER;
    private String PHONE_USER;
    private String DATE_ANNONCE;
    private String DATE_ANNONCE_VOYAGE;
    private String DATE_ANNONCE_VOYAGE2;
    private String KEYPUSH;
    private String PRIX;
    private String LIEUX_RDV1;
    private String LIEUX_RDV2;
    private String VILLE_DEPART;
    private String VILLE_ARRIVEE;
    private String HEURE_DEPART;
    private String HEURE_ARRIVEE;
    private String NOMBRE_KILO;
    private Context context;

    public Annonce(Context context,int ID,int ID_USER,String PHOTO_USER,String NOM_USER
            ,String PHONE_USER,String DATE_ANNONCE,String DATE_ANNONCE_VOYAGE,String PRIX
            ,String LIEUX_RDV1,String LIEUX_RDV2,String VILLE_DEPART,String VILLE_ARRIVEE,String HEURE_DEPART,String HEURE_ARRIVEE
            ,String NOMBRE_KILO,String DATE_ANNONCE_VOYAGE2,String KEYPUSH) {
        this.context = context;
        this.ID = ID;
        this.ID_USER = ID_USER;
        this.PHOTO_USER = PHOTO_USER;
        this.NOM_USER = NOM_USER;
        this.PHONE_USER = PHONE_USER;
        this.DATE_ANNONCE = DATE_ANNONCE;
        this.DATE_ANNONCE_VOYAGE = DATE_ANNONCE_VOYAGE;
        this.PRIX = PRIX;
        this.LIEUX_RDV1 = LIEUX_RDV1;
        this.LIEUX_RDV2 = LIEUX_RDV2;
        this.VILLE_DEPART = VILLE_DEPART;
        this.VILLE_ARRIVEE = VILLE_ARRIVEE;
        this.HEURE_DEPART = HEURE_DEPART;
        this.HEURE_ARRIVEE = HEURE_ARRIVEE;
        this.NOMBRE_KILO = NOMBRE_KILO;
        this.DATE_ANNONCE_VOYAGE2 = DATE_ANNONCE_VOYAGE2;
        this.KEYPUSH = KEYPUSH;
    }

    public Annonce(int ID,int ID_USER,String PHOTO_USER,String NOM_USER
            ,String PHONE_USER,String DATE_ANNONCE,String DATE_ANNONCE_VOYAGE,String PRIX
            ,String LIEUX_RDV1,String LIEUX_RDV2,String VILLE_DEPART,String VILLE_ARRIVEE,String HEURE_DEPART,String HEURE_ARRIVEE
            ,String NOMBRE_KILO,String DATE_ANNONCE_VOYAGE2,String KEYPUSH) {
        this.ID = ID;
        this.ID_USER = ID_USER;
        this.PHOTO_USER = PHOTO_USER;
        this.NOM_USER = NOM_USER;
        this.PHONE_USER = PHONE_USER;
        this.DATE_ANNONCE = DATE_ANNONCE;
        this.DATE_ANNONCE_VOYAGE = DATE_ANNONCE_VOYAGE;
        this.PRIX = PRIX;
        this.LIEUX_RDV1 = LIEUX_RDV1;
        this.LIEUX_RDV2 = LIEUX_RDV2;
        this.VILLE_DEPART = VILLE_DEPART;
        this.VILLE_ARRIVEE = VILLE_ARRIVEE;
        this.HEURE_DEPART = HEURE_DEPART;
        this.HEURE_ARRIVEE = HEURE_ARRIVEE;
        this.NOMBRE_KILO = NOMBRE_KILO;
        this.DATE_ANNONCE_VOYAGE2 = DATE_ANNONCE_VOYAGE2;
        this.KEYPUSH = KEYPUSH;
    }

    public Annonce(Parcel parcel){
        this.ID = parcel.readInt();
        this.ID_USER = parcel.readInt();
        this.PHOTO_USER = parcel.readString();
        this.NOM_USER = parcel.readString();
        this.PHONE_USER = parcel.readString();
        this.DATE_ANNONCE = parcel.readString();
        this.DATE_ANNONCE_VOYAGE = parcel.readString();
        this.PRIX = parcel.readString();
        this.LIEUX_RDV1 = parcel.readString();
        this.LIEUX_RDV2 = parcel.readString();
        this.VILLE_DEPART = parcel.readString();
        this.VILLE_ARRIVEE = parcel.readString();
        this.HEURE_DEPART = parcel.readString();
        this.HEURE_ARRIVEE = parcel.readString();
        this.NOMBRE_KILO = parcel.readString();
        this.DATE_ANNONCE_VOYAGE2 = parcel.readString();
        this.KEYPUSH = parcel.readString();
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

    public String getPHONE_USER() {
        return PHONE_USER;
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

    public void setNOM_USER(String NOM_USER) {
        this.NOM_USER = NOM_USER;
    }

    public void setPHONE_USER(String PHONE_USER) {
        this.PHONE_USER = PHONE_USER;
    }

    public void setHEURE_DEPART(String HEURE_DEPART) {
        this.HEURE_DEPART = HEURE_DEPART;
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

    public String getLIEUX_RDV1() {
        return LIEUX_RDV1;
    }

    public String getLIEUX_RDV2() {
        return LIEUX_RDV2;
    }

    public String getVILLE_DEPART() {
        return VILLE_DEPART;
    }

    public String getVILLE_ARRIVEE() {
        return VILLE_ARRIVEE;
    }

    public void setLIEUX_RDV1(String LIEUX_RDV1) {
        this.LIEUX_RDV1 = LIEUX_RDV1;
    }

    public void setLIEUX_RDV2(String LIEUX_RDV2) {
        this.LIEUX_RDV2 = LIEUX_RDV2;
    }

    public void setVILLE_DEPART(String VILLE_DEPART) {
        this.VILLE_DEPART = VILLE_DEPART;
    }

    public void setVILLE_ARRIVEE(String VILLE_ARRIVEE) {
        this.VILLE_ARRIVEE = VILLE_ARRIVEE;
    }

    public String getDATE_ANNONCE_VOYAGE2() {
        return DATE_ANNONCE_VOYAGE2;
    }

    public String getKEYPUSH() {
        return KEYPUSH;
    }

    public void setDATE_ANNONCE_VOYAGE2(String DATE_ANNONCE_VOYAGE2) {
        this.DATE_ANNONCE_VOYAGE2 = DATE_ANNONCE_VOYAGE2;
    }

    public void setKEYPUSH(String KEYPUSH) {
        this.KEYPUSH = KEYPUSH;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeInt(ID_USER);
        dest.writeString(PHOTO_USER);
        dest.writeString(NOM_USER);
        dest.writeString(PHONE_USER);
        dest.writeString(DATE_ANNONCE);
        dest.writeString(DATE_ANNONCE_VOYAGE);
        dest.writeString(PRIX);
        dest.writeString(LIEUX_RDV1);
        dest.writeString(LIEUX_RDV2);
        dest.writeString(VILLE_DEPART);
        dest.writeString(VILLE_ARRIVEE);
        dest.writeString(HEURE_DEPART);
        dest.writeString(HEURE_ARRIVEE);
        dest.writeString(NOMBRE_KILO);
        dest.writeString(DATE_ANNONCE_VOYAGE2);
        dest.writeString(KEYPUSH);
    }

    public static final Parcelable.Creator<Annonce> CREATOR = new Parcelable.Creator<Annonce>(){

        @Override
        public Annonce createFromParcel(Parcel parcel) {
            return new Annonce(parcel);
        }

        @Override
        public Annonce[] newArray(int size) {
            return new Annonce[0];
        }
    };


}
