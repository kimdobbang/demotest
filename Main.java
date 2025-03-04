import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(sum, coins));
    }

    // 알고리즘 구현 코드
    static int solution(int sum, int[] coins) {
        int[] dp = new int[sum + 1]; // dp 정의
        dp[0] = 1;

        for (int c : coins) {
            for (int i = c; i <= sum; i++) {
                dp[i] += dp[i - c];
            }
        }
        return dp[sum];
    }

}









