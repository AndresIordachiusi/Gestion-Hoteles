package com.hotel.hotel.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    
    private String nom;
    private String adreca;
    private String ciutat;



    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


    public Hotel() {
    }


    public Hotel(String nom, String adreca, String ciutat, Categoria categoria) {
        this.nom = nom;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.categoria = categoria;
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

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



    

    
}
