package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class TestCaseResultPage {
    public SelenideElement
            idCheckout = $(".TestCaseLayout__id"),
            stepsCheckout = $(".TestCaseStepRow"),
            preconditionCheckout = $(".MarkdownArticle > p"),
            expectedResultCheckout = $(".TestCaseOverview__primary > div:nth-of-type(3) > div > section > div > div > p");
    public TestCaseResultPage openPage() {
        step("Проверка id", () -> {
            idCheckout.shouldBe(Condition.visible);
        });
        return this;
    }
    public TestCaseResultPage stepsCheckout() {
        step("Проверка степов", () -> {
            stepsCheckout.shouldBe(Condition.visible);
        });
        return this;
    }
    public TestCaseResultPage preconditionCheckout() {
        step("Проверка предусловий", () -> {
            preconditionCheckout.shouldBe(Condition.visible);
        });
        return this;
    }
    public TestCaseResultPage expectedResultCheckout() {
        step("Проверка ожидаемого результата", () -> {
            expectedResultCheckout.shouldBe(Condition.visible);
        });
        return this;
    }
}
