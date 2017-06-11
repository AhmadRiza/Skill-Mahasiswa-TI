/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEngine;

/**
 *
 * @author Ahmad Riza
 */
public class DataHandler {

    private LinkedList mhsList = new LinkedList();
    boolean loaded = false;
    Array arrayHandler = new Array();
    String[][] result;
    private boolean empty = true;
    private int sortBy;
    private String order;
    
    public void insert(String NIM, String Nama, char JK, int Angkatan, String HP, String Skills) {
        Mahasiswa newLink = new Mahasiswa(NIM, Nama, JK, Angkatan, HP, Skills);
        mhsList.insertLast(newLink);
        loaded = false;
    }
    
    public String[][] getMasterData() {
        result = mhsList.LoadData();
        if (result == null) {
            empty = true;
        }else{
            empty=false;
        }
        if (!empty) {
            arrayHandler.insert(result);
        }
        loaded = true;
        System.out.println(">master data on LL loaded!!");
        return result;
    }

    public String[][] getResult(String by, String order) {

        int sortIdx = 0;
        by = by.toLowerCase();
        if (by.equals("nim")) {
            sortIdx = 0;
        } else if (by.equals("nama")) {
            sortIdx = 1;
        } else if (by.equals("angkatan")) {
            sortIdx = 2;
        }
        
        if (loaded) {
            if (empty) {
                return null;
            }
            if (this.sortBy!=sortIdx||this.order!=order) {
                this.sortBy=sortIdx;
                this.order=order;
                result = arrayHandler.sortArray(sortIdx, order);
            }
        } else {
            getMasterData();
            if (empty) {
                return null;
            }
            if (this.sortBy!=sortIdx||this.order!=order) {
                this.sortBy=sortIdx;
                this.order=order;
                result = arrayHandler.sortArray(sortIdx, order);
            }
        }
        return result;
    }

    public String[][] search(int searchBy, String where) {

        if (loaded) {
            if (empty) {
                return null;
            }
            return arrayHandler.search(searchBy, where);
            
        } else {
            getMasterData();
            if (empty) {
                return null;
            }
            return arrayHandler.search(searchBy, where);
        }
    }

    public String[] find(int findBy, String key) {
        if (loaded) {
            if (empty) {
                return null;
            }
            return arrayHandler.find(findBy, key);
        } else {
            getMasterData();
            if (empty) {
                return null;
            }
            return arrayHandler.find(findBy, key);
        }
    }

    public boolean delete(String key) {
        loaded = false;
        return mhsList.deleteKey(key);
    }
    
    public void setSortIdx(int idx){
        this.sortBy=idx;
    }
}
