package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = "/home/volodka/data";
        String fileName = args[0];
        ArrayList<Byte> alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alphabet.add((byte) (i + 65));
        }
        for (int i = 27; i <= 52; i++) {
            alphabet.add((byte) (i + 70));
        }
        try
        {
            int count = 0;
            FileInputStream fis = new FileInputStream(fileName);
            while (fis.available() > 0) {
                byte temp = (byte) fis.read();
                if (alphabet.contains(temp)) {
                    count++;
                }
            }
            System.out.println(count);
            fis.close();
        }
        catch (IOException e) {}

        
    }
}
