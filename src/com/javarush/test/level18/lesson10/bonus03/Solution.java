package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

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
            String id = args[1];
            String productName = args[2];
            String price = args[3];
            String quantity = args[4];
            String lineForUpdate;

            //сканируем файл и ищем максимальный айди, если в файле уже есть записи
            Scanner catalogScanner = new Scanner(catalog);
            catalogScanner.useDelimiter("\\n");
            if (catalogScanner.hasNext()) {
                while (catalogScanner.hasNext())
                {
                    String lastLine = catalogScanner.next();
                    String currentId = lastLine.substring(0, 8).trim());
                    if (currentId.equals(id)) {
                        lineForUpdate = lastLine;
                        break;
                    }
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
