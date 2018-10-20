package rekursjon;

import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class LambdaDemo {

    public static void main(String[] args) {

        //Integer[] verdier = {4, 2, 10, 9, 7, 22};
        //Integer[] resultat = brukPåAlle(verdier, x -> x * x);
        
        int[] values = {4, 2, 10, 9, 7, 22};
        
        int[] result = IntStream.of(values).map(x->x*x).toArray();
        
        IntStream.of(result).forEach(x->System.out.println(x));

    }

    /*
    public static Integer[] brukPåAlle(Integer[] tab, UnaryOperator<Integer> oper) {
        Integer[] res = new Integer[tab.length];

        for (int i = 0; i < tab.length; i++) {
            res[i] = oper.apply(tab[i]);
        }

        return res;
    }
    */
}
