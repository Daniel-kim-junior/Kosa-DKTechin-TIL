package day12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class FileOutLab {
    public static void main(String[] args) {
        String path = "C:/iotest";
        File isDir = new File(path);
        if (!isDir.exists()) {
            isDir.mkdirs();
        }

        try(FileWriter fileWriter = new FileWriter("C:/iotest/event.txt")) {
            LocalDateTime localDateTime = LocalDateTime.of(2023, 5, 5, 0, 0);
            fileWriter.write( localDateTime.getYear() + "년 "+ localDateTime.getDayOfMonth() + "월 "+ localDateTime.getDayOfMonth() +"일은 " +
                    localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) + "입니다." + "\n");
            System.out.println(localDateTime.getYear() + "년 "+ localDateTime.getDayOfMonth() + "월 "+ localDateTime.getDayOfMonth() +"일은 " +
                    localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) + "입니다." + "\n");
            LocalDateTime localDateTime2 = LocalDateTime.of(2023, 6, 6, 0, 0);
            fileWriter.write( localDateTime2.getYear() + "년 "+ localDateTime2.getDayOfMonth() + "월 "+ localDateTime2.getDayOfMonth() +"일은 " +
                    localDateTime2.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) + "요일입니다." + "\n");
            System.out.println(localDateTime2.getYear() + "년 "+ localDateTime2.getDayOfMonth() + "월 "+ localDateTime2.getDayOfMonth() +"일은 " +
                    localDateTime2.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN) + "입니다." + "\n");
            System.out.println("저장이 완료되었습니다.");
        } catch (IOException e) {
            System.out.println("파일에 저장하는 동안 오류가 발생했습니다.");
        }

    }
}
