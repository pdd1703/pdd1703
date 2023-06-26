/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import data.ConnectDB;
import static data.ConnectDB.con;
import static data.ConnectDB.stmt;
import java.awt.Image;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author myPC
 */
public class imageProcess {
    
    public imageProcess() {
        new ConnectDB();
    }
//scaled Image
    public ImageIcon scaleImage(ImageIcon icon, JLabel label) {
        Image img = icon.getImage();

        //Kich thuoc anh
        float w_img = icon.getIconWidth();
        float h_img = icon.getIconHeight();

        //kich thuoc khung anh
        float w = label.getWidth();
        float h = label.getHeight();

        //lay ty le cua anh
        float tlK = w / h;
        System.err.println(tlK);
        float tl = w_img / h_img;

        //dat kich thuoc scale        
        if (tl>=tlK) {
            w_img = w;
            h_img = w_img / tl;
        } else if ( tl < tlK) {
            h_img = h;
            w_img = h_img * tl;
        } 
        
        //Lam tron
        System.out.print("(" + w + "," + h + ") " + tl + " => ");
        int wf = Math.round(w_img);
        int hf = Math.round(h_img);
        
        System.out.println("(" + w + "," + h + ")");
        Image sImg = img.getScaledInstance(wf, hf, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sImg);
        return icon;
    }
//Ham lay anh tho ID dang varbinary
    public byte [] getImageByID(String id){
        byte[] bin = null;
        String sq = "SELECT `hinhanh` FROM `tapchi` WHERE `maTc` = '"+id+"'";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                bin = rs.getBytes("hinhanh");
            }
        }catch(NumberFormatException | SQLException e){
        }
        return bin;
    }  
//update anh
    public void updateImage(String id, InputStream is){
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE `tapchi` SET `hinhanh`= ? WHERE `maTc`= '"+id+"'");
            ps.setBlob(1, is);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
