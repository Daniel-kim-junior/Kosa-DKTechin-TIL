package exam;

public class TwoArrayLab1 {
    public static void main(String[] args) {
        int len = 4;
        int arr[][] = new int[len][len];
        int start = 10;
        String threeRowTier = "";
        String twoColTier = "";
        String leftCross = "";
        String rightCross = "";
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                arr[i][j] = start;
                start += 2;
                if(i == 0 && j == 0) {
                    System.out.printf("1행 1열의 데이터 %d\n", arr[i][j]);
                }
                if(i == 2 && j == 3) {
                    System.out.printf("3행 4열의 데이터 %d\n", arr[i][j]);

                }
                if(i == 2) {
                    threeRowTier += arr[i][j] + " ";
                }
                if(j == 1) {
                    twoColTier += arr[i][j] + " ";
                }
                if(i == j) {
                    leftCross += arr[i][j] + " ";
                }
                if(i + j == len - 1) {
                    rightCross += arr[i][j] + " ";
                }

            }
        }
        System.out.printf("행의 개수 : %d\n", len);
        System.out.printf("열의 개수 : %d\n", len);
        System.out.printf("3행의 데이터들 : %s\n", threeRowTier);
        System.out.printf("2열의 데이터들 : %s\n", twoColTier);
        System.out.printf("왼쪽 대각선의 데이터들 : %s\n", leftCross);
        System.out.printf("오른쪽 대각선의 데이터들 : %s\n", rightCross);




    }
}
