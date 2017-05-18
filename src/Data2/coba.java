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
public class coba {
    public static void main(String[] args) {
        String[][] array ={{"72222","aaaaa"},{"122222","mmmm"},{"11111","cccc"},{"5555","kkkkk"}};
        
        Heap heap = new Heap(array);
        array=heap.heapSortDESC(1);
        
        for (int i = 0; i < array.length; i++) {
            System.out.print(i+".");
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(" "+array[i][j]);
            }
            System.out.println("");
        }
    }
   
}
