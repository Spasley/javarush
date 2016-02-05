package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution
{
    public static void main(String[] args)
    {
        String key = args[0];
        switch (key) {
            case "-e" :
                encode(args[1], args[2]);
            case "-d" :
                decode(args[1], args[2]);
        }

    }

    public static void encode(String fileName, String fileOutputName)
    {
        FileReader fileReader;
        FileWriter fileWriter;
        try
        {
            fileReader = new FileReader(fileName);
            fileWriter = new FileWriter(fileOutputName, true);
            while (fileReader.ready()) {
                int temp = fileReader.read();
                fileWriter.write(temp + 1);
            }
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void decode(String fileName, String fileOutputName)
    {
        FileReader fileReader;
        FileWriter fileWriter;
        try
        {
            fileReader = new FileReader(fileName);
            fileWriter = new FileWriter(fileOutputName);
            while (fileReader.ready()) {
                int temp = fileReader.read();
                fileWriter.write(temp - 1);
            }
        }
        catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
