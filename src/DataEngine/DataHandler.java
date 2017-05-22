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

    private DoublyLinkedList mhsList = new DoublyLinkedList();
    String[][] result;

    public void insert(String NIM, String Nama, char JK, int Angkatan, String HP, String Skills) {
        Mahasiswa newLink = new Mahasiswa(NIM, Nama, JK, Angkatan, HP, Skills);
        mhsList.insertLast(newLink);
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

    public String[][] getResult(String by, String order) {
        return mhsList.LoadAll(by, order);
    }

    public boolean delete(String key) {
        return mhsList.deleteKey(key);
    }

}
