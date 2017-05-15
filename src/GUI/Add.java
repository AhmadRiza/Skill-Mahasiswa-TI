/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data2.Array;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad Riza
 */
public class Add extends javax.swing.JFrame {

    private String NIM;
    private String Nama;
    private char JK;
    private int Angkatan;
    private String HP;
    private String Skills = "";
    private Array arrayHandler;
    private String[][] result;
    /**
     * Creates new form Add
     */
    public Add() {
        initComponents();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNIM = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtAngkatan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        chkJava = new javax.swing.JCheckBox();
        chkPhp = new javax.swing.JCheckBox();
        chkCPP = new javax.swing.JCheckBox();
        chkCSub = new javax.swing.JCheckBox();
        chkHtmlCSS = new javax.swing.JCheckBox();
        chkPhyton = new javax.swing.JCheckBox();
        btnADD = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        comboJK = new javax.swing.JComboBox<>();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tambah Mahasiswa");
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TAMBAH MAHASISWA");

        jLabel3.setText("NIM :");

        jLabel4.setText("NAMA :");

        jLabel5.setText("ANGKATAN :");

        txtNIM.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txtNama.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        txtAngkatan.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("NO. HP :");

        jLabel7.setText("SKILL :");

        chkJava.setText("java");

        chkPhp.setText("php");

        chkCPP.setText("C++");

        chkCSub.setText("C#");

        chkHtmlCSS.setText("Html&CSS");

        chkPhyton.setText("Phyton");

        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel8.setText("JENIS KELAMIN :");

        comboJK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkJava)
                    .addComponent(chkCPP)
                    .addComponent(chkHtmlCSS))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPhp)
                    .addComponent(chkPhyton)
                    .addComponent(chkCSub))
                .addGap(134, 134, 134))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNama)
                                    .addComponent(txtNIM)
                                    .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHP, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboJK, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboJK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(chkJava)
                    .addComponent(chkPhp))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCPP)
                    .addComponent(chkCSub))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkHtmlCSS)
                    .addComponent(chkPhyton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        // TODO add your handling code here:
        //cek kosong
        if ("".equals(txtNIM.getText())||"".equals(txtNama.getText())||"".equals(txtAngkatan.getText())) {
            JOptionPane.showMessageDialog(null, "Input Salah");
            return;
        }
        
        this.NIM = txtNIM.getText().trim();
        
        //pencarian
        this.arrayHandler = new Array();
        arrayHandler.insert(MainUI.db.getResult("NIM", "ASC"));
        //jika ada nim sama
        if (arrayHandler.findNIM(NIM)!=null) {
            JOptionPane.showMessageDialog(null, "NIM sudah ada!");
            return;
        }
        
        txtNIM.setText("");
        this.Angkatan = Integer.parseInt(txtAngkatan.getText().trim());
        txtAngkatan.setText("");
        this.Nama = txtNama.getText().trim();
        txtNama.setText("");
        this.HP = txtHP.getText().trim();
        txtHP.setText("");
        this.JK = comboJK.getSelectedItem().toString().charAt(0);
        this.Skills="";      
        if (chkJava.isSelected()) {
            Skills += " java";
        }
        if (chkPhp.isSelected()) {
            Skills += " php";
        }
        if (chkCPP.isSelected()) {
            Skills += " c++";
        }
        if (chkCSub.isSelected()) {
            Skills += " c#";
        }
        if (chkHtmlCSS.isSelected()) {
            Skills += " Html+CSS";
        }
        if (chkPhyton.isSelected()) {
            Skills += " phyton";
        }
        Skills = Skills.trim();//hapus spasi
        
        

        chkJava.setSelected(false);
        chkPhp.setSelected(false);
        chkCPP.setSelected(false);
        chkCSub.setSelected(false);
        chkHtmlCSS.setSelected(false);
        chkPhyton.setSelected(false);

        MainUI.db.input(NIM, Nama, JK, Angkatan, HP, Skills);

    }//GEN-LAST:event_btnADDActionPerformed
    
    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnCancel;
    private javax.swing.JCheckBox chkCPP;
    private javax.swing.JCheckBox chkCSub;
    private javax.swing.JCheckBox chkHtmlCSS;
    private javax.swing.JCheckBox chkJava;
    private javax.swing.JCheckBox chkPhp;
    private javax.swing.JCheckBox chkPhyton;
    private javax.swing.JComboBox<String> comboJK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtAngkatan;
    private javax.swing.JTextField txtHP;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}