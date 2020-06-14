package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//@Data
public class DataGenerator {
    private DataGenerator(){}

    @Value
    public static class City {
        private String city;

        public static City getCity() {
            Random rand = new Random();
            List<String> list = Arrays.asList("Москва","Санкт-Петербург","Казань","Воронеж","Волгоград","Екатеринбург","Саратов","Новосибирск","Владикавказ","Владивосток","Ярославль","Краснодар");
            int randomIndex = rand.nextInt(list.size());
            String randomElement = list.get(randomIndex);
            return new City(randomElement);
        }
    }

    @Value
    public static class Date {
        private String date;

        public static Date getRelevantDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate today = LocalDate.now();
            LocalDate date = today.plusDays(5);
            date.format(formatter);
            return new Date(date.format(formatter));
        }

        public static Date getOtherRelevantDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate today = LocalDate.now();
            LocalDate date = today.plusDays(7);
            date.format(formatter);
            return new Date(date.format(formatter));
        }

        public static Date getIrrelevantDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate today = LocalDate.now();
            today.format(formatter);
            return new Date(today.format(formatter));
        }
    }

    @Value
    public static class Name {
        private String name;

        public static Name getName() {
            Faker faker = new Faker(new Locale("ru"));
            return new Name(faker.name().fullName());
        }
    }


    @Value
    public static class Phone {
        private String phone;

        public static Phone getPhone() {
            Faker faker = new Faker(new Locale("ru"));
            return new Phone(faker.phoneNumber().phoneNumber());
        }
    }

}

