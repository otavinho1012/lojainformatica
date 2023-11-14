/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojainformatica;

/**
 *
 * @author oaugu
 */
public class Computador {
    public int id;
    public static  String marca = "Otavio Augusto Reis Almeida";
    public String hd;
    public String processador;
    
    public Computador(String hd,String processador) {
         this.hd = hd;
           this.processador = processador; 
    }
    
    public Computador() {
    }
    
    public Computador( int id, String hd,String processador) {
        this.marca = "Otavio Augusto Reis Almeida";
        this.id = id;
        this.hd = hd;
        this.processador = processador; 
    }
    public Computador( int id, String hd,String processador,String marca) {
        this.marca = marca;
        this.id = id;
        this.hd = hd;
        this.processador = processador; 
    }

    public String getMarca() {
        return marca;
    }
    public String getHD() {
        return hd;
    }

    public void setHD(String hd) {
        this.hd = hd;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
    public Object getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
               
}

