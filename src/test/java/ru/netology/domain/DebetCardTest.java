package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebetCardTest {


    @Test
    void shouldSendRequest () {

        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
}
