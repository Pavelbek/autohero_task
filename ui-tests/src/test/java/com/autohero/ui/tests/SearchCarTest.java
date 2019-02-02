package com.autohero.ui.tests;

import com.autohero.ui.enums.CarsSortingTypes;
import com.autohero.ui.pages.SearchPage;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.testng.Assert.assertTrue;

public class SearchCarTest extends BaseTest {

    @Test
    public void filterByYearAndDescendantSortingWorksCorrectlyTest(){
        //given
        String yearToSelect = "2015";
        SearchPage.open();

        //when
        at(SearchPage.class)
                .filterCarsByFirstRegistrationFrom(yearToSelect)
                .sortCarsByType(CarsSortingTypes.HOCHSTER_PREIS);

        //then
        int actualMinimalYear = at(SearchPage.class).getMinimalRegistrationYearDisplayed();
        List<Float> carsPricesAfterSorting = at(SearchPage.class).getCarPrices();

        assertMinimalRegistrationYearDisplayedIsCorrect(actualMinimalYear, Integer.parseInt(yearToSelect));
        assertPricesAreSortedInDescOrder(carsPricesAfterSorting);
    }

    private void assertMinimalRegistrationYearDisplayedIsCorrect(int actualMinimalYear, int expectedMinimalYear){
        assertThat("Actual: " + actualMinimalYear +  ", Expected: " + expectedMinimalYear, actualMinimalYear, greaterThanOrEqualTo(expectedMinimalYear));

    }

    private void assertPricesAreSortedInDescOrder(List<Float> elementsToCheck){
        assertTrue(elementsToCheck.equals(elementsToCheck.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())), "Prices are not sorted in descendant order.");
    }
}
