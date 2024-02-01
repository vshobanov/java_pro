package ru.inno.lesson3;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class MainApplication {
    public static void main(String[] args) throws IOException {

        String inputString = "Имеется массив строк в каждой из которых лежит набор из 5 строк разделенных пробелом найдите среди всех слов самое длинное если таких слов несколько получите любое из них";

        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("Исходный массив:" + list);
        System.out.println("Дедублицированный массив:" + list.stream()
                .distinct()
                .toList());
        System.out.println("3-е наибольшее число (без дедубликации):" + list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .stream()
                .toList());
        System.out.println("3-е наибольшее число:" + list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .stream()
                .toList());

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 35, "Engineer"));
        employees.add(new Employee("Bob", 21, "Engineer"));
        employees.add(new Employee("Sasha", 45, "Engineer"));
        employees.add(new Employee("Vova", 50, "Manager"));
        employees.add(new Employee("Alex", 50, "Chief"));
        employees.add(new Employee("Ivan", 28, "Engineer"));

        System.out.println("Список имен 3-х инженеров:" + employees.stream()
                .filter(employee -> employee.getPosition().equals("Engineer"))
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .toList());
        System.out.println("Средний возраст сотрудников:" + employees.stream()
                .filter(employee -> employee.getPosition().equals("Engineer"))
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0));


        Map<String, Integer> wordFrequency = Arrays.stream(inputString.split(" "))
                .collect(Collectors.toMap(x -> x, x -> 1, Integer::sum));
        System.out.println(wordFrequency);

        System.out.println(Arrays.stream(inputString.split(" "))
                .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
                .toList());

        System.out.println("Самое длинное слово в файле:" + Files.lines(Paths.get("ru/inno/lesson3/words.txt"))
                .map(line -> (line.split(" ")))
                .flatMap(Arrays::stream)
                .sorted(Comparator.comparing(String::length).reversed()).limit(1).toList());
    }

}
