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
        if (!order.equals("DESC")) {
            QuickSort();
        } else {
            mergeSort();
        }
        return mhs;
    }

//////////////////////// QUICK SORT//////////////////////////
    private void QuickSort() {
        recQuickSortASC(0, nElemen - 1);
        System.out.println(order + " Quick Sorted !!");
    }

///////////////Quick Sort ASC/////////////////////
    private void recQuickSortASC(int batasKiri, int batasKanan) {
        if (batasKanan - batasKiri <= 0) {
            return;
        } else {
            String[] pivot = mhs[batasKanan];
            int partisi = partitionASC(batasKiri, batasKanan, pivot);
            recQuickSortASC(batasKiri, partisi - 1);
            recQuickSortASC(partisi + 1, batasKanan);
        }
    }

    private int partitionASC(int batasKiri, int batasKanan, String[] pivot) {
        int indexKiri = batasKiri - 1;
        int indexKanan = batasKanan + 1;
        while (true) {
            while (indexKiri < batasKanan
                    && compare(mhs[++indexKiri][by], pivot[by]) < 0);
            while (indexKanan > batasKiri
                    && compare(mhs[--indexKanan][by], pivot[by]) > 0);
            if (indexKiri >= indexKanan) {
                break;
            } else {
                swap(indexKiri, indexKanan);
            }
        }
        return indexKiri;
    }

    ////////////////////////Merge Sort////////////////////////
    public void mergeSort() {
        String[][] workSpace = new String[mhs.length][mhs[0].length];
        recMergeSort(workSpace, 0, nElemen - 1);
        System.out.println(order + " Merge Sorted !!");
    }

    public void recMergeSort(String[][] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workSpace, lowerBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    public void merge(String[][] workSpace, int lowIndex, int highIndex, int upperBound) {

        int j = 0;
        int lowerBound = lowIndex;
        int mid = highIndex - 1;
        int nItem = upperBound - lowerBound + 1;

        while (lowIndex <= mid && highIndex <= upperBound) {
            if (compare(mhs[lowIndex][by], mhs[highIndex][by]) > 0) {
                workSpace[j++] = mhs[lowIndex++];
            } else {
                workSpace[j++] = mhs[highIndex++];
            }
        }

        while (lowIndex <= mid) {
            workSpace[j++] = mhs[lowIndex++];
        }

        while (highIndex <= upperBound) {
            workSpace[j++] = mhs[highIndex++];
        }

        for (j = 0; j < nItem; j++) {
            mhs[lowerBound + j] = workSpace[j];
        }
    }

    //search
    public String[][] search(int by, String where) {
        this.by = by;
        this.where = where.trim();
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

    public String[] findNIM(String where) {
        this.by = 0;
        this.where = where.trim();
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
            midVal = mhs[midIdx][by];

            if (midVal.length() >= whereLength) {
                if (midVal.substring(0, whereLength).equals(where)) {
                    return midIdx;
                }
                if (compare(midVal.substring(0, whereLength), where) > 0) {
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
            midVal = mhs[midIdx][by];

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
            while (mhs[endIdx][by].substring(0, whereLength).equals(where)) {
                endIdx++;
                if (endIdx == nMax) {
                    break;
                }
            }
            endIdx--;
        }
        if (idx - 1 == -1 || mhs[idx - 1][by].length() >= whereLength) {
            while (mhs[startIdx][by].substring(0, whereLength).equals(where)) {
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
