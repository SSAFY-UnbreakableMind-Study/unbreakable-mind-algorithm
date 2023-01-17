public class BOJ_9461_S3_파도반수열{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] dp = new int[101];
        for(int i=1;i<3;i++){
            dp[i] = 1;
        }
        dp[3] = dp[4] = 2;
        for(int i=5;i<101;i++){
            dp[i] = dp[i-1] + dp[i-5];
        }
        for(int t=1;t<=T;t++){
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}