package com.hotel.hotel.model;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Empleat extends Persona{
    
    private int id;
    private String llocFeina;
    private LocalDate data_contractacio;
    private double salariBrut;


    @Enumerated(EnumType.STRING)
    private EstatLaboral estat;


    public Empleat() {
        
    }



    public Empleat(Long id, String nom, String cognom, String adreça, String document_identitat,
        LocalDate data_naixement, String telefon, String email, int id2, String llocFeina,
        LocalDate data_contractacio, double salariBrut, EstatLaboral estat) {
        super(id, nom, cognom, adreça, document_identitat, data_naixement, telefon, email);
        this.id = id2;
        this.llocFeina = llocFeina;
        this.data_contractacio = data_contractacio;
        this.salariBrut = salariBrut;
        this.estat = estat;
    }
    



    
}
