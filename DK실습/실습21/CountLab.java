package day12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountLab {
    public static void main(String[] args) {

        try(FileReader fr = new FileReader("c:/iotest/yesterday.txt");
                BufferedReader br = new BufferedReader(fr)) {
            int cnt = 0;
            String stA[];
            while (true) {
                String str = br.readLine();

                if(str == null) {
                    System.out.printf("yesterday 라는 단어는 %d개 있습니다.", cnt);
                    return;
                }
                stA = str.split(" ");
                for(String s : stA) {
                    s = s.replaceAll("[\\s.]", "");
                    if(s.toLowerCase().equals("yesterday")) cnt++;
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
