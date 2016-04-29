package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter pw = new PrintWriter(outputStream);
            String delimiter = "person delimiter";
            boolean isHasName = this.name!=null;
            pw.println(isHasName);
            if (isHasName) {
                pw.println(this.name);
            }
            boolean isHasAssets = this.assets!=null;
            pw.println(isHasAssets);
            if (isHasAssets) {
                for (Asset asset : assets) {
                    pw.println(asset.getName());
                    pw.println(asset.getPrice());
                }
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (inputStream.available() > 0) {
                String currentLine = reader.readLine();
                if (currentLine.equals("person delimiter"))
                {
                    boolean isHasName = Boolean.getBoolean(reader.readLine());
                    if (isHasName)
                    {
                        String name = reader.readLine();
                        boolean isHasAssets = Boolean.getBoolean(reader.readLine());
                        ArrayList<Asset> assets = new ArrayList<>();
                        while (isHasAssets)
                        {
                            String assetName = reader.readLine();
                            Asset asset = new Asset(assetName);
                            asset.setPrice(Double.parseDouble(reader.readLine()));
                            assets.add(asset);
                            currentLine = (reader.readLine());
                            if (currentLine.equals("person delimiter")) { continue; } else {break;}
                        }
                    }
                }
            }
        }
    }
}
