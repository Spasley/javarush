package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке


*/

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        //args = new String[1];
        //args[0] = "123";
        int parameter = Integer.parseInt(args[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        String result = null;
        try {
            fileName = reader.readLine();
            Scanner read = new Scanner(new File(fileName));
            while (read.hasNext()) {
                String temp = read.nextLine();
                String[] currentLine = temp.split(" ");
                if (currentLine[0].equals(args[0])) {
                    result = temp;
                    break;
                }
            }
            if (!(result == null))
            {
                System.out.println(result);
            }
            read.close();
            reader.close();
        }
        catch (IOException e) {}
    }
}
