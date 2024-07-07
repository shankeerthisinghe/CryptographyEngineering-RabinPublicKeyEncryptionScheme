public class RabinPKEScheme {

    private static final int PLAINTEXT = 100;
    private static final int P = 7;
    private static final int Q = 11;

    public static void main(String[] args) {
        int plainText = PLAINTEXT;

        System.out.println("======PLAIN_TEXT======");
        System.out.println("plainText = " + plainText);

        int[] key = KeyGeneration();

        int p = key[0];
        int q = key[1];
        int n = key[2];

        System.out.println("\n======Generate_Keys======");
        System.out.println(String.format("p = %s, q = %s, n = %s", p, q, n));

        int cipherText = Encryption(n, plainText);

        System.out.println("\n=======Cipher_Text======");
        System.out.println("CipherText = " + cipherText);

        int[] plainTexts = Decryption(p, q, n, cipherText);

        System.out.println("\n======Possible_Plain_Text======");
        for (int i = 0; i < plainTexts.length; i++) {
            System.out.println("p" + (i + 1) + " = " + plainTexts[i]);
        }
    }

    private static int[] KeyGeneration() {
        int p = P;
        int q = Q;
        int n = p * q;
        return new int[]{p, q, n};
    }

    private static int Encryption(int n, int plainText) {
        return Math.floorMod((int) Math.pow(plainText, 2), n);
    }

    private static int[] Decryption(int p, int q, int n, int cipherText) {
        int mp = modSqrt(cipherText, p);
        int mq = modSqrt(cipherText, q);

        int[] coefficients = calcCoefficients(p, q);
        int yP = coefficients[0];
        int yQ = coefficients[1];

        int p1 = Math.floorMod(yP * p * mq + yQ * q * mp, n);
        int p2 = Math.floorMod(yP * p * mq - yQ * q * mp, n);
        int p3 = Math.floorMod(yP * p * (-mq) + yQ * q * mp, n);
        int p4 = Math.floorMod(yP * p * (-mq) - yQ * q * mp, n);

        return new int[]{p1, p2, p3, p4};
    }

    private static int[] calcCoefficients(int p, int q) {
        int[] gcdResult;
        do {
            gcdResult = gcd(p, q);
            p = gcdResult[1];
            q = gcdResult[2];
        } while (gcdResult[0] != 1);

        int yP = gcdResult[1];
        int yQ = gcdResult[2];

        return new int[]{yP, yQ};
    }

    private static int[] gcd(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0};
        }
        int[] vals = gcd(b, a % b);
        int d = vals[0];
        int x = vals[2];
        int y = vals[1] - (a / b) * vals[2];
        return new int[]{d, x, y};
    }

    private static int modSqrt(int a, int p) {
        int s = p - 1;
        int e = 0;
        while (s % 2 == 0) {
            s /= 2;
            e += 1;
        }

        // Find a non-quadratic residue mod p
        int n = 2;
        while (LegendreSymbol(n, p) != -1) {
            n += 1;
        }

        int x = modExp(a, (s + 1) / 2, p);
        int b = modExp(a, s, p);
        int g = modExp(n, s, p);
        int r = e;

        while (true) {
            int t = b;
            int m = 0;
            for (m = 0; m < r; m++) {
                if (t == 1) {
                    break;
                }
                t = modExp(t, 2, p);
            }

            if (m == 0) {
                return x;
            }

            int gs = modExp(g, 1 << (r - m - 1), p);
            g = modExp(gs, 2, p);
            x = (x * gs) % p;
            b = (b * g) % p;
            r = m;
        }
    }

    private static int LegendreSymbol(int a, int p) {
        int ls = modExp(a, (p - 1) / 2, p);
        if (ls == p - 1) {
            return -1;
        }
        return ls;
    }

    private static int modExp(int base, int exp, int modulus) {
        if (modulus == 1) {
            return 0;
        }
        long result = 1;
        long b = base % modulus;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * b) % modulus;
            }
            exp = exp >> 1;
            b = (b * b) % modulus;
        }
        return (int) result;
    }
}
