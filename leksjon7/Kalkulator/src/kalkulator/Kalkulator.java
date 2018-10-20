package kalkulator;

public class Kalkulator {

    private int tall, forrige;
    private char operat;
    private boolean inputFase = true;

    public void siffer(int siffer) {
        if (inputFase) {
            if (tall < 0) {
                tall = 10 * tall - siffer;
            } else {
                tall = 10 * tall + siffer;
            }
        } else {
            forrige = tall;
            tall = siffer;
            inputFase = true;
        }
    }

    public String hentTall() {
        return Integer.toString(tall); // TODO
    }

    public void oper(char op) {
        switch (operat) {
            case '+':
                tall = forrige + tall;
                break;
            case '-':
                tall = forrige - tall;
                break;
            case '*':
                tall = forrige * tall;
                System.out.println("ganger.");
                break;
            case '/':
                tall = forrige / tall;
                System.out.println("deler.");
                break;

            default:
                break;
        }
        operat = op;
        inputFase = false;
    }

    void erLik() {
        switch (operat) {
            case '+':
                tall = forrige + tall;
                break;
            case '-':
                tall = forrige - tall;
                break;
            case '*':
                tall = forrige * tall;
                break;
            case '/':
                tall = forrige / tall;
                break;
            default:
                break;
        }
        operat = '=';
        inputFase = false;
    }

    void snuFortegn() {
        tall = -tall;
    }

    void nullstill() {
        tall = 0;
        forrige = 0;
        operat = '=';
        inputFase = true;
    }

}
