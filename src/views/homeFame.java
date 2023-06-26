/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.loaiController;
import controllers.tapChiController;
import data.excelFile;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Loai;
import models.NguoiDung;
import models.TapChi;
import util.imageProcess;

/**
 *
 * @author myPC
 */
public class homeFame extends javax.swing.JFrame {
    loaiController loaiCTL = new loaiController();
    tapChiController tcCTL = new tapChiController();
    TapChi[] TC = tcCTL.getData("",true);
    DefaultTableModel modelHome = null;
    DefaultComboBoxModel modelLoai = null;
    NguoiDung user = new NguoiDung();
    imageProcess imagePRC = new imageProcess();
    /**
     * Creates new form homeFame
     */
    public homeFame() {
        initComponents();
        init();
    }
    public homeFame(NguoiDung u) {
        user = u;
        initComponents();
        init();
        userNameLabel.setText(user.getTenND());
    }
    public void init(){
        setTitle("Quản lý tạp chí");
        loadTable();
        loadLoaiCB();
        setInfor();
        setBounds(250, 100, 815, 405);
        
        //set ICON
        ImageIcon icon2 = new ImageIcon("icon2.png");
        ImageIcon icon3 = new ImageIcon("icon3.png");
        ImageIcon icon4 = new ImageIcon("icon4.png");
        
        icon2 = imagePRC.scaleImage(icon2, visualLabel);
        icon3 = imagePRC.scaleImage(icon3, visualLabel);
        icon4 = imagePRC.scaleImage(icon4, anhLabel);
        
        setUserBTN.setIcon(icon2);
        exitBTN.setIcon(icon3);
        anhLabel.setIcon(icon4);
    }
    public void refresh(){
        loadTable();
        setInfor();
    }
    
    public void loadLoaiCB(){
        Loai[] loai = loaiCTL.getLoaiList();
        modelLoai = new DefaultComboBoxModel();
        modelLoai.addElement("All");
        for(int i = 0; i<loai.length; i++){
            modelLoai.addElement(loai[i].getTenLoai());
        }
        LoaiCB.setModel(modelLoai);
    }
    public void loadTable(){
        TC = tcCTL.getData(loaiCTL.getMa((String) LoaiCB.getSelectedItem()),isAll());
        modelHome = (DefaultTableModel)homeTable.getModel();
        modelHome.setRowCount(0);
        if(TC.length>0){
            for(int i = 0; i<TC.length; i++){
                String[] data = {
                    TC[i].getMaTc(),
                    TC[i].getTenTc(),
                    String.valueOf(TC[i].getSoLuong()),
                    TC[i].getNxb(),
                    TC[i].getNgayXb(),
                    TC[i].getLoai()
                };
                modelHome.addRow(data);
            }
        }
    }
    //Ham seleted{
    public boolean isAll(){
        return !slHienCoCheckBox.isSelected();
    }
    //Ham thong tin phân tích
    public void setInfor(){
        int count = 0;
        int max = 0;
        tenTcMaxField.setText("");
        String ten = "";
        for (TapChi TC1 : TC) {
            count++;
            if (TC1.getSoLuong() >= max) {
                max = TC1.getSoLuong();
            }
        }
        for (TapChi TC1 : TC) {
            if (TC1.getSoLuong() == max) {
                ten = ten.concat(TC1.getTenTc() + "\n");
            }
        }
        sumLabel.setText(String.valueOf(count));
        tenTcMaxField.setText(ten);
    }
    ///--------------------------------------
    public void reloadFunc(){
        refresh();
    }
    public void hienCoFun(){
        loadTable();
    }
    public void searchFunc(){
        resultSearchField.setText("");
        String key = searchField.getText();
        String kq = "";
        String gia = "";
        TapChi[] tcKey = tcCTL.Search(key);
        for (TapChi tapChi : tcKey) {
            gia = loaiCTL.getGia(tapChi.getLoai());
            kq = kq.concat(gia+" - "+tapChi.getTenTc()+"\n");
        }
        resultSearchField.setText(kq);
    }
    //ham them tap chi moi
    public void themFunc(){
        themFrame themFrame = new themFrame();
        themFrame.setVisible(true);
    }
    public void caiDatFunc(){
        settingFrame settFrame = new settingFrame();
        settFrame.setVisible(true);
    }
    public void nhapExcelFunc(){
        excelFile exCTL = new excelFile();
        exCTL.importExcel(homeTable);
    }
    public void xuatExcelFunc(){
        excelFile exCTL = new excelFile();
        exCTL.exportExcel(homeTable);
    }
    public void chiTietFunc(){
        int i = homeTable.getSelectedRow();
        if(i>-1){
            chiTietFrame chiTietFrame = new chiTietFrame(TC[i]);
            chiTietFrame.setVisible(true);
        }
    }
    //Ham cài đặt thông tin người dùng
    public void userSettingFunc(){
        userSettingFrame userSettingFrame = new userSettingFrame(user);
        userSettingFrame.setVisible(true);
    }
    //Ham load data tu bang len CSDL
    public void updateToDB(){
        tcCTL.deleteAllTapChi();
        TapChi tc = new TapChi();
        for (int row = 0; row < homeTable.getRowCount(); row++) {
            tc.setMaTc((String) homeTable.getValueAt(row, 0));
            tc.setTenTc((String) homeTable.getValueAt(row, 1));
            tc.setSoLuong(Integer.parseInt((String) homeTable.getValueAt(row, 2)));
            tc.setNxb((String) homeTable.getValueAt(row, 3));
            tc.setNgayXb((String) homeTable.getValueAt(row, 4));
            tc.setLoai((String) homeTable.getValueAt(row, 5));
            tcCTL.insertTapChi(tc);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        homeTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        themBTN = new javax.swing.JButton();
        reloadBTN = new javax.swing.JButton();
        LoaiCB = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        slHienCoCheckBox = new javax.swing.JCheckBox();
        ReloadLoaiLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sumLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        resultSearchField = new javax.swing.JTextArea();
        searchField = new javax.swing.JTextField();
        searchBTN = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tenTcMaxField = new javax.swing.JTextArea();
        updateBTN = new javax.swing.JButton();
        visualLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        anhLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        exitBTN = new javax.swing.JButton();
        setUserBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên ", "SL", "NXB", "Ngày xuất bản", "Loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(homeTable);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setText("CHI TIẾT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("NHẬP");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("XUẤT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        themBTN.setText("THÊM");
        themBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBTNActionPerformed(evt);
            }
        });

