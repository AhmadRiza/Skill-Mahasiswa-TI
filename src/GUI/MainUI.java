/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataEngine.FileRW;
import DataEngine.DataHandler;
import DataEngine.Array;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
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
    private String filterSkillCond="";
    public static boolean saved;
    public static boolean empty;
    public static String[][] result;
    public static DataHandler db;
    public static Array arrayHandler;
    private FileRW file;

    /**
     * CONSTRUCTOR
     */
    public MainUI() {
        initComponents();
        myInitComponet();
        db = new DataHandler();
        file = new FileRW();

        radAll.setSelected(true);
        saved = true;

        //MUAT DATA DARI DISK
        try {
            if ((result = file.readFile()) != null) {
                for (int i = 0; i < result.length; i++) {
                    db.insert(result[i][COL_NIM], result[i][COL_NAMA].toUpperCase(), result[i][COL_JK].charAt(0), Integer.parseInt(result[i][COL_ANGKATA]), result[i][COL_HP], result[i][COL_SKILL]);
                }
                System.out.println(">data system created");
                filterTable();
            } else {
                System.out.println(">no content");
                empty = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //END

    /**
     * UPDATE SEMUA VARIABEL
     */
    public void updateVar() {
        this.where = txtFind.getText().trim().toLowerCase();
        //cek cari dengan apa
        if (optFind.getSelectedIndex() == 0) {
            this.searchBy = 0;
        } else if (optFind.getSelectedIndex() == 1) {
            this.searchBy = 1;
        } else if (optFind.getSelectedIndex() == 2) {
            this.searchBy = 2;
        } else {
            this.searchBy = 4;
        }
        //cek filter jk
        if (radLK.isSelected()) {
            this.filterGender = "L";
        } else if (radPR.isSelected()) {
            this.filterGender = "P";
        } else {
            this.filterGender = "";
        }
        //sortby inisialisasi
        this.sortBy = optSort.getSelectedItem().toString().toLowerCase();
        this.order = optOrder.getSelectedItem().toString();
        this.filterSkillCond=optSkillCond.getSelectedItem().toString();
        //cek skill
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
        if (chkKosong.isSelected()) {
            skills += " <kosong>";
        }

        skills = skills.trim();
        //get all result sorted

        result = db.getResult(sortBy, order);;

        empty = (result == null);
        System.out.println(">var updated !");
    }
//    END

    /**
     * METHOD SEARCH
     *
     * @return BOOLEAN HASIL
     */
    public boolean search() {
        if (!where.equals("")) {
            result = db.search(searchBy, where);
            if (result == null) {
                empty = true;
                return false;
            } else {
                return true;
            }
        } else {//do nothing
            db.setSortIdx(searchBy);
            return true;
        }
    }

    /**
     * RESET KOSONG SEMUA FORM
     */
    public void resetForm() {
        txtFind.setText("");
        chkJava.setSelected(false);
        chkPhp.setSelected(false);
        chkPhyton.setSelected(false);
        chkHtml.setSelected(false);
        chkCPP.setSelected(false);
        chkCSub.setSelected(false);
        radAll.setSelected(true);
        radPR.setSelected(false);
        radLK.setSelected(false);
        optSort.setSelectedIndex(0);
        optOrder.setSelectedIndex(0);
    }
//    END

    /**
     * FILTER SEMUA ROW TABEL
     */
    public void filterTable() {
        updateVar();
        updateTableAll();
        if (!empty) {
            filterSkill();
            filterJK();
            if (!search()) {
                System.out.println("search result not found!");
                updateTableAll();
            } else {
                updateTableAll();
            }
            showRow();
        }
    }

    //END
    /**
     * TAMPILKAN JUMLAH ROW TABLE
     */
    public void showRow() {
        TableModel dtm = tblMahasiswa.getModel();
        lblResult.setText("Result = " + dtm.getRowCount() + " row");
    }

    //END
    /**
     * SIMPAN SEMUA VALUE PADA TABLE
     */
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
        System.out.println(">Table retrieved!");
    }
    //END

    /**
     * FILTER JENIS KELAMIN
     */
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
            retreveTable(); //SIMPAN HASIL FILTER
        } else {
            return;
        }
        System.out.println(">filter gender by " + filterGender);
    }

    //END
    /**
     * CEK SKILL
     *
     * @param SKILL YANG DI CARI
     * @return BOOLEAN HASIL
     */
    
    public boolean checkSkillOR(String s) {
        String[] skillMain = skills.split(" ");
        String[] skillParam = s.split(" ");
        for (int i = 0; i < skillMain.length; i++) {
            for (int j = 0; j < skillParam.length; j++) {
                if (skillMain[i].equals(skillParam[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSkillAND(String s) {
        String[] skillMain = skills.split(" ");
        String[] skillParam = s.split(" ");
        boolean found = true;

        for (int i = 0; i < skillMain.length&&found; i++) {
            found = false;
            for (int j = 0; j < skillParam.length; j++) {
                if (skillParam[j].equals(skillMain[i])) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    //END
    /**
     * FILTER DARI SKILL
     */
    private void filterSkill() {
        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);//reset the table

        if (!skills.equals("")) {
            for (int i = 0; i < result.length; i++) {
                if (filterSkillCond.equals("OR")?checkSkillOR(result[i][COL_SKILL]):checkSkillAND(result[i][COL_SKILL])) {
                    Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_ANGKATA], result[i][COL_JK], result[i][COL_HP], result[i][COL_SKILL]};
                    model.addRow(row);
                }
            }
            retreveTable();
        } else {
            return;
        }
        System.out.println(">filter skill by " + skills);
    }

    //END
    /**
     * PRINT SEMUA DATA KE TABLE
     */
    public void updateTableAll() {

        DefaultTableModel model = (DefaultTableModel) tblMahasiswa.getModel();
        model.setRowCount(0);
        if (empty) {
            return;
        }

        for (int i = 0; i < result.length; i++) {
            Object[] row = {result[i][COL_NIM], result[i][COL_NAMA], result[i][COL_ANGKATA], result[i][COL_JK], result[i][COL_HP], result[i][COL_SKILL]};
            model.addRow(row);
        }
    }
    //END

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
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        optSkillCond = new javax.swing.JComboBox<>();
        chkKosong = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        optSort = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        radLK = new javax.swing.JRadioButton();
        radPR = new javax.swing.JRadioButton();
        btnFilter = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        radAll = new javax.swing.JRadioButton();
        optOrder = new javax.swing.JComboBox<>();
        lblResult = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMahasiswa = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        menuInsert = new javax.swing.JMenuItem();
        menuDelete = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuRefresh = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSave = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Skill Mahasiswa TI");
        setMinimumSize(new java.awt.Dimension(700, 500));

        txtFind.setToolTipText("Insert Nama");
        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html>\n<b>\nSkill<br> Mahasiswa <br>TI\n</b>\n</htmll>");

        btnFind.setText("Search");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        chkJava.setText("java");
        chkJava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJavaActionPerformed(evt);
            }
        });

        chkPhp.setText("php");
        chkPhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhpActionPerformed(evt);
            }
        });

        chkHtml.setText("Html&Css");
        chkHtml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHtmlActionPerformed(evt);
            }
        });

        chkCPP.setText("C++");
        chkCPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPPActionPerformed(evt);
            }
        });

        chkCSub.setText("C#");
        chkCSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCSubActionPerformed(evt);
            }
        });

        chkPhyton.setText("Phyton");
        chkPhyton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhytonActionPerformed(evt);
            }
        });

        optFind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIM", "NAMA", "ANGKATAN", "NO. HP" }));
        optFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optFindActionPerformed(evt);
            }
        });
        optFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optFindKeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/updatesicon.png"))); // NOI18N
        jButton1.setToolTipText("Refresh (F5)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/icon-mhs.png"))); // NOI18N

        optSkillCond.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OR", "AND" }));
        optSkillCond.setToolTipText("filter skill condition");
        optSkillCond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optSkillCondActionPerformed(evt);
            }
        });

        chkKosong.setText("Kosong");
        chkKosong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKosongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optFind, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(chkPhyton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkKosong)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optSkillCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optFind, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkJava)
                                .addComponent(chkPhp)
                                .addComponent(chkHtml)
                                .addComponent(chkCPP)
                                .addComponent(chkCSub)
                                .addComponent(chkPhyton)
                                .addComponent(chkKosong))
                            .addComponent(optSkillCond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        btnFilter.setText("Apply");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
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
                                            .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
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

        fileMenu.setText("File");

        menuInsert.setText("Insert...");
        menuInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInsertActionPerformed(evt);
            }
        });
        menuInsert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuInsertKeyPressed(evt);
            }
        });
        fileMenu.add(menuInsert);

        menuDelete.setText("Delete...");
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteActionPerformed(evt);
            }
        });
        fileMenu.add(menuDelete);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Update...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);
        fileMenu.add(jSeparator3);

        menuRefresh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuRefresh.setText("Refresh");
        menuRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRefreshActionPerformed(evt);
            }
        });
        fileMenu.add(menuRefresh);
        fileMenu.add(jSeparator1);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText("Save");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        fileMenu.add(menuSave);
        fileMenu.add(jSeparator2);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        fileMenu.add(menuExit);

        jMenuBar1.add(fileMenu);

        menuAbout.setText("Help");

        jMenuItem4.setText("About...");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
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

    private void radLKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radLKActionPerformed
        // TODO add your handling code here:
        radAll.setSelected(false);
        radPR.setSelected(false);
    }//GEN-LAST:event_radLKActionPerformed

    private void radPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPRActionPerformed
        // TODO add your handling code here:
        radLK.setSelected(false);
        radAll.setSelected(false);
    }//GEN-LAST:event_radPRActionPerformed

    private void radAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAllActionPerformed
        // TODO add your handling code here:
        radLK.setSelected(false);
        radPR.setSelected(false);
    }//GEN-LAST:event_radAllActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
        updateVar();

        if (!txtFind.getText().equals("")) {
            retreveTable();
            arrayHandler = new Array();
            arrayHandler.insert(result);
            int by = 0;
            if (sortBy.equals("nim")) {
                by = 0;
            } else if (sortBy.equals("nama")) {
                by = 1;
            } else {
                by = 2;
            }
            result = arrayHandler.sortArray(by, order);
        }
        updateTableAll();
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void menuInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInsertActionPerformed
        // TODO add your handling code here:
        new Add().setVisible(true);
    }//GEN-LAST:event_menuInsertActionPerformed

    private void optFindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optFindKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            filterTable();
        }
    }//GEN-LAST:event_optFindKeyPressed

    private void menuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteActionPerformed
        // TODO add your handling code here:
        new Delete().setVisible(true);
    }//GEN-LAST:event_menuDeleteActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        // TODO add your handling code here:
        String ObjButtons[] = {"Ya", "Tidak"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Apakah anda yakin ingin keluar?", "Skill Mahasiswa TI",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuExitActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new About().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    //shortcut


    private void menuInsertKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuInsertKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuInsertKeyPressed

    private void chkJavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJavaActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkJavaActionPerformed

    private void chkPhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhpActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkPhpActionPerformed

    private void chkHtmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHtmlActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkHtmlActionPerformed

    private void chkCPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPPActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkCPPActionPerformed

    private void chkCSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCSubActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkCSubActionPerformed

    private void chkPhytonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhytonActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkPhytonActionPerformed

    private void save() throws IOException {

        updateVar();
        if (!empty) {
            file.writeFile(result);
        } else {
            file.writeFile(null);
        }
        JOptionPane.showMessageDialog(null, "Data telah disimpan!");

    }

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        // TODO add your handling code here:
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        saved = true;
    }//GEN-LAST:event_menuSaveActionPerformed

    private void menuRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRefreshActionPerformed
        // TODO add your handling code here:
        resetForm();
        filterTable();
    }//GEN-LAST:event_menuRefreshActionPerformed

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_txtFindKeyReleased

    private void optFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optFindActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_optFindActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new Edit().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        menuRefreshActionPerformed(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void optSkillCondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optSkillCondActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_optSkillCondActionPerformed

    private void chkKosongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKosongActionPerformed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_chkKosongActionPerformed

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
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSort;
    private javax.swing.JCheckBox chkCPP;
    private javax.swing.JCheckBox chkCSub;
    private javax.swing.JCheckBox chkHtml;
    private javax.swing.JCheckBox chkJava;
    private javax.swing.JCheckBox chkKosong;
    private javax.swing.JCheckBox chkPhp;
    private javax.swing.JCheckBox chkPhyton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblResult;
    private javax.swing.JMenu menuAbout;
    private javax.swing.JMenuItem menuDelete;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuInsert;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JComboBox<String> optFind;
    private javax.swing.JComboBox<String> optOrder;
    private javax.swing.JComboBox<String> optSkillCond;
    private javax.swing.JComboBox<String> optSort;
    private javax.swing.JRadioButton radAll;
    private javax.swing.JRadioButton radLK;
    private javax.swing.JRadioButton radPR;
    private javax.swing.JTable tblMahasiswa;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables

    //menu shortcut
    private void myInitComponet() {
        menuInsert.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        menuDelete.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuExit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                ////saat system exit

                //JIKA BELUM TERSIMPAN
                if (!saved) {
                    String ObjButtons[] = {"Simpan", "Tidak"};
                    int PromptResult = JOptionPane.showOptionDialog(null,
                            "Data belum tersimpan?", "Skill Mahasiswa TI",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                            ObjButtons, ObjButtons[1]);
                    if (PromptResult == 0) {
                        try {
                            save();//simpan data
                            saved = true;
                        } catch (IOException ex) {
                            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                //TANYA KELUAR
                String ObjButtons[] = {"Ya", "Tidak"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Apakah anda yakin ingin keluar?", "Skill Mahasiswa TI",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    System.exit(0);
                }
            }
        });
    }
}
