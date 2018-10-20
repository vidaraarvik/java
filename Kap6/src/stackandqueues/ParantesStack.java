package stackandqueues;

import java.util.LinkedList;
import java.util.Scanner;

public class ParantesStack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Legg inn kode: ");
        String s = sc.nextLine();
        sc.close();

        // Legger til parantesene i array
        LinkedList<Character> stack = new LinkedList<Character>();
        String melding = "";
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(c);
                    break;
                case '[':
                    stack.push(c);
                    break;
                case '{':
                    stack.push(c);
                    break;

                case ')':
                    if (stack.isEmpty()) {
                        melding += "\nEmptyStackError " + c;
                    } else {
                        char ch = stack.pop();
                        if (ch != '(') {
                            melding += "Missing bracket " + c + "\n";
                        }
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        melding += "\nEmptyStackError " + c;
                    } else {
                        char ch = stack.pop();
                        if (ch != '[') {
                            melding += "Missing bracket " + c + "\n";
                        }
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        melding += "\nEmptyStackError " + c;
                    } else {
                        char ch = stack.pop();
                        if (ch != '{') {
                            melding += "Missing bracket " + c + "\n";
                        }
                    }
                    break;
            }
        }
        
        System.out.println(melding);

    }

}
