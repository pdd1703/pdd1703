/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.sql.ResultSet;
import data.ConnectDB;
import static data.ConnectDB.stmt;
import java.sql.SQLException;
import models.Loai;

/**
 *
 * @author myPC
 */
public class loaiController {

    public loaiController() {
        new ConnectDB();
    }
    
    public String[] getMaLoai(String maGia){
        String[] loaiM = null;
        int n = 0;
        String sq = "Select count(`maLoai`) from `loai` where `maGia` = '"+maGia+"'";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                n = Integer.parseInt(rs.getString(1));
                loaiM = new String[n];
            }
        } catch (Exception e) {
            System.out.println("Loi tim mang co ma gia la " + maGia+": "+ e.getMessage());
        }
        int i = -1;
        String sq2 = "Select `maLoai`from `loai` where `maGia` = '" + maGia + "'";
        try {
            ResultSet rs = stmt.executeQuery(sq2);
            while(rs.next()){
                i++;
                loaiM[i] = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("Loi tao mang: "+ e.getMessage());
        }
        return loaiM;
    }
    public int laySoLuong(){
        int sl = 0;
        String sq = "SELECT COUNT(`maLoai`) FROM `loai`";
         try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                sl =  Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        return sl;
    }
    public Loai[] getLoaiList(){
        int i = -1;
        Loai[] loai = new Loai[laySoLuong()];
        String sq = "SELECT * FROM `loai`";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                i++;
                loai[i] = new Loai(rs.getString("maLoai"),rs.getString("tenLoai"),rs.getString("maGia"));
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        return loai;
    }
    public int layMaCuoi(){
       int maCuoi = 1;
       String maCuoiString = "";
       String sq = "SELECT `maLoai` FROM `loai`";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                maCuoiString =  rs.getString("maLoai");
                maCuoiString = maCuoiString.substring(1, maCuoiString.length());
                maCuoi = Integer.parseInt(maCuoiString) + 1;
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        
        return maCuoi;
    }
    //Ham them loai moi
    public void insertLoai(String tenLoai, String maGia){
        String maLoai = "L"+layMaCuoi();
        String sq = "INSERT INTO `loai`(`maLoai`, `tenLoai`, `maGia`) VALUES ('" + maLoai + "','" + tenLoai + "','" + maGia + "')";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
            System.err.println("Loi them loai: " + e.getMessage());
        }
    }
    //Ham xoa loai
    public void deleteLoai(String maLoai){
        String sq1 = "UPDATE `tapchi` SET `maLoai`= null WHERE `maLoai` = '"+maLoai+"';";
        String sq2 = "DELETE FROM `loai` WHERE `maLoai` = '"+maLoai+"';";
        try {
            stmt.executeUpdate(sq1);
            stmt.executeUpdate(sq2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //Cap nhap loai
    public void updateLoai(Loai loai, String tenLoai, String maGia){
        String sq1 = "UPDATE `loai` SET `tenLoai`='"+ tenLoai+"',`maGia`='"+maGia+"' WHERE `maLoai` = '"+loai.getMaLoai()+"';";
        try {
            stmt.executeUpdate(sq1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    //Lay ma tu ten
    public String getMa(String name){
        String ma = "";
        String sq = "Select `maLoai` from `loai` where `tenLoai`  = '"+name+"'";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                ma = rs.getString("maLoai");
            }
        } catch (Exception e) {
        }
        return ma;
    }
    //Lay ten tu ma
    public String getTen(String ma){
        String ten = "";
        String sq = "Select `tenLoai` from `loai` where `maLoai`  = '"+ma+"'";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                ten = rs.getString("tenLoai");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ten;
    }
    public String getGia(String name){
        String ma = "";
        String sq = "Select `maGia` from `loai` where `tenLoai`  = '"+name+"'";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                ma = rs.getString("maGia");
            }
        } catch (Exception e) {
        }
        return ma;
    }
//    public static void main(String[] args) {
//        loaiController loaiController = new loaiController();
//        System.out.println(loaiController.getTen("L1"));
//    }
}
