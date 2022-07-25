package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebetCardTest {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }


    @Test
    void shouldSendRequest() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! "
                + "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestIfSurnameWithDash() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Игорь Перейра-Родригес");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! "
                + "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestIfNameWithDash() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Ана-Мария Огбезян");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! "
                + "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldGetInvalidInputWrongName() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input ").setValue("James Bond");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия "
                + "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetInvalidInputSymbolsInsteadOfName() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input ").setValue("$#@%^&*()(@!!");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия "
                + "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetInvalidInputIfNameInLatin() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input ").setValue("Aleksei Pakin");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия "
                + "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetInvalidInputNoName() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input ").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле "
                + "обязательно для заполнения"));
    }

    @Test
    void shouldGetInvalidInputWrongPhone() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("89110070707");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан "
                + "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetInvalidInputSymbolsInsteadOfPhone() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("()()*&^%#@$!~~><");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан "
                + "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetInvalidInputNoPhone() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле "
                + "обязательно для заполнения"));
    }

    @Test
    void shouldGetInvalidInputNoAgreement() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id=name] input").setValue("Джеймс Бонд");
        form.$("[data-test-id=phone] input").setValue("+79110070707");
        form.$("[role=button]").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(Condition.exactText("Я соглашаюсь "
                + "с  условиями обработки и использования моих персональных данных и разрешаю сделать запрос в "
                + "бюро кредитных историй"));
    }

    @Test
    void shouldGetInvalidInputIfEmptyInput() {
        SelenideElement form = $("[action='/']");
        form.$("[role=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле "
                + "обязательно для заполнения"));
    }
}
