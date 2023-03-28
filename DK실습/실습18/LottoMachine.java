package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class LottoMachine {
    int lottoArray[];

    public LottoMachine() {
        lottoArray = new int[6];
    }

    public void createLottoNums() {
        Random random = new Random();
        for(int i = 0; i < 6; i++) {
            lottoArray[i] = random.nextInt(20) + 1;
        }
    }

    public void checkLottoNums() throws DuplicateException {
        int checked[] = new int[21];
        for(int i = 0; i < 6; i++) {
            checked[lottoArray[i]]++;
            if(checked[lottoArray[i]] > 1) {
                throw new DuplicateException();
            }
        }

    }

    public int[] getNums() {
        return lottoArray;
    }
}
