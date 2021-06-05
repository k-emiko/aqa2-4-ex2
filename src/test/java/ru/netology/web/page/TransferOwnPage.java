package ru.netology.web.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.annotations.Optional;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

@Name("Перевод между своими счетами")
public class TransferOwnPage extends AkitaPage {
    @FindBy(css = "[data-test-id='amount'] .input__control")
    @Name("Сумма")
    private SelenideElement amountField;
    @FindBy(css = "[data-test-id='from'] .input__control")
    @Name("Счет списания")
    private SelenideElement fromField;
    @FindBy(css = "[data-test-id='action-transfer'].button")
    @Name("Пополнить")
    private SelenideElement transferButton;
    @Optional
    @FindBy(css = ".notification")
    @Name("Уведомление")
    private SelenideElement notification;

    public DashboardPage depositToCard(String sum, String account){
        amountField.setValue(String.valueOf(sum));
        fromField.setValue(account);
        transferButton.click();
        return Selenide.page(DashboardPage.class);
    }

    public void depositFromEmpty(int sum){
        amountField.setValue(String.valueOf(sum));
        transferButton.click();
    }

    public void depositFromInvalid(int sum){
        amountField.setValue(String.valueOf(sum));
        fromField.setValue("0000 0000 0000 0000");
        transferButton.click();
    }

    public void assertNotificationVisibility(){
        notification.shouldBe(visible).shouldHave(text("Ошибка"));
    }

}
