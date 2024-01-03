package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.devtools.v115.indexeddb.model.Key;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryOrderTest {
    private String dateIndication(int amountDays, String pattern) {
        return LocalDate.now().plusDays(amountDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void entryOfTheOrder() {
        open("http://localhost:9999");

        //$("[data-test-id='city'] input").setValue("Екатеринбург");
        $("[placeholder='Город']").setValue("Екатеринбург");
        String planDate = dateIndication(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planDate);
        //$("[data-test-id='name'] input").setValue("Петров Иван");
        $("[name='name']").setValue("Петров Иван");
        //$("[data-test-id='phone'] input").setValue("+79123456789");
        $("[name='phone']").setValue("+79123456789");
        //$("[data-test-id='agreement']").click();
        $(".checkbox__box").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planDate));

    }
}
