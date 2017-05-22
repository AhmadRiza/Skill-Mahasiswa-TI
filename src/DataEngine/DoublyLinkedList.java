package DataEngine;

public class DoublyLinkedList {

    private Mahasiswa first;
    private Mahasiswa last;
    private int count;
    String[][] result;
    private Array sortHandler;

    public DoublyLinkedList() {
        first = null;
        last = null;
        sortHandler = new Array();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(Mahasiswa newLink) {
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        newLink.next = first;
        first = newLink;
        count++;
    }

    public void insertLast(Mahasiswa newLink) {
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
        count++;
    }

    public Mahasiswa deleteFirst() {
        Mahasiswa temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public Mahasiswa deleteLast() {
        Mahasiswa temp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    public boolean deleteKey(String key) {
        Mahasiswa current = first;

        while (!current.getNIM().equals(key)) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }

        if (current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
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
}
