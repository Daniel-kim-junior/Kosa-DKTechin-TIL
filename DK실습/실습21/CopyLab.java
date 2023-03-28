package day12;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CopyLab {
    public static <f> void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String yyyyMmDd = localDateTime.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        try(FileReader fr = new FileReader("C:/iotest/sample.txt");
                BufferedReader br = new BufferedReader(fr);
            FileWriter writer = new FileWriter("C:/iotest/sample_"+ yyyyMmDd +".txt", true);
                    PrintWriter out = new PrintWriter(writer)) {
               while(true) {
                   String s = br.readLine();
                   if(s == null) {
                       System.out.println("저장 완료되었습니다.");
                       return;
                   }
                   out.println(s);
               }
        } catch (FileNotFoundException e) {
            System.out.println("sample.txt 파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력을 처리하는 동안 오류가 잘생했습니다.");
        }

    }
}
