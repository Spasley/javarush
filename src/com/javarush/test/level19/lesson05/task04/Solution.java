package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sourceFileName = reader.readLine();
        String targetFileName = reader.readLine();

        FileReader fileReader = new FileReader(sourceFileName);
        FileWriter writer = new FileWriter(targetFileName);
        while (fileReader.ready()) {
            char currentByte = (char) fileReader.read();
            if ((String.valueOf(currentByte)).equals(".")) {
                writer.write(33);
            }
            else { writer.write(currentByte); }
        }
        reader.close();
        fileReader.close();
        writer.close();

    }
}
