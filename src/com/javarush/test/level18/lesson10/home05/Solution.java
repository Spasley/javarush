package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String inputFile = reader.readLine();
            String outputFile = reader.readLine();
            Writer fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile, true), "utf-8"));
            Scanner read = new Scanner(new File(inputFile));
            read.useDelimiter(" ");
            double value = 0;
            while (read.hasNext()) {
                value = Double.parseDouble(read.next());
                int rounded = (int) Math.round(value);
                System.out.println(rounded);
                fos.write(Integer.toString(rounded));
                if (!read.hasNext())
                {

                    fos.flush();
                }
                else {
                    fos.write(" ");
                    fos.flush();
                }
            }
            fos.close();
            read.close();
            reader.close();

        }
        catch (IOException e) {}

    }
}
