package uxlTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uxlObjects.checkOutObject;
import uxlObjects.homePage;

public class testCases extends testBase {

	homePage hpage;
	checkOutObject chkPgae;

	@Parameters({ "URL" })
	@Test(priority = 1, description = "Verify Amazon home page")
	public void TC_001(String URL) {
		//1- Open browser
		//2- Type web site URL and press enter
		uxlDriver.get(URL);
		assertTrue(uxlDriver.getTitle().contains("أمازون مصر: تسوق أونلاين | أسعار مخفضة"),
				"[Info] -- TC_001: Amazon home page not loaded successfully");
	}

	@Parameters({ "Category" })
	@Test(priority = 2, description = "Verify search functionality by Category", dependsOnMethods = "TC_001")
	public void TC_002(String Category) throws Throwable {
		hpage = new homePage(uxlDriver);
		//1- From home page clicks category list
		//2- Select a categort to search with (eg. الإلكترونيات)
		hpage.chooseCategory(Category);
		//3- Clicks Search icon to show result
		hpage.searchForCategory();
		assertTrue(hpage.listText().contains("electronic"),
				"[Info] -- TC_002: Failed to select correct category");
	}

	@Parameters({ "Keyword" })
	@Test(priority = 3, description = "Verify search functionality by Category", dependsOnMethods = "TC_001")
	public void TC_003(String Keyword) throws Exception {
		hpage = new homePage(uxlDriver);
		hpage.searchByKeyWord(Keyword);
		assertTrue(uxlDriver.getCurrentUrl().contains(Keyword.replace(' ', '+')),
				"[Info] -- TC_003: Failed to search by keyword");
	}

	@Test(priority = 4, description = "Verify the sorting", dependsOnMethods = "TC_001")
	public void TC_004() throws Exception {
		hpage = new homePage(uxlDriver);
		hpage.sortByPriceAsc();
		assertTrue(uxlDriver.getCurrentUrl().contains("sr_st_price-asc-rank"), "[Info] -- TC_004: Failed to order by price asc.");
		
		hpage.sortByreviewRank();
		assertTrue(uxlDriver.getCurrentUrl().contains("sr_st_review-rank"), "[Info] -- TC_004: Failed to order by review rank.");
	}

	@Test(priority = 5, description = "Verify selling an item from e-commerce portal", dependsOnMethods = "TC_003")
	public void TC_005() throws Throwable {
		hpage = new homePage(uxlDriver);
		hpage.addItemToCart();
		assertEquals(hpage.cartItemsCount(), 1, "[Info] -- TC_005: Failed to add item to cart.");
	}

	@Test(priority = 6, description = "Verify selling an item from e-commerce portal", dependsOnMethods = "TC_003")
	public void TC_006() {
		hpage = new homePage(uxlDriver);
		hpage.navigateCartItems();
		
		chkPgae = new checkOutObject(uxlDriver);
		chkPgae.proceedToPayment();
	}

	@Test(priority = 7)
	public void TC_007() {
	}

}
