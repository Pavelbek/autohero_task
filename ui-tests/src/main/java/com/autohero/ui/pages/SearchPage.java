package com.autohero.ui.pages;

import com.autohero.ui.enums.CarsSortingTypes;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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


    public static SearchPage open(){
        return Selenide.open(PAGE_PATH, SearchPage.class);
    }

    public SearchPage filterCarsByFirstRegistrationFrom(String yearFrom){
        clickFirstRegistrationFromLink().openRegistrationYearSelectBox();
        clickRegistrationFromYearValue(yearFrom);
        return this;
    }

    public SearchPage clickFirstRegistrationFromLink(){
        firstRegistrationFromLink.click();
        return this;
    }

    public SearchPage openRegistrationYearSelectBox(){
        registrationYearsSelectBox.click();
        return this;
    }

    public SearchPage clickRegistrationFromYearValue(String yearFrom){
        registrationYearsSelectBox.selectOption(yearFrom);
        return this;
    }

    public int getMinimalRegistrationYearDisplayed(){
        Set<Integer> uniqueYears = registrationDates
                .stream()
                .map(x -> Integer.valueOf(x.text().split("/")[1]))
                .collect(Collectors.toSet());
        return Collections.min(uniqueYears);
    }

    public SearchPage openSortBySelectBox(){
        sortBySelectBox.shouldBe(Condition.visible).click();
        return this;
    }

    public SearchPage selectSortingTypeByText(String sortingType){
        sortBySelectBox.shouldBe(Condition.visible).selectOption(sortingType);
        return this;
    }

    public SearchPage sortCarsByType(CarsSortingTypes sortingType){
        openSortBySelectBox();
        selectSortingTypeByText(sortingType.getSortingType());
        Selenide.sleep(1000);
        return this;
    }

    public List<Float> getCarPrices(){
        return carPrices
                .stream()
                .map(x -> Float.valueOf(x.text().replaceAll("\\D+", "")))
                .collect(Collectors.toList());
    }
}
