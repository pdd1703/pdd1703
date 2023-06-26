/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import data.ConnectDB;
import static data.ConnectDB.stmt;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import models.Gia;

/**
 *
 * @author myPC
 */
public class giaController {
    loaiController loaiCTL = new loaiController();
    public giaController() {
        new ConnectDB();
    }
    public Gia getGia(String maGia){
        Gia gia = new Gia();
        String sq = "SELECT `maGia`, `moTa` FROM `gia` WHERE `maGia` = '"+maGia+"'";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                gia.setMaGia(rs.getString("maGia"));
                gia.setMoTa(rs.getString("moTa"));
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        return gia;
    }
    public int laySoGia(){
        int sl = 0;
        String sq = "SELECT COUNT(`maGia`) FROM `gia`";
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
    public Gia[] getMangGia(){
        int i = -1;
        Gia[] gia = new Gia[laySoGia()];
        String sq = "SELECT `maGia`, `moTa` FROM `gia`";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                i++;
                gia[i] = new Gia(rs.getString("maGia"),rs.getString("moTa"));
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        return gia;
    }
    
    public void capNhapGia(Gia gia){
        String sq = "UPDATE `gia` SET `moTa`='"+gia.getMoTa()+"' WHERE `maGia` = '" + gia.getMaGia()+"'";
        
        try {
            stmt.executeUpdate(sq);
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
    }
    
    public void xoaGia(String maGia){
        String[] maLoai = loaiCTL.getMaLoai(maGia);
        for(int i = 0; i<maLoai.length; i++){
            String sq01 = "DELETE FROM `tapchi` WHERE `maLoai` = '"+ maLoai[i] +"';";
            String sq02 = "ALTER TABLE `tapchi` DROP FOREIGN KEY `fk_maLoai`;";
            String sq03 =  "DELETE FROM `loai` WHERE `maLoai` = '" + maLoai[i]+"';";
            String sq04 =  "ALTER TABLE `tapchi` ADD CONSTRAINT `fk_maLoai` FOREIGN KEY(`maLoai`) REFERENCES `loai`(`maLoai`);";
            try {
                stmt.executeUpdate(sq01);
                stmt.executeUpdate(sq02);
                stmt.executeUpdate(sq03);
                stmt.executeUpdate(sq04);
            } catch (Exception e) {
                System.err.println("Xoa cac tap chi loai " + maLoai[i]+"o gia "+maGia+" khong thanh cong!");
            }
        }
        
        
        String sq = "DELETE FROM `loai` WHERE `maGia` = '"+ maGia +"';";
        String sq1 = "ALTER TABLE `loai` DROP FOREIGN KEY `fk_maGia`;";
        String sq2 =  "DELETE FROM `gia` WHERE `maGia` = '" + maGia+"';";
        String sq3 =  "ALTER TABLE `loai` ADD CONSTRAINT `fk_maGia` FOREIGN KEY(`maGia`) REFERENCES `gia`(`maGia`);";
        
        try {
            stmt.executeUpdate(sq);
            stmt.executeUpdate(sq1);
            stmt.executeUpdate(sq2);
            stmt.executeUpdate(sq3);
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
    }
    public void themGia(Gia gia){
        String sq = "INSERT INTO `gia`(`maGia`, `moTa`) VALUES ('"+gia.getMaGia()+"','"+gia.getMoTa()+"')";
        
        try {
            stmt.executeUpdate(sq);
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
    }
    //Ham cai dat comboBox
    public void loadComboBox(JComboBox box){
        String sq = "SELECT `maGia` FROM `gia`";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                box.addItem(rs.getString("maGia"));
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
    }
    //Ham lay thong tin mo ta theo ma
    public String layMoTa(String maGia){
        String moTa = "Khong co mo ta!";
        String sq = "SELECT `moTa` FROM `gia` WHERE `maGia` = '"+maGia+"'";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                moTa = rs.getString("moTa");
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        return moTa;
    }
    //Ham lay gia cuoi cung
    public int layMaCuoi(){
       int maCuoi = 1;
       String maCuoiString = "";
       String sq = "SELECT `maGia`, `moTa` FROM `gia`";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                maCuoiString =  rs.getString("maGia");
                maCuoiString = maCuoiString.substring(1, maCuoiString.length());
                maCuoi = Integer.parseInt(maCuoiString) + 1;
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        
        return maCuoi;
    }
    public static void main(String[] args) {
        giaController giaController = new giaController();
        Gia[] gia = giaController.getMangGia();
        System.out.println(gia[0].getMoTa());
    }
}
