package com.hazelcast.pages.mancenter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.hazelcast.pages.BasePage;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;

@Component
public class ManageClustersPage extends BasePage {

    private SelenideElement getSelectClusterButton(String clusterName) {
        return $x(String.format("//a[@href='/%s']", clusterName));
    }

    @Step("Select the cluster by name")
    public void selectClusterByName(String clusterName) {
        getSelectClusterButton(clusterName).shouldBe(Condition.visible).click();
    }
}
