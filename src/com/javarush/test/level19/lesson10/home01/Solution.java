package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
1.
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];
        TreeMap<String, Double> vedomost = new TreeMap<>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.ready()) {
                String[] ar = reader.readLine().split(" ");
                if (vedomost.containsKey(ar[0])) {
                    vedomost.put(ar[0], vedomost.get(ar[0]) + Double.parseDouble(ar[1]));
                }
                else {
                    vedomost.put(ar[0], Double.parseDouble(ar[1]));
                }
            }
            for (Map.Entry<String, Double> map: vedomost.entrySet()) {
                System.out.println(map.getKey() + " " + map.getValue());
            }
            reader.close();

        }
        catch (IOException e) {}



    }
}
