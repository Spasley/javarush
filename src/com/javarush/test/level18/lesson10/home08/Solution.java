package com.javarush.test.level18.lesson10.home08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> fileNames = new ArrayList<>();
        String input = reader.readLine();
        while (!input.equals("exit")) {
            fileNames.add(input);
            input = reader.readLine();
        }
        for (String file : fileNames) {
            ReadThread newT = new ReadThread(file);
            newT.start();
            newT.join();
        }
        reader.close();


    }

    public static class ReadThread extends Thread {
        String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
            //implement constructor body
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run() {
            HashMap<String, Integer> temp = new HashMap<>();
            try
            {
                FileInputStream fis = new FileInputStream(fileName);
                int currentByte = fis.read();
                while (fis.available() > 0) {
                    if (!temp.containsKey(String.valueOf(currentByte)))
                    {
                        temp.put(String.valueOf(currentByte), 1);
                        currentByte = fis.read();
                    }
                    else {
                        temp.put(String.valueOf(currentByte), temp.get(String.valueOf(currentByte)) + 1);
                        currentByte = fis.read();
                    }
                }
                int maxValue = 0;
                int keyOfMaxValue = 0;
                for (Map.Entry<String, Integer> map : temp.entrySet()) {
                    if (map.getValue() > maxValue) {
                        maxValue = map.getValue();
                        keyOfMaxValue = Integer.parseInt(map.getKey());
                    }
                }
                resultMap.put(fileName, keyOfMaxValue);
                fis.close();
            }
            catch (IOException e) {}
        }


    }
}
