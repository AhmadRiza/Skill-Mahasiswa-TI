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
public class Heap {

    private String[][] heapArray;
    private int maxSize;
    private int currentSize;
    private int by;

    public Heap(String[][] array) {
        this.maxSize = array.length;
        currentSize = array.length;
        heapArray = array;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void sizeIncrement(){
        currentSize++;
    }
    
    public void insertAt(int index, String[] data){
        if (index>=maxSize||index<0) {
            return ;
        }
        heapArray[index]=data;
        
    }
    
    public String[][] heapSortASC(int by){
        this.by = by;
        int sizeTemp=currentSize;
        for (int i = currentSize/2-1; i >= 0; i--) {//trikle down n/2-1 
            tricleDown(i);
        }
        for (int i = currentSize-1; i >=0; i--) {
            String[] temp = remove();
            insertAt(i, temp);
        }
        currentSize=sizeTemp;
        System.out.println("sorted on idx "+by+" ASC");
        return heapArray;
    }
    
    public String[][] heapSortDESC(int by){
        this.by = by;//index sorting
        
        String[][] result = new String[heapArray.length][heapArray[0].length];//srrsy bust nyimpsn
        int sizeTemp=currentSize;//nyimpsn size sementara
        
        for (int i = currentSize/2-1; i >= 0; i--) {//trikle down n/2-1 
            tricleDown(i);
        }
        
        for (int i = 0; i <maxSize; i++) {//
            result[i]=remove();
        }
        
        heapArray=result;
        currentSize=sizeTemp;
        System.out.println("sorted on idx "+by+" DESC");
        return heapArray;
    }
    
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
    
    public void tricleUp(int index) {
        int parent = (index - 1) / 2;
        String[] bottom = heapArray[index];
        while (index > 0 && compare(heapArray[parent][by], bottom[by])<0) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }
    
    public String[] remove() {
        String[] root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        tricleDown(0);
        return root;
    }
       
    public void tricleDown(int index) {
        int largerChild;
        String[] top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize
                    && compare(heapArray[leftChild][by]
                    , heapArray[rightChild][by])<0 ) {
                largerChild = rightChild;
            }else{
                largerChild = leftChild;
            }
            if (compare(top[by],heapArray[largerChild][by])>=0) {
                break;
            }
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index]=top;
        
    }
    
    public void tricleDownDESC(int index) {
        int largerChild;
        String[] top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize
                    && compare(heapArray[leftChild][by]
                    , heapArray[rightChild][by])>0 ) {
                largerChild = rightChild;
            }else{
                largerChild = leftChild;
            }
            if (compare(top[by],heapArray[largerChild][by])<=0) {
                break;
            }
            
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index]=top;
    }
    

}
