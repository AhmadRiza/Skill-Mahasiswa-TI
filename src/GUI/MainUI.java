/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data2.*;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ahmad Riza
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    private final int COL_NIM = 0;
    private final int COL_NAMA = 1;
    private final int COL_ANGKATA = 2;
    private final int COL_JK = 3;
    private final int COL_HP = 4;
    private final int COL_SKILL = 5;

    private int searchBy = 0;
    private String where = "";
    private String skills = "";
    private String filterGender = "";
    private String sortBy = "";
    private String order = "";
    public static String[][] result;
    public static DataHandler db;
    public static Array arrayHandler;

    public MainUI() {
        initComponents();
        db = new DataHandler();
        String nama = "si ";
        char a = 'a';
        radAll.setSelected(true);
        ///test
        for (int i = 0; i < 30; i++) {
            db.input("166500" + i, nama + a, 'L', 2015, "08861996", "java php");
            a++;
        }
        a = 'a';
        for (int i = 0; i < 30; i++) {
            db.input("176500" + i, nama + a, 'L', 2015, "08861996", "phyton");
            a++;
        }
        for (int i = 0; i < 30; i++) {
            db.input("186500" + i, nama + a, 'P', 2016, "08861977", "Html+CSS");
            a++;
        }

        filterTable();
    }

    public boolean search() {
        if (!where.equals("")) {
            arrayHandler = new Array();
            arrayHandler.insert(result);
            result = arrayHandler.search(searchBy, where);
            if (result == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void updateVar() {
        this.where = txtFind.getText().trim();
        if (optFind.getSelectedIndex() == 0) {
            this.searchBy = 0;
        } else if(optFind.getSelectedIndex()==1){
            this.searchBy = 1;
        }else if(optFind.getSelectedIndex()==2){
            this.searchBy=2;
        }else{
            this.searchBy=4;
        }
        this.sortBy = optSort.getSelectedItem().toString();
        this.order = optOrder.getSelectedItem().toString();
        this.skills = "";

        if (chkJava.isSelected()) {
            skills += " java";
        }
        if (chkPhp.isSelected()) {
            skills += " php";
        }
        if (chkCPP.isSelected()) {
            skills += " c++";
        }
        if (chkCSub.isSelected()) {
            skills += " c#";
        }
        if (chkHtml.isSelected()) {
            skills += " Html+CSS";
        }
        if (chkPhyton.isSelected()) {
            skills += " phyton";
        }
        skills = skills.trim();
        System.out.println(skills);
        result = db.getResult(sortBy, order);
    }

    public void filterTable() {
        updateVar();
        updateTableAll();
        filterSkill();
        filterJK();
        if (!search()) {
            JOptionPane.showMessageDialog(null, "Not found!");
            updateTableAll();
            showRow();
        } else {
            updateTableAll();
            showRow();
        }
    }

    public void showRow() {
        TableModel dtm = tblMahasiswa.getModel();
        lblResult.setText("Result = " + dtm.getRowCount() + " row");
    }

    public void retreveTable() {
        TableModel dtm = tblMahasiswa.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        String[][] tableData = new String[nRow][nCol];

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j).toString();
            }
        }
        result = tableData;
    }

    //filter JK
    public void filterJK() {
        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);
        if (!filterGender.equals("")) {
            for (int i = 0; i < result.length; i++) {
                if (result[i][COL_JK].equals(filterGender)) {
                    Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_ANGKATA], result[i][COL_JK], result[i][COL_HP], result[i][COL_SKILL]};
                    model.addRow(row);
                }
            }
        } else {
            return;
        }
        retreveTable();
    }

    ////////////////////////filter skill
    public boolean checkSkill(String s) {
        String[] sk = skills.split(" ");
        String[] sk2 = s.split(" ");
        for (int i = 0; i < sk.length; i++) {
            for (int j = 0; j < sk2.length; j++) {
                if (sk[i].equals(sk2[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private void filterSkill() {
        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);//reset the table
        if (!skills.equals("")) {
            for (int i = 0; i < result.length; i++) {
                if (checkSkill(result[i][COL_SKILL])) {
                    Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_ANGKATA], result[i][COL_JK], result[i][COL_HP], result[i][COL_SKILL]};
                    model.addRow(row);
                }
            }
        } else {
            return;
        }
        retreveTable();
    }

    public void updateTableAll() {
//        updateVar();
        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);
        if (result==null||result.length==0) {
            return ;
        }
        for (int i = 0; i < result.length; i++) {
            Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_ANGKATA], result[i][COL_JK], result[i][COL_HP], result[i][COL_SKILL]};
            model.addRow(row);
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

        jPanel1 = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnFind = new javax.swing.JButton();
        chkJava = new javax.swing.JCheckBox();
        chkPhp = new javax.swing.JCheckBox();
        chkHtml = new javax.swing.JCheckBox();
        chkCPP = new javax.swing.JCheckBox();
        chkCSub = new javax.swing.JCheckBox();
        chkPhyton = new javax.swing.JCheckBox();
        optFind = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        optSort = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        radLK = new javax.swing.JRadioButton();
        radPR = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        radAll = new javax.swing.JRadioButton();
        optOrder = new javax.swing.JComboBox<>();
        lblResult = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMahasiswa = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuInsert = new javax.swing.JMenuItem();
        menuDelete = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Skill Mahasiswa TI");

        txtFind.setToolTipText("Insert Nama");
        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });
        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html>\n<b>\nSKILL<br> MAHASISWA <br>TI\n</b>\n</htmll>");

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        chkJava.setText("java");

        chkPhp.setText("php");

        chkHtml.setText("Html&Css");

        chkCPP.setText("C++");

        chkCSub.setText("C#");

        chkPhyton.setText("Phyton");

        optFind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIM", "NAMA", "ANGKATAN", "NO. HP" }));
        optFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optFindKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optFind, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkJava)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkPhp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkHtml)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCPP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCSub)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkPhyton)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optFind, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkJava)
                            .addComponent(chkPhp)
                            .addComponent(chkHtml)
                            .addComponent(chkCPP)
                            .addComponent(chkCSub)
                            .addComponent(chkPhyton))))
                .addContainerGap())
        );

        jLabel2.setText("Sort By :");

        optSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIM", "NAMA", "ANGKATAN" }));

        jLabel3.setText("Filter :");

        radLK.setText("Laki-laki");
        radLK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radLKActionPerformed(evt);
            }
        });

        radPR.setText("Perempuan");
        radPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPRActionPerformed(evt);
            }
        });

        jButton1.setText("Apply");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSort.setText("Apply");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        radAll.setText("All");
        radAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAllActionPerformed(evt);
            }
        });

        optOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASC", "DESC" }));

        lblResult.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblResult.setText("Result = 0 row");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optOrder, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optSort, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radLK)
                                    .addComponent(radAll)
                                    .addComponent(radPR)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblResult)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(72, 72, 72)))
                            .addComponent(jLabel3))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(optOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSort)
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radLK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radPR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(lblResult)
                .addGap(58, 58, 58))
        );

        tblMahasiswa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIM", "Nama", "Angkatan", "Jenis Kelamin", "No. Hp", "Skills"
            }
        ));
        tblMahasiswa.setEnabled(false);
        jScrollPane1.setViewportView(tblMahasiswa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        menuInsert.setText("Insert item");
        menuInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInsertActionPerformed(evt);
            }
        });
        jMenu1.add(menuInsert);

        menuDelete.setText("Delete item");
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteActionPerformed(evt);
            }
        });
        jMenu1.add(menuDelete);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Save");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator2);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        menuAbout.setText("Help");

        jMenuItem4.setText("About");
        menuAbout.add(jMenuItem4);

        jMenuBar1.add(menuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindActionPerformed

    private void radLKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radLKActionPerformed
        // TODO add your handling code here:
        radAll.setSelected(false);
        radPR.setSelected(false);
        this.filterGender = "L";
    }//GEN-LAST:event_radLKActionPerformed

    private void radPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPRActionPerformed
        // TODO add your handling code here:
        radLK.setSelected(false);
        radAll.setSelected(false);
        this.filterGender = "P";
    }//GEN-LAST:event_radPRActionPerformed

    private void radAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAllActionPerformed
        // TODO add your handling code here:
        radLK.setSelected(false);
        radPR.setSelected(false);
        this.filterGender = "";
    }//GEN-LAST:event_radAllActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_btnSortActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menuInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInsertActionPerformed
        // TODO add your handling code here:
        new Add().setVisible(true);
        filterTable();
    }//GEN-LAST:event_menuInsertActionPerformed

    private void txtFindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            filterTable();
        }
    }//GEN-LAST:event_txtFindKeyPressed

    private void optFindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optFindKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            filterTable();
        }
    }//GEN-LAST:event_optFindKeyPressed

    private void menuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteActionPerformed
        // TODO add your handling code here:
        new Delete().setVisible(true);
    }//GEN-LAST:event_menuDeleteActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_menuExitActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSort;
    private javax.swing.JCheckBox chkCPP;
    private javax.swing.JCheckBox chkCSub;
    private javax.swing.JCheckBox chkHtml;
    private javax.swing.JCheckBox chkJava;
    private javax.swing.JCheckBox chkPhp;
    private javax.swing.JCheckBox chkPhyton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lblResult;
    private javax.swing.JMenu menuAbout;
    private javax.swing.JMenuItem menuDelete;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuInsert;
    private javax.swing.JComboBox<String> optFind;
    private javax.swing.JComboBox<String> optOrder;
    private javax.swing.JComboBox<String> optSort;
    private javax.swing.JRadioButton radAll;
    private javax.swing.JRadioButton radLK;
    private javax.swing.JRadioButton radPR;
    private javax.swing.JTable tblMahasiswa;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables
}