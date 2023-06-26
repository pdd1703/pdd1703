/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.sql.ResultSet;
import data.ConnectDB;
import static data.ConnectDB.stmt;
import java.sql.SQLException;
import models.TapChi;
/**
 *
 * @author myPC
 */
public class tapChiController {
    loaiController loaiCTL = new loaiController();
    nxbController nxbCTL = new nxbController();
    public tapChiController() {
    }
    //Ham dem
    public int countTC(String maLoai, boolean all){
        int i = 0;
        String sq = "Select count(`maTc`) from `tapchi`";
        if(maLoai.equals("")){
            if(all == false)
                sq = "SELECT count(`maTc`) FROM `tapchi` where `soLuong` > 0";
        }else{
            if(all == false){
                sq = "SELECT count(`maTc`) FROM `tapchi` where `soLuong` > 0 and `maLoai` = '" + maLoai +"'";
            }else{
                sq = "SELECT count(`maTc`) FROM `tapchi` where `maLoai` = '" + maLoai +"'";
            }
        }
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                i = Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.err.println("i: " + i);
        return i;
    }
    
    //Ham lay du lieu ve model
    public TapChi[] getData(String maLoai, boolean all){
        int i = -1;
        TapChi[] tc = new TapChi[countTC("",true)];
        String sq = "SELECT * FROM `tapchi`";
        if(maLoai.equals("")){
            if(all == false)
            {
                sq = "SELECT * FROM `tapchi` where `soLuong` > 0";
                tc = new TapChi[countTC("",false)];
            }
                
        }else{
            if(all == false){
                sq = "SELECT * FROM `tapchi` where `soLuong` > 0 and `maLoai` = '" + maLoai +"'";
                tc = new TapChi[countTC(maLoai,false)];
            }else{
                sq = "SELECT * FROM `tapchi` where `maLoai` = '" + maLoai +"'";
                tc = new TapChi[countTC(maLoai,true)];
            }
        }
        try {
            System.out.println(sq);
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                i++;
                tc[i] = new TapChi(
                        rs.getString("maTc"),
                        rs.getString("tenTc"), 
                        Integer.parseInt(rs.getString("soLuong")),
                        rs.getString("maNxb"),
                        rs.getString("ngayXb"),
                        rs.getString("maLoai")
                        );
            }
            for (TapChi tc1 : tc) {
                tc1.setLoai(loaiCTL.getTen(tc1.getLoai()));
                tc1.setNxb(nxbCTL.getTen(tc1.getNxb()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tc;
    }
    //Tìm kiếm tương đối
    public TapChi[] Search(String key){
        TapChi[] tc = null;
        int i =-1;
        String sq0 = "SELECT count(`maTc`) FROM `tapchi` WHERE `tenTc` LIKE '%"+key+"%';";
        String sq = "SELECT * FROM `tapchi` WHERE `tenTc` LIKE '%"+key+"%';";
        try {
            ResultSet rs= stmt.executeQuery(sq0);
            while(rs.next()){
                int n = Integer.parseInt(rs.getString(1));
                tc = new TapChi[n];
            }
        } catch (Exception e) {
        }
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                i++;
                tc[i] = new TapChi(
                        rs.getString("maTc"),
                        rs.getString("tenTc"), 
                        Integer.parseInt(rs.getString("soLuong")),
                        rs.getString("maNxb"),
                        rs.getString("ngayXb"),
                        rs.getString("maLoai")
                        );
            }
            for (TapChi tc1 : tc) {
                tc1.setLoai(loaiCTL.getTen(tc1.getLoai()));
                tc1.setNxb(nxbCTL.getTen(tc1.getNxb()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tc;
    }
    //Ham lay ma cuoi 
    public int layMaCuoi(){
       int maCuoi = 1;
       String maCuoiString = "";
       String sq = "SELECT * FROM `tapchi`";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                maCuoiString =  rs.getString("maTc");
                maCuoiString = maCuoiString.substring(2, maCuoiString.length());
                maCuoi = Integer.parseInt(maCuoiString)+1;
            }
        } catch (SQLException ex) {
            System.err.println("Loi: " + ex.getMessage());
        }
        System.out.println("ma cuoi: "+maCuoi);
        return maCuoi;
    }
    //Them tap chi
    public void insertTapChi(TapChi tc){
        String maNxbString = nxbCTL.getMa(tc.getNxb());
        String maLoaiString = loaiCTL.getMa(tc.getLoai());
        
        String sq = "INSERT INTO `tapchi`(`maTc`, `tenTc`, `soLuong`, `maNxb`, `ngayXb`, `maLoai`, `hinhanh`) "
                + "VALUES ('"+tc.getMaTc()
                +"','"+tc.getTenTc()+"',"
                +tc.getSoLuong()
                +",'"+maNxbString
                +"','"+tc.getNgayXb()
                +"','"+maLoaiString
                +"',null)";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }
    //Ham xoa 1 cuốn tạp chí
    
    public void deleteTapChi(String ma){
        String sq = "Delete from `tapchi` where `maTc` = '"+ma+"'";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }//Ham xóa tất cả cuốn tạp chí
    
    public void deleteAllTapChi(){
        String sq = "Delete from `tapchi` where 1=1";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }
    
    //Ham cap nhap 1 cuốn tạp chí
    public void updateTapChi(TapChi tc){
        String maNxbString = nxbCTL.getMa(tc.getNxb());
        String maLoaiString = loaiCTL.getMa(tc.getLoai());
        
        String sq = "UPDATE `tapchi` SET "
                + "`tenTc`='"+tc.getTenTc()+"',`soLuong`="+tc.getSoLuong()+","
                + "`maNxb`='"+maNxbString+"',`ngayXb`='"+tc.getNgayXb()+"',"
                + "`maLoai`='"+maLoaiString+"'"
                + "WHERE `maTc` = '"+tc.getMaTc()+"'";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }
     public static void main(String[] args) {
        tapChiController tcCTL = new tapChiController();
        TapChi[] tc = tcCTL.getData("",true);
        for(int i = 0; i< tc.length; i++){
            System.out.println(tc[i].getTenTc());
        }
    }
}
