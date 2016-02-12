package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        String key = args[0];

        //проверяем значение параметра
        if (key.equals("-c")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            File catalog = new File(reader.readLine());
            String productName = args[1];
            String price = args[2];
            String quantity = args[3];
            int lastId = 0;
            int maxId = 0;

            //сканируем файл и ищем максимальный айди, если в файле уже есть записи
            Scanner catalogScanner = new Scanner(catalog);
            catalogScanner.useDelimiter("\\n");
            if (catalogScanner.hasNext()) {
                while (catalogScanner.hasNext())
                {
                    String lastLine = catalogScanner.next();
                    lastId = Integer.parseInt(lastLine.substring(0, 8).trim());
                    maxId = lastId > maxId ? lastId : maxId;
                }
            }

            //подготавливаем данные для записи - если длина строки больше заданной, обрезаем до нужной (???) если меньше -
            //добиваем пробелами
            lastId = maxId == 0 ? 1 : ++maxId;
            String lastIdAsString = String.valueOf(lastId);
            String idForWrite = lastIdAsString.length() == 8 ? lastIdAsString.substring(0, 8) :
                    lastIdAsString + new String(new char[8 - lastIdAsString.length()]).replace("\0", " ");
            productName = productName.length() == 30 ? productName.substring(0, 30) :
                    productName + new String(new char[30 - productName.length()]).replace("\0", " ");
            price = price.length() == 8 ? price.substring(0, 8) :
                    price + new String(new char[8 - price.length()]).replace("\0", " ");
            quantity = quantity.length() == 4 ? quantity.substring(0, 4) :
                    quantity + new String(new char[4 - quantity.length()]).replace("\0", " ");

            //создаем "писатель", записываем строку в конец файла, закрываем потоки/сканеры
            FileWriter writer = new FileWriter(catalog, true);
            writer.write(idForWrite + productName + price + quantity + "\n");
            writer.flush();
            catalogScanner.close();
            writer.close();
            reader.close();
        }

    }
}
