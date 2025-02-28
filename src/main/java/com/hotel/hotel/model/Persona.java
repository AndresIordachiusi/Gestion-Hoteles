package com.hotel.hotel.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Persona")
public class Persona {
    





@Id
private Long id;    

private String nom;
private String cognom;
private String adreça;

@Column (nullable = false, unique = true)
private String document_identitat;

private LocalDate data_naixement;
private String telefon;
private String email;



public Persona() {
}



public Persona(Long id, String nom, String cognom, String adreça, String document_identitat, LocalDate data_naixement,
    String telefon, String email) {
    this.id = id;
    this.nom = nom;
    this.cognom = cognom;
    this.adreça = adreça;
    this.document_identitat = document_identitat;
    this.data_naixement = data_naixement;
    this.telefon = telefon;
    this.email = email;
}



public Long getId() {
    return id;
}



public void setId(Long id) {
    this.id = id;
}



public String getNom() {
    return nom;
}



public void setNom(String nom) {
    this.nom = nom;
}



public String getCognom() {
    return cognom;
}



public void setCognom(String cognom) {
    this.cognom = cognom;
}



public String getAdreça() {
    return adreça;
}



public void setAdreça(String adreça) {
    this.adreça = adreça;
}



public String getDocument_identitat() {
    return document_identitat;
}



public void setDocument_identitat(String document_identitat) {
    this.document_identitat = document_identitat;
}



public LocalDate getData_naixement() {
    return data_naixement;
}



public void setData_naixement(LocalDate data_naixement) {
    this.data_naixement = data_naixement;
}



public String getTelefon() {
    return telefon;
}



public void setTelefon(String telefon) {
    this.telefon = telefon;
}



public String getEmail() {
    return email;
}



public void setEmail(String email) {
    this.email = email;
}








}
