package ru.netology.web.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Name("Дашбоард")
public class DashboardPage extends AkitaPage {
    @FindBy(css = "[data-test-id=dashboard]")
    private SelenideElement heading;

    @FindBy(css = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button")
    @Name("Пополнить карту 1")
    private SelenideElement transferButton1;
    @FindBy(css = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button")
    @Name("Пополнить карту 2")
    private SelenideElement transferButton2;

    @FindBy(css = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']")
    @Name("1")
    private SelenideElement balance1;
    @FindBy(css = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']")
    @Name("2")
    private SelenideElement balance2;


    public SelenideElement getBalance1() {
        return balance1;
    }

    public SelenideElement getBalance2() {
        return balance2;
    }

    public String getBalance(SelenideElement account) {
        String tmp = account.getOwnText();
        int index;
        for (int i = 0; i < 2; i++) {
            index = tmp.indexOf('\n');
            tmp = tmp.substring(index + 1);
        }
        index = tmp.indexOf('\n');
        tmp = tmp.substring(0, index);
        return tmp;
    }

    public TransferOwnPage depositTo(int cardNumber) {
        if (cardNumber == 1){
            transferButton1.click();
        }
        if (cardNumber == 2){
            transferButton2.click();
        }
        return Selenide.page(TransferOwnPage.class);
    }
}
