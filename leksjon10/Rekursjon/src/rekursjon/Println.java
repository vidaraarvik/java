/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rekursjon;

/**
 *
 * @author speedy
 */
public class Println {
    public static void main(String[] args) {
        vidiPrint("Hei du! Nice!", 15);
    }

    private static void vidiPrint(String melding, int antall) {
        if (antall >= 1) {
            
            for (int i = 0; i<antall; i++){
                System.out.print(" ");
            }
            
            System.out.println(melding);
            
            vidiPrint(melding, antall-1);
        }
    }
}
