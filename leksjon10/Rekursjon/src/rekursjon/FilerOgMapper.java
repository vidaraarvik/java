package rekursjon;

import java.io.File;

public class FilerOgMapper {

    public static void main(String[] args) {

        /*
        if (args.length != 1) {
            System.out.println("Skriv: FilerOgMapper mappe");
            System.exit(-1);
        }

        String mappenavn = args[0];
        //System.out.println(mappenavn);
        */
        
        String mappenavn = "c:\\Fraps\\";

        visMapperOgFiler(mappenavn, 0);
    }

    private static void visMapperOgFiler(String mappenavn, int nivå) {
        File mappe = new File(mappenavn);
        System.out.println(innrykk(nivå) + mappenavn);

        for (File fil : mappe.listFiles()) {

            String filSti = fil.getAbsolutePath();
            String navn = fil.getName();

            if (fil.isDirectory()) {
                visMapperOgFiler(filSti, nivå + 1);
            } 
            
            else {
                System.out.println(innrykk(nivå + 1) + navn);
            }

        }

    }

    private static String innrykk(int nivå) {
        String blanke = "";
        for (int i = 0; i < nivå; i++) {
            blanke += "  ";
        }
        return blanke;
    }

}
