package lotto;

public class LottoGame {
    public static void main(String[] args) throws DuplicateException {
        LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.createLottoNums();
        lottoMachine.checkLottoNums();
        printArray(lottoMachine.lottoArray);
    }

    static void printArray(int[] p2) {
        String rst = "";
        for(int i = 0; i < p2.length - 1; i++) {
            rst += p2[i] + ", ";
        }
        rst += p2[p2.length - 1];
        System.out.println(rst);
    }
}
