// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {
    public static void main(String[] args) {
        System.out.println("----Sumator----");

        Sumator myClass = new Sumator();
        double res = 0;
        try {
            float a = 1;
            float n = 2;
            float step1 = 0.1f;
            float b = 1;
            float m = 2;
            float step2 = 0.5f;

            System.out.printf("Input: \n a= %f\n n= %f\n stepOne= %f\n b= %f\n m= %f\n stepTwo= %f\n\n", a, n, step1, b, m, step2);
            res = myClass.calculate(a, n, step1, b, m, step2);
            System.out.println("S = " + res);
        } catch (Exception ex) {
            System.err.println("Please, change input. The problem is: \n"
                    + ex.getMessage());
        }
    }
}

