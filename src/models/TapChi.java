/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author myPC
 */
public class TapChi {
    private String maTc;
    private String tenTc ;
    private int soLuong;
    private String loai ;
    private String nxb;
    private String ngayXb;

    public TapChi() {
    }

    public TapChi(String maTc, String tenTc, int soLuong, String nxb,String ngayXb, String loai) {
        this.maTc = maTc;
        this.tenTc = tenTc;
        this.soLuong = soLuong;
        this.nxb = nxb;
        this.ngayXb = ngayXb;
        this.loai = loai;
    }

    public String getMaTc() {
        return maTc;
    }

    public void setMaTc(String maTc) {
        this.maTc = maTc;
    }

    public String getTenTc() {
        return tenTc;
    }

    public void setTenTc(String tenTc) {
        this.tenTc = tenTc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getNgayXb() {
        return ngayXb;
    }

    public void setNgayXb(String ngayXb) {
        this.ngayXb = ngayXb;
    }

    
}
