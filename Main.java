import java.math.BigInteger;
import java.util.*;

public class Main {

    // Lagrange interpolation
    public static BigInteger[] lagrange(BigInteger[] x, BigInteger[] y, int k) {

        BigInteger[] coeff = new BigInteger[k];
        Arrays.fill(coeff, BigInteger.ZERO);

        for (int i = 0; i < k; i++) {

            BigInteger[] temp = new BigInteger[k];
            Arrays.fill(temp, BigInteger.ZERO);
            temp[0] = BigInteger.ONE;

            BigInteger denom = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {

                    for (int d = k - 1; d > 0; d--)
                        temp[d] = temp[d - 1];

                    temp[0] = BigInteger.ZERO;

                    for (int d = 0; d < k - 1; d++)
                        temp[d] = temp[d].subtract(temp[d + 1].multiply(x[j]));

                    denom = denom.multiply(x[i].subtract(x[j]));
                }
            }

            BigInteger factor = y[i].divide(denom);

            for (int d = 0; d < k; d++)
                coeff[d] = coeff[d].add(temp[d].multiply(factor));
        }

        return coeff;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        BigInteger[] x = new BigInteger[k];
        BigInteger[] y = new BigInteger[k];

        for (int i = 0; i < k; i++) {

            int base = sc.nextInt();
            String value = sc.next();

            x[i] = BigInteger.valueOf(i + 1);  // x = 1,2,3...
            y[i] = new BigInteger(value, base);
        }

        BigInteger[] result = lagrange(x, y, k);

        System.out.println("Polynomial Coefficients:");
        for (int i = 0; i < result.length; i++)
            System.out.println("a" + i + " = " + result[i]);

        sc.close();
    }
}
