package com.autohero.ui.pages;

import com.autohero.ui.enums.CarsSortingTypes;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage extends BasePage{

    public static final String PAGE_PATH = "/search";

    private SelenideElement firstRegistrationFromLink = $("div[data-qa-selector=filter-year] span");
    private ElementsCollection registrationDates = $$("ul[data-qa-selector=spec-list] li:nth-child(1)");
    private SelenideElement registrationYearsSelectBox = $(By.name("yearRange.min"));
    private SelenideElement sortBySelectBox = $(By.name("sort"));
    private ElementsCollection carPrices = $$("div[data-qa-selector=price]");


    @Step
    public static SearchPage open(){
        return Selenide.open(PAGE_PATH, SearchPage.class);
    }

    @Step
    public SearchPage filterCarsByFirstRegistrationFrom(String yearFrom){
        clickFirstRegistrationFromLink();
        openRegistrationYearSelectBox();
        clickRegistrationFromYearValue(yearFrom);
        return this;
    }

    @Step
    public int getMinimalRegistrationYearDisplayed(){
        Set<Integer> uniqueYears = registrationDates
                .stream()
                .map(x -> Integer.valueOf(x.text().split("/")[1]))
                .collect(Collectors.toSet());
        return Collections.min(uniqueYears);
    }

    @Step
    public SearchPage sortCarsByType(CarsSortingTypes sortingType){
        openSortBySelectBox();
        selectSortingTypeByText(sortingType.getSortingType());
        sleepForSeconds(1);
        return this;
    }

    @Step
    public List<Float> getCarsPrices(){
        return carPrices
                .stream()
                .map(x -> Float.valueOf(x.text().replaceAll("\\D+", "")))
                .collect(Collectors.toList());
    }

    //private methods
    private SearchPage clickFirstRegistrationFromLink(){
        firstRegistrationFromLink.click();
        return this;
    }

    private SearchPage openRegistrationYearSelectBox(){
        registrationYearsSelectBox.click();
        return this;
    }

    private SearchPage clickRegistrationFromYearValue(String yearFrom){
        registrationYearsSelectBox.selectOption(yearFrom);
        return this;
    }

    private SearchPage openSortBySelectBox(){
        sortBySelectBox.shouldBe(Condition.visible).click();
        return this;
    }

    private SearchPage selectSortingTypeByText(String sortingType){
        sortBySelectBox.shouldBe(Condition.visible).selectOption(sortingType);
        return this;
    }
}
