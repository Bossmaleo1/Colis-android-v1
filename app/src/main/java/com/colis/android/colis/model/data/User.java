package com.colis.android.colis.model.data;

public class User {

    private int ID;
    private String NOM;
    private String PRENOM;
    private String DATE_DE_NAISSANCE;
    private String SEXE;
    private String EMAIL;
    private String PHOTO;
    private String KEYPUSH;
    private String TELEPHONE;
    private String LANGUE;
    private String ETAT;
    private String PAYS;
    private String VILLE;

    public User()
    {

    }

    public User(int id,String nom,String prenom,String date,String sexe
            ,String email,String photo,String keypush,String langue,String etat,String pays,String ville,String telephone)
    {
        this.ID = id;
        this.NOM = nom;
        this.PRENOM = prenom;
        this.DATE_DE_NAISSANCE = date;
        this.SEXE = sexe;
        this.PHOTO = photo;
        this.KEYPUSH = keypush;
        this.LANGUE = langue;
        this.ETAT = etat;
        this.PAYS = pays;
        this.VILLE = ville;
        this.EMAIL = email;
        this.TELEPHONE = telephone;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public String getPAYS() {
        return PAYS;
    }

    public String getVILLE() {
        return VILLE;
    }

    public void setPAYS(String PAYS) {
        this.PAYS = PAYS;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public void setVILLE(String VILLE) {
        this.VILLE = VILLE;
    }

    public int getID() {
        return ID;
    }

    public String getDATE_DE_NAISSANCE() {
        return DATE_DE_NAISSANCE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getETAT() {
        return ETAT;
    }

    public String getKEYPUSH() {
        return KEYPUSH;
    }

    public String getLANGUE() {
        return LANGUE;
    }

    public String getNOM() {
        return NOM;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public String getSEXE() {
        return SEXE;
    }

    public void setDATE_DE_NAISSANCE(String DATE_DE_NAISSANCE) {
        this.DATE_DE_NAISSANCE = DATE_DE_NAISSANCE;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setETAT(String ETAT) {
        this.ETAT = ETAT;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setKEYPUSH(String KEYPUSH) {
        this.KEYPUSH = KEYPUSH;
    }

    public void setLANGUE(String LANGUE) {
        this.LANGUE = LANGUE;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public void setSEXE(String SEXE) {
        this.SEXE = SEXE;
    }

}
