package com.javarush.test.level18.lesson03.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream fis = new FileInputStream(fileName);
        byte maxByte = -128;
        byte temp;
        while (fis.available() > 0) {
            temp = (byte) fis.read();
            maxByte = temp > maxByte ? temp : maxByte;
        }
        System.out.println(maxByte);
        reader.close();
        fis.close();
    }
}
