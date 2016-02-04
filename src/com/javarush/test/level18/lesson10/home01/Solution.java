package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        //args = new String[1];
        //args[0] = "/home/volodka/data";
        String fileName = args[0];
        int count = 0;
        try {
        FileReader fileReader = new FileReader(fileName);
        while (fileReader.ready()) {
            char ch = (char) fileReader.read();
            if (String.valueOf(ch).matches("[a-zA-Z]")) {
                count++;
            }
        }
            fileReader.close();
        }
        catch (IOException e) {}
        System.out.println(count);

    }
}
