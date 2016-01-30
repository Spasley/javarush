package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String url = reader.readLine();
            String[] urlAndParameters = url.split("\\?");
            String[] parametersAndValuesPairs = urlAndParameters[1].split("&");
            String output = " ";
            for (String paramValuePair : parametersAndValuesPairs) {
                String[] pair = paramValuePair.split("=");
                output += (pair[0] + " ");
            }
            System.out.println(output.trim());
            for (String paramValuePair : parametersAndValuesPairs) {
                String[] param = paramValuePair.split("=");
                if (param[0].equals("obj")) {
                    try {
                        alert(Double.parseDouble(param[1]));
                    }
                    catch (ClassCastException | NumberFormatException e) {
                        alert(param[1]);
                    }
                }
            }
            reader.close();

        }
        catch (IOException e) {}
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
