package day13;

import java.io.*;
import java.net.URL;

public class ImageIOLab {
    public static void main(String[] args) {
        try(FileReader fileReader = new FileReader("C:/iotest/list.txt");
        BufferedReader br = new BufferedReader(fileReader);
            ) {
            String stArr[];
            String title;
            String s;
            URL url;
            File file;
            File directory = new File("C:/iotest/myimage");
            if(!directory.exists()) {
                directory.mkdir();
            }
            while(true) {
                s = br.readLine();
                if(s == null) return;
                stArr = s.split(",");
                title = stArr[0];
                url = new URL(stArr[1]);
                file = new File("C:/iotest/myimage/" + title + ".jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(url.openStream().readAllBytes());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
