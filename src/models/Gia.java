/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author myPC
 */
public class Gia {
    private String maGia;
    private String moTa;

    public Gia() {
    }

    public Gia(String maGia, String moTa) {
        this.maGia = maGia;
        this.moTa = moTa;
    }
    
    public String getMaGia() {
        return maGia;
    }

    public void setMaGia(String maGia) {
        this.maGia = maGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    
}
