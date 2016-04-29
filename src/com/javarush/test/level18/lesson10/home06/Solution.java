package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources


Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        //args = new String[1];
        //args[0] = "/home/volodka/data";
        String filename = args[0];
        TreeMap<Byte, Integer> result = new TreeMap<Byte, Integer>();
        FileReader fis = new FileReader(filename);
        byte currentByte = (byte) fis.read();
        while (fis.ready()) {
            if (result.containsKey(currentByte)) {
                result.put(currentByte, result.get(currentByte) + 1);
            }
            else /*if (currentByte != 10 && currentByte != 13)*/{
                result.put(currentByte, 1);
            }
            currentByte = (byte) fis.read();
        }
        fis.close();
        for (Map.Entry<Byte, Integer> map : result.entrySet()) {
            System.out.println(String.valueOf(Character.toChars((int) map.getKey())) + " " + map.getValue());
        }
    }
}
