package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = "/home/volodka/data";
        try
        {

            int countAll = 0;
            int countSpaces = 0;
            FileInputStream fis = new FileInputStream(args[0]);
            while (fis.available() > 0) {
                byte temp = (byte) fis.read();
                if (temp == 32) {
                    countSpaces++;
                }
                countAll++;
            }
            System.out.printf("%.2f", ((float) countSpaces / countAll) * 100);
            fis.close();
        }
        catch (IOException e) {}
    }
}
