package exam;

public class TwoArrayLab4 {
    public static void main(String[] args) {
        int array[][] = new int[][]{{10, 20, 30, 40, 50}, {5, 10, 15},{11, 22, 33, 44}, {9, 8, 7, 6, 5, 4, 3, 2, 1}};
        int rowSum;
        String rst = "";
        for(int i = 0; i < array.length; i++) {
            rowSum = 0;
            for(int j = 0; j < array[i].length; j++) {
                rowSum += array[i][j];
            }
            rst += (i + 1) + "행의 합은 " + rowSum + "입니다.\n";

        }
        System.out.println(rst);
    }
}