        reloadBTN.setText("TẢI LẠI");
        reloadBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadBTNActionPerformed(evt);
            }
        });

        jButton9.setText("CÀI ĐẶT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        slHienCoCheckBox.setText("Hiện có");
        slHienCoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slHienCoCheckBoxActionPerformed(evt);
            }
        });

        ReloadLoaiLabel.setForeground(new java.awt.Color(153, 153, 153));
        ReloadLoaiLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ReloadLoaiLabel.setText("RL");
        ReloadLoaiLabel.setMinimumSize(new java.awt.Dimension(22, 22));
        ReloadLoaiLabel.setPreferredSize(new java.awt.Dimension(22, 22));
        ReloadLoaiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReloadLoaiLabelMouseClicked(evt);
            }
        });

        jLabel3.setText("Tổng  tạp chí trong bảng     =");

        sumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sumLabel.setText("0");

        jLabel5.setText("Tạp chí còn nhiều nhất");

        resultSearchField.setEditable(false);
        resultSearchField.setColumns(20);
        resultSearchField.setLineWrap(true);
        resultSearchField.setRows(10);
        jScrollPane4.setViewportView(resultSearchField);

        searchBTN.setText("TÌM");
        searchBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(searchBTN)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tenTcMaxField.setColumns(1);
        tenTcMaxField.setLineWrap(true);
        tenTcMaxField.setRows(10);
        jScrollPane3.setViewportView(tenTcMaxField);

        updateBTN.setText("Update");
        updateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTNActionPerformed(evt);
            }
        });

        visualLabel.setName("visualLabel"); // NOI18N
        visualLabel.setPreferredSize(new java.awt.Dimension(72, 23));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(visualLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ReloadLoaiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LoaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(slHienCoCheckBox))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reloadBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(themBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBTN))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(themBTN)
                            .addComponent(reloadBTN)
                            .addComponent(LoaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slHienCoCheckBox)
                            .addComponent(updateBTN))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReloadLoaiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(visualLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jLabel3)
                    .addComponent(sumLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(0, 40, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        anhLabel.setBackground(new java.awt.Color(204, 204, 204));
        anhLabel.setForeground(new java.awt.Color(102, 102, 102));
        anhLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        userNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(0, 102, 102));
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNameLabel.setText("Nobody");

        exitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBTNActionPerformed(evt);
            }
        });

        setUserBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setUserBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(setUserBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(exitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(setUserBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitBTN)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReloadLoaiLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReloadLoaiLabelMouseClicked
        // TODO add your handling code here:
        loadLoaiCB();
    }//GEN-LAST:event_ReloadLoaiLabelMouseClicked

    private void slHienCoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slHienCoCheckBoxActionPerformed
        // TODO add your handling code here:
        hienCoFun();
    }//GEN-LAST:event_slHienCoCheckBoxActionPerformed

    private void reloadBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadBTNActionPerformed
        // TODO add your handling code here:
        reloadFunc();
    }//GEN-LAST:event_reloadBTNActionPerformed

    private void searchBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBTNActionPerformed
        // TODO add your handling code here:
        searchFunc();
    }//GEN-LAST:event_searchBTNActionPerformed

    private void themBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBTNActionPerformed
        // TODO add your handling code here:
        themFunc();
    }//GEN-LAST:event_themBTNActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        caiDatFunc();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        nhapExcelFunc();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        xuatExcelFunc();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        chiTietFunc();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void exitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBTNActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn thoát chương trình?", 
                "Thoát", JOptionPane.YES_NO_OPTION);
        if(option == 0){
            dispose();
        }
    }//GEN-LAST:event_exitBTNActionPerformed

    private void setUserBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setUserBTNActionPerformed
        // TODO add your handling code here:
        userSettingFunc();
    }//GEN-LAST:event_setUserBTNActionPerformed

    private void updateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTNActionPerformed
        // TODO add your handling code here:
        updateToDB();
    }//GEN-LAST:event_updateBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(homeFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homeFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homeFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homeFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homeFame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> LoaiCB;
    private javax.swing.JLabel ReloadLoaiLabel;
    private javax.swing.JLabel anhLabel;
    private javax.swing.JButton exitBTN;
    private javax.swing.JTable homeTable;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton reloadBTN;
    private javax.swing.JTextArea resultSearchField;
    private javax.swing.JButton searchBTN;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton setUserBTN;
    private javax.swing.JCheckBox slHienCoCheckBox;
    private javax.swing.JLabel sumLabel;
    private javax.swing.JTextArea tenTcMaxField;
    private javax.swing.JButton themBTN;
    private javax.swing.JButton updateBTN;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel visualLabel;
    // End of variables declaration//GEN-END:variables
}
