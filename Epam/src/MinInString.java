import java.util.Scanner;

public class MinInString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(solve(s));
    }
    private static char solve(String s) {
        int[] freq = new int[62];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                freq[s.charAt(i) - 'a']++;
            }
            else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                freq[26 + s.charAt(i) - 'A']++;
            }
            else {
                freq[52 + s.charAt(i) - '0']++;
            }
        }
        // finding min freq in array :
        int minFreq = Integer.MAX_VALUE;
        for (int i = 0; i < 62; i++) {
            if (freq[i] > 0) {
                minFreq = Math.min(freq[i], minFreq);
            }
        }

        // extracting the first number having this minFreq:
        for (char ch : s.toCharArray()) {
            int index;
            if (ch >= 'a' && ch <= 'z') {
                index = ch - 'a';
            }
            else if (ch >= 'A' && ch <= 'Z') {
                index = 26 + ch - 'A';
            }
            else {
                index = 52 + ch - '0';
            }

            if (minFreq == freq[index]) {
                return ch;
            }
        }
        return '0';
    }
}
