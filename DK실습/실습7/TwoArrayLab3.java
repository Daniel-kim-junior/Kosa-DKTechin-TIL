package exam;

public class TwoArrayLab3 {
    public static void main(String[] args) {
        int timeTable[][] = new int[][]{{5, 2, 1, 0, 2, 3, 6}, {4, 3, 2, 1, 1, 0, 5}, {3, 1, 2, 1, 3, 1, 3}, {4, 3, 1, 0, 4, 2, 7}};
        int resultDay[] = new int[7];
        int resultWeek[] = new int[4];
        String resultW = "";
        String dayofWeek[] = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 7; j++) {
                resultDay[j] += timeTable[i][j];
                resultWeek[i] += timeTable[i][j];
            }
            resultW += (i+1) + "주차" + " : " + resultWeek[i] + "시간\n";
        }
        String resultD = "";

        for(int i = 0; i < 7; i++) {
            resultD += dayofWeek[i] + " : " + resultDay[i] + "시간\n";
        }


        System.out.println(resultD);
        System.out.println();
        System.out.println(resultW);





    }
}
