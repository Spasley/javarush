package com.javarush.test.level17.lesson10.home09;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1;
        String filename2;
        FileInputStream fis1;
        FileInputStream fis2;

        try
        {
            filename1 = reader.readLine();
            filename2 = reader.readLine();
            fis1 = new FileInputStream(filename1);
            fis2 = new FileInputStream(filename2);

            while (fis1.available() > 0)
            {
                reader = new BufferedReader(new InputStreamReader(fis1));
                String temp = reader.readLine();
                while ((temp) != null)
                {
                    allLines.add(temp);
                    temp = reader.readLine();
                    fis1.read();

                }
            }


            while (fis2.available() > 0)
            {
                reader = new BufferedReader(new InputStreamReader(fis2));
                String temp = reader.readLine();
                while ((temp) != null)
                {
                    forRemoveLines.add(temp);
                    temp = reader.readLine();
                    fis1.read();
                }
            }

            new Solution().joinData();
        }
        catch (IOException e) {}


    }

    public void joinData () throws CorruptedDataException {
        boolean allMatch = true;
        for (String item : forRemoveLines) {
            if (!allLines.contains(item)) {
                allMatch = false;
            }
        }
        if (allMatch) {
            for (String item : forRemoveLines) {
                if (allLines.contains(item)) {
                    allLines.remove(item);
                }
            }
        }
        else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
