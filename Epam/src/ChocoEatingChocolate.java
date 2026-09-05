import java.util.Scanner;

public class ChocoEatingChocolate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // contains the number of chocolates
        int money = sc.nextInt();   // Money choco has
        String s = sc.next();
        int[] price_chocolate = new int[26];
        for (int i = 0; i < 26; i++) {
            price_chocolate[i] = sc.nextInt();
        }
        int ans = solve(n, money, s, price_chocolate);
        System.out.println(ans);
    }

    private static int solve(int n, int money,String s, int[] price_of_chocolate) {
        int l = 0;
        int maxLen = 0;
        int maxFreq_number = 0;
        int[] price_when_bought = new int[26];
        int total = 0;

        for (int r = 0; r < n; r++) {
            int index_of_chocolate = s.charAt(r) - 'a';

            total += price_of_chocolate[index_of_chocolate];
            price_when_bought[index_of_chocolate] += price_of_chocolate[index_of_chocolate];

            maxFreq_number = Math.max(maxFreq_number, price_when_bought[index_of_chocolate]);

            if (total - maxFreq_number > money) {
                int left_index = s.charAt(l) - 'a';
                price_when_bought[left_index] -= price_of_chocolate[left_index];
                total -= price_of_chocolate[left_index];

                l++;

                maxFreq_number = 0;
                for (int i = 0; i < 26; i++) {
                    maxFreq_number = Math.max(maxFreq_number, price_when_bought[i]);
                }
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}
