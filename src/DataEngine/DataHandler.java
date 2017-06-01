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

    public void insert(String NIM, String Nama, char JK, int Angkatan, String HP, String Skills) {
        Mahasiswa newLink = new Mahasiswa(NIM, Nama, JK, Angkatan, HP, Skills);
        mhsList.insertLast(newLink);
        loaded=false;
    }

    public void printResult(String by, String order) {
        result = mhsList.LoadAll(by, order);

        for (int i = 0; i < result.length; i++) {
            System.out.print(i);
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(". " + result[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public String[][] getMasterData() {
        result = mhsList.LoadData();
        arrayHandler.insert(result);
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
            return arrayHandler.sortArray(sortIdx, order);
        } else {
            getMasterData();
            return arrayHandler.sortArray(sortIdx, order);
        }
    }

    public String[][] search(int searchBy, String where) {
        if (loaded) {
            return arrayHandler.search(searchBy, where);
        } else {
            getMasterData();
            return arrayHandler.search(searchBy, where);
        }
    }

    public String[] find(int findBy, String key) {
        if (loaded) {
            return arrayHandler.find(findBy, key);
        } else {
            getMasterData();
            return arrayHandler.find(findBy, key);
        }
    }

    public boolean delete(String key) {
        loaded=false;
        return mhsList.deleteKey(key);
    }

}
