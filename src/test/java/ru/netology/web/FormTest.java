package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;

public class FormTest {
    private Faker faker;
    DataGenerator dataGenerator;
    private DataGenerator.City city;
    private DataGenerator.Date time;
    private DataGenerator.Name name;
    private DataGenerator.Phone phone;


    private LocalDate today = LocalDate.now();
    private LocalDate date = today.plusDays(5);
    private LocalDate newDate = today.plusDays(10);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String todayString = today.format(formatter);
    private String dateString = date.format(formatter);
    private String newDateString = newDate.format(formatter);

    @BeforeEach
    void setUpAll(){faker = new Faker(new Locale("ru"));}


        @Test
        void shouldSubmitRequest() {
            open("http://localhost:9999");
            SelenideElement form = $("[action]");
            form.$(cssSelector("[data-test-id=city] input")).sendKeys(city.getCity());
            form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(cssSelector("[data-test-id=date] input")).sendKeys(time.getRelevantDate());
            form.$(cssSelector("[name=name]")).sendKeys(name.getName());
            form.$(cssSelector("[name=phone]")).sendKeys(phone.getPhone());
            form.$(cssSelector("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $(cssSelector(".notification__title")).waitUntil(Condition.visible, 15000);
        }

        @Test
        void shouldGiveNewDateWhenSameRequest() {
            open("http://localhost:9999");
            SelenideElement form = $("[action]");
            form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
            form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(cssSelector("[data-test-id=date] input")).sendKeys(dateString);
            form.$(cssSelector("[name=name]")).sendKeys("Николай Иванов");
            form.$(cssSelector("[name=phone]")).sendKeys("+79111235678");
            form.$(cssSelector("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
            open("http://localhost:9999");
            form.$(cssSelector("[data-test-id=city] input")).sendKeys("Москва");
            form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(cssSelector("[data-test-id=date] input")).sendKeys(newDateString);
            form.$(cssSelector("[name=name]")).sendKeys("Николай Иванов");
            form.$(cssSelector("[name=phone]")).sendKeys("+79111235678");
            form.$(cssSelector("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $(cssSelector(".notification_status_error .button")).click();
            $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
        }

    }