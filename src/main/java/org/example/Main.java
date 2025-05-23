package org.example;


import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Ali", "Yılmaz"));
        employees.add(new Employee(2, "Ayşe", "Demir"));
        employees.add(new Employee(3, "Mehmet", "Kaya"));
        employees.add(new Employee(2, "Ayşe", "Demir")); // Duplicate
        employees.add(new Employee(1, "Ali", "Yılmaz")); // Duplicate

        System.out.println("Duplicates:");
        findDuplicates(employees).forEach(System.out::println);

        System.out.println("\nUniques (at most one per duplicate):");
        findUniques(employees).forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\nAfter removing all duplicates:");
        removeDuplicates(employees).forEach(System.out::println);



        Map<String, Integer> result = WordCounter.calculatedWord();
        System.out.println(result.get("greek"));
        System.out.println(result.size());
    }

    public static List<Employee> findDuplicates(List<Employee> list) {
        Set<Employee> seen = new HashSet<>();
        Set<Employee> duplicates = new LinkedHashSet<>();

        for (Employee e : list) {
            if (!seen.add(e)) {
                duplicates.add(e);
            }
        }
        return new LinkedList<>(duplicates);
    }

    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Employee> uniqueMap = new HashMap<>();

        for (Employee e : list) {
            if (e == null) continue;
            countMap.put(e.getId(), countMap.getOrDefault(e.getId(), 0) + 1);
            if (!uniqueMap.containsKey(e.getId())) {
                uniqueMap.put(e.getId(), e);
            }
        }

        Map<Integer, Employee> result = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            result.put(entry.getKey(), uniqueMap.get(entry.getKey()));
        }

        return result;
    }

    public static List<Employee> removeDuplicates(List<Employee> list) {
        Map<Employee, Integer> countMap = new HashMap<>();

        for (Employee e : list) {
            if (e != null) {
                countMap.put(e, countMap.getOrDefault(e, 0) + 1);
            }
        }

        LinkedList<Employee> result = new LinkedList<>();
        for (Map.Entry<Employee, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}