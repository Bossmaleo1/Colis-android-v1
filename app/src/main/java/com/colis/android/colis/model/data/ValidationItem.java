package com.colis.android.colis.model.data;

import android.content.Context;

public class ValidationItem {

    private Context context;
    private int id;
    private String description;
    private String date_validation;
    private String statut_validation;
    private String nombre_kilo;
    private String id_emmetteur;
    private String id_annonce;
    private String nom_emmetteur;
    private String prenom_emmetteur;
    private String photo_emmetteur;
    private String telephone;

    public ValidationItem(Context context,int id,String description,String date_validation,String statut_validation,String nombre_kilo,String id_emmetteur,
                          String id_annonce,String nom_emmetteur,String prenom_emmetteur,String photo_emmetteur,String phone) {
        this.context = context;
        this.id = id;
        this.description = description;
        this.date_validation = date_validation;
        this.statut_validation = statut_validation;
        this.nombre_kilo = nombre_kilo;
        this.id_emmetteur = id_emmetteur;
        this.id_annonce = id_annonce;
        this.nom_emmetteur = nom_emmetteur;
        this.prenom_emmetteur = prenom_emmetteur;
        this.photo_emmetteur = photo_emmetteur;
        this.telephone = phone;
    }


    public int getId() {
        return id;
    }

    public String getDate_validation() {
        return date_validation;
    }

    public String getDescription() {
        return description;
    }

    public String getId_emmetteur() {
        return id_emmetteur;
    }

    public String getId_annonce() {
        return id_annonce;
    }

    public String getNom_emmetteur() {
        return nom_emmetteur;
    }

    public String getNombre_kilo() {
        return nombre_kilo;
    }

    public String getPhoto_emmetteur() {
        return photo_emmetteur;
    }

    public String getStatut_validation() {
        return statut_validation;
    }

    public void setDate_validation(String date_validation) {
        this.date_validation = date_validation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrenom_emmetteur() {
        return prenom_emmetteur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_annonce(String id_annonce) {
        this.id_annonce = id_annonce;
    }

    public void setId_emmetteur(String id_emmetteur) {
        this.id_emmetteur = id_emmetteur;
    }

    public void setNom_emmetteur(String nom_emmetteur) {
        this.nom_emmetteur = nom_emmetteur;
    }

    public void setNombre_kilo(String nombre_kilo) {
        this.nombre_kilo = nombre_kilo;
    }

    public void setStatut_validation(String statut_validation) {
        this.statut_validation = statut_validation;
    }

    public void setPhoto_emmetteur(String photo_emmetteur) {
        this.photo_emmetteur = photo_emmetteur;
    }

    public void setPrenom_emmetteur(String prenom_emmetteur) {
        this.prenom_emmetteur = prenom_emmetteur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
