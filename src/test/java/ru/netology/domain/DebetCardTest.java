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

    @Test
    void shouldGetInvalidInputWrongName () {
        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input ").setValue("James Bond");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".input_invalid .input__sub").
            shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetInvalidInputNoName () {
        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input ").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".input_invalid .input__sub").
                shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetInvalidInputWrongPhone () {
        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("89110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".input_invalid .input__sub").
                shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetInvalidInputNoPhone () {
        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".input_invalid .input__sub").
                shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetInvalidInputNoAgreement () {
        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[role=button]").click();
        $(".input_invalid").
                shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldGetInvalidInputIfEmptyInput () {
        open("http://localhost:9999");
        SelenideElement form = $("[action='/']");
        form.$("[role=button]").click();
        $(".input_invalid .input__sub").
                shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

}
