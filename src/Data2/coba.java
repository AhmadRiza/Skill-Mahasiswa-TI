/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data2;

import java.io.IOException;

/**
 *
 * @author Ahmad Riza
 */
public class coba {
    public static void main(String[] args) throws IOException {
        
        FileRW file = new FileRW();
        String[][] rs = file.readFile();
        for (int i = 0; i < rs.length; i++) {
            for (int j = 0; j < rs[0].length; j++) {
                System.out.print(rs[i][j]+",");
            }
            System.out.println("");
        }
        
    }
   
}
