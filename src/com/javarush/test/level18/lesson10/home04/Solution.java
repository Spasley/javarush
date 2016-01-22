package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1;
        String fileName2;



        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
            FileInputStream fis1 = new FileInputStream(fileName1);
            FileInputStream fis2 = new FileInputStream(fileName2);

            byte[] file1Content = new byte[fis1.available()];
            fis1.read(file1Content);
            byte[] file2Content = new byte[fis2.available()];
            FileOutputStream fos = null;
            while (fis2.available() > 0)
            {
                int count = fis2.read(file2Content);
                fos = new FileOutputStream(fileName1);
                fos.write(file2Content, 0, count);
            }

            fos = new FileOutputStream(fileName1, true);
            fos.write(file1Content);
            fos.close();
            fis1.close();
            fis2.close();
            reader.close();


        }
        catch (IOException e) {}
    }
}
