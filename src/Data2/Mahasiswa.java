/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data2;

/**
 *
 * @author Ahmad Riza
 */
public class Mahasiswa {
    private String NIM;
    private String Nama;
    private char JK;
    private int Angkatan;
    private String HP;
    private String Skills;
    
    public Mahasiswa next;
    public Mahasiswa previous;

    public Mahasiswa(String NIM, String Nama, char JK, int Angkatan, String HP, String Skills) {
        this.NIM = NIM;
        this.Nama = Nama;
        this.JK = JK;
        this.Angkatan = Angkatan;
        this.HP = HP;
        this.Skills = Skills;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String Skills) {
        this.Skills = Skills;
    }
    
    
    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public void setJK(char JK) {
        this.JK = JK;
    }

    public void setAngkatan(int Angkatan) {
        this.Angkatan = Angkatan;
    }

    public void setHP(String HP) {
        this.HP = HP;
    }
    
    

    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return Nama;
    }

    public char getJK() {
        return JK;
    }

    public int getAngkatan() {
        return Angkatan;
    }

    public String getHP() {
        return HP;
    }

    public Mahasiswa getNext() {
        return next;
    }

    public Mahasiswa getPrevious() {
        return previous;
    }
    
    
}
