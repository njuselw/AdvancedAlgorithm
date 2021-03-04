package SecondExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0208 {
    /*
    有9个因数的数
    Description
    Find the count of numbers less than N having exactly 9 divisors
    1<=T<=1000,1<=N<=10^12

    Input
    First Line of Input contains the number of testcases.
    Only Line of each testcase contains the number of members N in the rival gang.

    Output
    Print the desired output.
     */

    //9个因数的数应当满足以下条件之一
    // x = a^2 * b^2 (a != b) || x = a ^ 8
    //上式中a, b均为质数
    //由于当a,b超过sqrt(n)后，上式结果必然超过n，因此舍去这部分的素数
    public static int getNineDivisorsNum(long n, List<Integer> primes) {
        int cnt = 0;
        //x = a^2 * b^2
        for (int i = 0; i < primes.size() - 1; i++) {
            for (int j = i + 1; j < primes.size(); j++) {
                if (Math.pow(primes.get(i), 2) * Math.pow(primes.get(j), 2) > n) {
                    break;
                }
                cnt++;
            }
        }
        for (int i = 0; i < primes.size(); i++) {
            if (Math.pow(primes.get(i), 8) > n) {
                break;
            }
            cnt++;
        }
        return cnt;
    }

    public static List<Integer> getPrime(long n) {
        List<Integer> primes = new ArrayList<>();
        int size = (int) Math.sqrt(n) + 1;
        int[] isPrime = new int[size];
        for (int i = 2; i < size; i++) {
            if (isPrime[i] == 0) {
                primes.add(i);
                //将所有素数的倍数标记为合数
                for (int j = i * i; j < size; j += i) {
                    isPrime[j] = 1;
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            long N = scanner.nextLong();
            List<Integer> primeList = getPrime(N);
            System.out.println(getNineDivisorsNum(N, primeList));
        }
    }
}
