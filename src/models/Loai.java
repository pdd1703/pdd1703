/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author myPC
 */
public class Loai {
    private String maLoai;
    private String tenLoai;
    private String maGia;

    public Loai(String maLoai, String tenLoai, String maGia) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.maGia = maGia;
    }

    public Loai() {
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMaGia() {
        return maGia;
    }

    public void setMaGia(String maGia) {
        this.maGia = maGia;
    }
    
}
