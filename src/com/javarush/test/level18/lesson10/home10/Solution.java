package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        //собираю имена файлов в список
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        ArrayList<String> fileNames = new ArrayList<>();
        while (!input.equals("end")) {
            fileNames.add(input);
            input = reader.readLine();
        }

        //определяю имя целого файла и создаю его (туду: выучить регэкспы)
        String outputFileName = "";
        for (int i = 0; i < fileNames.get(0).split("\\.").length - 1; i++) {
            outputFileName += fileNames.get(0).split("\\.")[i] + " ";
        }
        outputFileName = outputFileName.trim();
        outputFileName = outputFileName.replace(" ", ".");
        File outputFile = new File(outputFileName);

        //ищу в списке имен файлов тот, чье название оканчивается на очередной индекс, считая, что имен файлов у меня в
        //списке столько же, сколько частей. Когда нашел, записываю содержимое файла с таким именем в буфер размером под
        //этот файл, а следом - содержимое буфера в итоговый файл.
        FileOutputStream fos = new FileOutputStream(outputFile);
        for (int i = 0; i <= fileNames.size(); i++) {
            for (String fileName : fileNames) {
                if (fileName.endsWith(String.valueOf(i))) {
                    FileInputStream fis = new FileInputStream(fileName);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fos.write(buffer);
                    fos.flush();
                    fis.close();
                }
            }
        }
        fos.close();
        reader.close();
    }
}
