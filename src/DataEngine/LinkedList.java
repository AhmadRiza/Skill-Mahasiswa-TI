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
public class LinkedList {
    private Mahasiswa first;
    private Mahasiswa last;
    private int count;
    String[][] result;
    private Array sortHandler;

    public LinkedList() {
        first = null;
        last = null;
        sortHandler = new Array();
    }
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insertLast(Mahasiswa newLink) {
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
        count++;
    }
    
    public boolean deleteKey(String key){
        Mahasiswa current = first;
        Mahasiswa previous = first;
        while (!current.getNIM().equals(key)) {
            if (current.next == null) {
                return false;
            }else{
                previous = current;
                current = current.next;
            }
        }
        if (current == first) {
            first = first.next;
        }else{
            previous.next = current.next;
        }
        count--;
        return true;
    }
    
    public String[][] LoadAll(String by, String order) {
        while (isEmpty()) {
            return null;
        }
        result = new String[count][6];//get data mahasiswa
        Mahasiswa current = first;
        int i = 0, j = 0;

        while (current != null) {
            result[i][0] = current.getNIM();
            result[i][1] = current.getNama();
            result[i][2] = String.valueOf(current.getAngkatan());
            result[i][3] = String.valueOf(current.getJK());
            result[i][4] = current.getHP();
            result[i][5] = current.getSkills();

            i++;
            current = current.next;
        }
        sortHandler.insert(result);

        by = by.toLowerCase();
        if (by.equals("nim")) {
            result = sortHandler.sortArray(0, order);
        } else if (by.equals("nama")) {
            result = sortHandler.sortArray(1, order);
        } else if (by.equals("angkatan")) {
            result = sortHandler.sortArray(2, order);
        }
        return result;
    }
    
    public String[][] LoadData() {
        while (isEmpty()) {
            return null;
        }
        result = new String[count][6];//get data mahasiswa
        Mahasiswa current = first;
        int i = 0, j = 0;

        while (current != null) {
            result[i][0] = current.getNIM();
            result[i][1] = current.getNama();
            result[i][2] = String.valueOf(current.getAngkatan());
            result[i][3] = String.valueOf(current.getJK());
            result[i][4] = current.getHP();
            result[i][5] = current.getSkills();

            i++;
            current = current.next;
        }
        
        return result;
    }
    

    
    
}
