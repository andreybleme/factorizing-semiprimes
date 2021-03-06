package in.choy.math.ia;

import java.math.BigInteger;
import javax.swing.*;

public class Main {
    static int n = 397927; /* integer for factorization */
    static int ffLooped = 0;
    static int bfLooped = 0;
    static int pfLooped = 0;

    public static int inputSemiPrime() {
       int semiPrime = 0;
       String errorMessage = "";
       do {
        try {
             semiPrime = Integer.parseInt(JOptionPane.showInputDialog(errorMessage + "\nEnter a semiPrime"));
             errorMessage = "";
           } catch(NumberFormatException nfe) {
             errorMessage = "Invalid input. Please enter an integer";
           }
        } while(!errorMessage.isEmpty());
        return semiPrime;
    }

    public static boolean sqrtnisEven(double sqrtn) {
        if (sqrtn % 2 == 0) {
            return true;
        }
        return false;
    }

    public static boolean aIsGonnaBeEven() {
        if ((n + 1) % 4 == 0) {
            return true;
        }
        return false;
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void bruteForceMethodBI(BigInteger num) {
      BigInteger TWO = new BigInteger("2");
      BigInteger i = new BigInteger("3");

      final long bfStartTime = System.currentTimeMillis();
      while (true) {
        BigInteger tmp0 = num.mod(i);
        if (tmp0 == BigInteger.ZERO) {
          break;
        }
        i = i.add(TWO);
        bfLooped++;
      }
      final long bfEndTime = System.currentTimeMillis();

      System.out.println("BF: " + i + " is a factor");
      System.out.println("BF: " + num.divide(i) + " is another factor");
      System.out.println("BF: Total execution time: " + (bfEndTime - bfStartTime) + "ms");
      System.out.println("BF: steps involved: " + bfLooped);
    }

    public static void bruteForceMethod(int num) {
        long i = 3;
        final long bfStartTime = System.currentTimeMillis();
        while (!(num % i == 0)) {
            i = i + 2;
            bfLooped++;
        }
        final long bfEndTime = System.currentTimeMillis();
        System.out.println("BF: " + i + " is a factor");
        System.out.println("BF: " + num / i + " is another factor");
        System.out.println("BF: Total execution time: " + (bfEndTime - bfStartTime) + "ms");
        System.out.println("BF: steps involved: " + bfLooped);
    }

    public static void fermatsFactorizationMethod(int num) {
        final long ffStartTime = System.currentTimeMillis();
        int a = (int) Math.ceil(Math.sqrt(num));
        int bsquared = a * a - num;
        if (aIsGonnaBeEven()) { //a is even
            if (sqrtnisEven(a)) { //initial sqrtn is even
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - num; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            } else {
                a++;
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - n; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            }
        } else { //a is odd
            if (sqrtnisEven(a)) { //initial sqrtn is even
                a++;
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - num; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            } else {
                while (Math.sqrt(bsquared) % 1 != 0) {
                    a = a + 2; //increase sqrtn by 2
                    bsquared = a * a - n; //rerun algorithm
                    ffLooped++; //count the number of times calculated
                }
            }
        }
        final long ffEndTime = System.currentTimeMillis();
        System.out.println("FF: " + (a - (long) Math.sqrt(bsquared)) + " is a factor");
        System.out.println("FF: " + (a + (long) Math.sqrt(bsquared)) + " is another factor");
        System.out.println("FF: Total execution time: " + (ffEndTime - ffStartTime) + "ms");
        System.out.println("FF: steps involved: " + ffLooped);
    }

    public static void pollardsRhoMethod(int num) {
        final long pfStartTime = System.currentTimeMillis();
        int p = 0; //result of gcd
        int x = 1; //value to increase
        while (p <= 1) {
            x++;
            p = gcd((x * x * x * x + x * x + 1) % num, num); //(x^5+x^2+1)modn, num
            pfLooped++;
        }
        final long pfEndTime = System.currentTimeMillis();
        System.out.println("PF: " + p + " is a factor");
        System.out.println("PF: " + num / p + " is another factor");
        System.out.println("PF: steps involved: " + pfLooped);
        System.out.println("PF: Total execution time: " + (pfEndTime - pfStartTime) + "ms");
    }

    public static void main(String[] args) {
        n = inputSemiPrime();
        fermatsFactorizationMethod(n);
        bruteForceMethod(n);
        pollardsRhoMethod(n);

        System.out.println("BigInteger start");
        int bfLooped = 0;
        BigInteger bn = new BigInteger(Integer.toString(n));
        bruteForceMethodBI(bn);
    }
}
