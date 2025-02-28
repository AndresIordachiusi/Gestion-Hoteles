package com.hotel.hotel.model;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Client extends Persona {
    
    private int id;
    private LocalDate data_registre;



    @Enumerated(EnumType.STRING)
    private CategoriaClient tipus;


    
    private int targeta_credit;

    public Client() {
        
    }

    
    public Client(Long id, String nom, String cognom, String adreça, String document_identitat,
        LocalDate data_naixement, String telefon, String email, int id2, LocalDate data_registre,
        CategoriaClient tipus, int targeta_credit) {
        super(id, nom, cognom, adreça, document_identitat, data_naixement, telefon, email);
        this.id = id2;
        this.data_registre = data_registre;
        this.tipus = tipus;
        this.targeta_credit = targeta_credit;
    }





    
}