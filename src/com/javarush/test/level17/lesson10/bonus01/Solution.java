package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        String name;
        String sex;
        Date bd;
        int id;
        String firstParam = args[0];
        switch (firstParam) {
            case "-c" :
                name = args[1];
                sex = args[2];
                bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                if (sex.equals("ж")) {
                    allPeople.add(Person.createFemale(name, bd));
                }
                else if (sex.equals("м")) {
                    allPeople.add(Person.createMale(name, bd));
                }
            case "-u" :
                id = Integer.parseInt(args[1]);
                name = args[2];
                sex = args[3];
                bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
                if (sex.equals("ж"))
                {
                    allPeople.set(id, Person.createFemale(name, bd));
                }
                else if (sex.equals("м")) {
                    allPeople.set(id, Person.createMale(name, bd));
                }
            case "-i" :
                id = Integer.parseInt(args[1]);
                System.out.println(id + ":" + " " + allPeople.get(id).getName() + " " + allPeople.get(id).getSex() + " " +
                allPeople.get(id).getBirthDay());
            case "-d" :
                id = Integer.parseInt(args[1]);
                allPeople.set(id, Person.createMale(null, null));



        }
        /*for (Person p : allPeople) {
            System.out.println(p + ":" + " " + allPeople.get(p).getName() + " " + allPeople.get(p).getSex() + " " +
                    allPeople.get(p).getBirthDay());
        }*/
    }
}
