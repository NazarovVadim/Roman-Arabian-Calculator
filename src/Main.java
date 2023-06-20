import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    static void calc(String aS, String bS, String op) throws MyException {
        int a,b;
        boolean isArabian = true;

        if(aS.matches("[-+]?\\d+") && bS.matches("[-+]?\\d+")){
            a = Integer.valueOf(aS);
            b = Integer.valueOf(bS);
            if(a<1 || a>10 || b<1 || b>10){
                throw new MyException("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
            }
        } else if (aS.matches("[-+]?\\d+") && !bS.matches("[-+]?\\d+") || !aS.matches("[-+]?\\d+") && bS.matches("[-+]?\\d+")) {
            throw new MyException("Используются одновременно разные системы счисления");
        } else{
            isArabian = false;
            Roman a1, b1;

            try{
                a1 = Roman.valueOf(aS);
                b1 = Roman.valueOf(aS);
            }catch (IllegalArgumentException e){
                throw new MyException("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
            }

            a = Integer.parseInt(String.valueOf(a1.toInt(aS)));
            b = Integer.parseInt(String.valueOf(b1.toInt(bS)));
        }

        switch (op) {
            case "+" -> System.out.printf(String.valueOf(isArabian ? a + b : convertNumToRoman(a+b)));
            case "-" -> System.out.printf(String.valueOf(isArabian ? a - b : convertNumToRoman(a-b)));
            case "*" -> System.out.printf(String.valueOf(isArabian ? a * b : convertNumToRoman(a*b)));
            case "/" -> System.out.printf(String.valueOf(isArabian ? a / b : convertNumToRoman(a/b)));
        };

    }

    public static void main(String[] args) throws MyException {
        String exp = in.nextLine();
        String[] expSplit = exp.split(" ");

        if(expSplit.length != 3){
            throw new MyException("Строка не является математической операцией");
        }

        calc(expSplit[0], expSplit[2], expSplit[1]);
    }
}