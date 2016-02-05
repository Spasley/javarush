package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public volatile static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        String name = "";
        Sex sex = null;
        int id;
        Date bd = null;
        String firstParam = args[0];
        switch (firstParam) {
            case "-c" :
                for (String paramValue : args) {
                    try{
                        bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(paramValue);
                        synchronized (allPeople)
                        {
                            allPeople.add((sex == Sex.MALE ? Person.createMale(name.trim(), bd) : Person.createFemale(name.trim(), bd)));
                            System.out.println(allPeople.indexOf(allPeople.get(allPeople.size() - 1)));
                        }
                        name = "";
                    }
                    catch (ParseException e)
                    {
                        if (!(paramValue.length() == 1) && !paramValue.equals("-c"))
                        {
                            name += paramValue;
                            name += " ";
                        } else if (paramValue.length() == 1)
                        {
                            if (paramValue.equals("ж"))
                            {
                                sex = Sex.FEMALE;
                            } else
                            {
                                sex = Sex.MALE;
                            }
                        }
                    }
                }
                break;
            case "-u" :
                id = Integer.parseInt(args[1]);
                for (String paramValue : args)
                {
                    try
                    {
                        bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(paramValue);
                        synchronized (allPeople)
                        {
                            allPeople.set(id, (sex == Sex.MALE ? Person.createMale(name.trim(), bd) : Person.createFemale(name.trim(), bd)));
                        }
                        name = "";
                    }
                    catch (ParseException e)
                    {
                        if (!(paramValue.length() == 1) && !paramValue.equals("-u"))
                        {
                            name += paramValue;
                            name += " ";
                        } else if (paramValue.length() == 1)
                        {
                            if (paramValue.equals("ж"))
                            {
                                sex = Sex.FEMALE;
                            } else
                            {
                                sex = Sex.MALE;
                            }
                        }
                    }
                }
                break;
            case "-i" :
                for (String paramValue : args) {
                    if (!paramValue.equals("-i")) {
                        id = Integer.parseInt(paramValue);
                        System.out.println(allPeople.get(id).getName() + " " + (allPeople.get(id).getSex() == Sex.MALE ?
                        "м" : "ж") + " " + new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(allPeople.get(id).getBirthDay()));
                    }
                }
                break;
            case "-d" :
                for (String paramValue : args) {
                    if (!paramValue.equals("-d")) {
                        id = Integer.parseInt(paramValue);
                        synchronized (allPeople)
                        {
                            allPeople.set(id, Person.createFemale(null, null));
                            allPeople.get(id).setSex(null);
                        }
                    }
                }
                break;
        }
    }
}
