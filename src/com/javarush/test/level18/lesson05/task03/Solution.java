package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        FileInputStream fis = new FileInputStream(file1);

        while (fis.available() > 0) {
            if (!(fis.available() % 2 == 0))
            {
                byte[] buffer = new byte[(fis.available() / 2) + 1];
                int count = fis.read(buffer);
                FileOutputStream fos = new FileOutputStream(file2);
                fos.write(buffer, 0, count);
                buffer = new byte[fis.available()];
                count = fis.read(buffer);
                FileOutputStream fos2 = new FileOutputStream(file3);
                fos2.write(buffer, 0, count);
                fos.close();
                fos2.close();

            }
            else {
                byte[] buffer = new byte[fis.available() / 2];
                int count = fis.read(buffer);
                FileOutputStream fos = new FileOutputStream(file2);
                fos.write(buffer, 0, count);

                count = fis.read(buffer);
                FileOutputStream fos2 = new FileOutputStream(file3);
                fos2.write(buffer, 0, count);
                fos.close();
                fos2.close();
            }


        }
        fis.close();
        reader.close();


    }
}
