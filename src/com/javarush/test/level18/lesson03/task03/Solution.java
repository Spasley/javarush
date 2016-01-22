package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        HashMap<Byte, Integer> bytes = new HashMap<Byte, Integer>();

        FileInputStream fis = new FileInputStream(fileName);
        Byte temp;

        while (fis.available() > 0) {
            temp = (byte) fis.read();
            if (bytes.containsKey(temp)) {
                bytes.put(temp, bytes.get(temp) + 1);
            }
            else {
                bytes.put(temp, 1);
            }
        }

        int maxValue = 1;
        for (Map.Entry<Byte, Integer> entry : bytes.entrySet()) {
            int tempValue = entry.getValue();
            maxValue = tempValue > maxValue ? tempValue : maxValue;

        }

        for (Map.Entry<Byte, Integer> entry : bytes.entrySet()) {
            if (entry.getValue() == maxValue) {
                System.out.println(entry.getKey());
            }

        }

    }
}
