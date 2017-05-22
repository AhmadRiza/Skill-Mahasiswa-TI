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
public class Array {

    private String[][] mhs;
    private int nElemen;
    private String order;
    private int by;
    private int nMax;
    private String where;
    private int whereLength;
    private String[][] searchRes;

    public void insert(String[][] mhs) {
        this.mhs = mhs;
        this.nElemen = mhs.length;
        this.nMax = mhs.length;
    }

    public void swap(int a, int b) {
        String[] temp = mhs[a];
        mhs[a] = mhs[b];
        mhs[b] = temp;
    }

    public int compare(String a, String b) {
        return a.compareTo(b);
    }

    public String[][] sortArray(int by, String order) {
        this.by = by;
        this.order = order;
        Heap heap=new Heap(mhs);
        
        if (!order.equals("DESC")) {
            mhs=heap.heapSortASC(by);
        } else {
            mhs=heap.heapSortDESC(by);
        }
        return mhs;
    }

    //search
    public String[][] search(int by, String where) {
        this.by = by;
        this.where = where.trim().toLowerCase();
        this.whereLength = where.length();
        sortArray(by, "DESC");
        int idx = partialBinarySearch(where);
        System.out.println(idx);
        if (idx != -1) {
            searchRes = like(idx);
        } else {
            searchRes = null;
        }
        return searchRes;
    }

    public String[] find(int by, String where) {
        this.by = by;
        this.where = where.trim().toLowerCase();
        this.whereLength = where.length();
        sortArray(by, "DESC");
        int idx =  clearBinarySearch(where);
        if (idx == -1) {
            return null;
        }
        return mhs[idx];
    }

    public int partialBinarySearch(String where) {
        int righIdx = nElemen - 1;
        int leftIdx = 0;
        int midIdx = (leftIdx + righIdx) / 2;
        String midVal;

        while (righIdx >= leftIdx) {
            midVal = mhs[midIdx][by].toLowerCase();

            if (midVal.length() >= whereLength) {
                if (midVal.substring(0, whereLength).toLowerCase().equals(where)) {
                    return midIdx;
                }
                if (compare(midVal.substring(0, whereLength).toLowerCase(), where) > 0) {
                    leftIdx = midIdx + 1;
                } else {
                    righIdx = midIdx - 1;
                }
            } else if (compare(midVal, where) > 0) {
                leftIdx = midIdx + 1;
            } else {
                righIdx = midIdx - 1;
            }

            midIdx = (leftIdx + righIdx) / 2;
        }
        return -1;
    }

    public int clearBinarySearch(String where) {
        int righIdx = nElemen - 1;
        int leftIdx = 0;
        int midIdx = (leftIdx + righIdx) / 2;
        String midVal;

        while (righIdx >= leftIdx) {
            midVal = mhs[midIdx][by].toLowerCase();

            if (midVal.equals(where)) {
                return midIdx;
            }
            if (compare(midVal, where) > 0) {
                leftIdx = midIdx + 1;
            } else {
                righIdx = midIdx - 1;
            }
            midIdx = (leftIdx + righIdx) / 2;
        }
        return -1;
    }

    public String[][] like(int idx) {
        int startIdx = idx;
        int endIdx = idx;

        if (idx + 1 == nMax || mhs[idx + 1][by].length() >= whereLength) {
            while (mhs[endIdx][by].substring(0, whereLength).toLowerCase().equals(where)) {
                endIdx++;
                if (endIdx == nMax) {
                    break;
                }
            }
            endIdx--;
        }
        if (idx - 1 == -1 || mhs[idx - 1][by].length() >= whereLength) {
            while (mhs[startIdx][by].substring(0, whereLength).toLowerCase().equals(where)) {
                startIdx--;
                if (startIdx == -1) {
                    break;
                }
            }
            startIdx++;
        }
        System.out.println("!!");
        searchRes = new String[(endIdx - startIdx) + 1][mhs[0].length];

        for (int i = 0; i < searchRes.length; i++) {
            searchRes[i] = mhs[startIdx++];
        }
        return searchRes;
    }

}
