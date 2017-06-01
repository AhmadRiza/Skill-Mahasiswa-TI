/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Ahmad Riza
 */
public class FileRW {

    private BufferedReader br;
    private BufferedWriter bw;
    private String[][] result;
    private final String directory = System.getProperty("user.home") + "\\Documents\\data-mhs";
    private final String fileLoc = directory + "\\mahasiswa.dtm";
    private String temp = "";

    public FileRW() {
        File file = new File(directory);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("NEW DIRECTORY CREATED!");
            } else {
                System.out.println("ERR::NO DIRECTORY CREATED");
            }
        }
    }

    public void writeFile(String[][] input) throws IOException {

        this.bw = new BufferedWriter(new FileWriter(fileLoc));

        if (input == null) {
            bw.write("");
        } else {
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[0].length; j++) {
                    bw.write(input[i][j]);
                    if (j != input[0].length - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        }
        bw.close();
        System.out.println("File Saved on >" + fileLoc);
    }

    public String[][] readFile() throws FileNotFoundException, IOException {
        File f = new File(fileLoc);
        if (!f.exists()) {
            return null;
        }

        this.br = new BufferedReader(new FileReader(fileLoc));
        
        String line;
        temp = "";
        for (int i = 0; (line = br.readLine()) != null; i++) {
            if (i != 0) {
                temp += "\n";
            }
            temp += line;
        }
        if (temp=="") {
            return null;
        }
        String[] rows = temp.split("\n");
        String[] row = rows[0].split(",");

        int rowSum = rows.length;
        int colSum = row.length;

        result = new String[rowSum][colSum];

        for (int i = 0; i < rowSum; i++) {
            row = rows[i].split(",");
            result[i] = row;
        }
        System.out.println(">data on disk red");
        return result;
    }

}
