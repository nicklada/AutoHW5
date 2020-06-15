package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.Data;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
public class DataGenerator {
    DataGenerator() {
    }

    public static String getCity() {
        Random rand = new Random();
        List<String> list = Arrays.asList("Москва", "Санкт-Петербург", "Казань", "Воронеж", "Волгоград", "Екатеринбург", "Саратов", "Новосибирск", "Владикавказ", "Владивосток", "Ярославль", "Краснодар");
        int randomIndex = rand.nextInt(list.size());
        String randomElement = list.get(randomIndex);
        return randomElement;
    }

    public static String getRelevantDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(5);
        date.format(formatter);
        return date.format(formatter);
    }

    public static String getOtherRelevantDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(5);
        date.format(formatter);
        return date.format(formatter);
    }

    public static String getIrrelevantDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        today.format(formatter);
        return today.format(formatter);
    }


    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getPhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }
}



