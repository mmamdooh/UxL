package uxlObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class homePage {

	WebDriver homePageDriver;
	WebDriverWait wait;

	@FindBy(how = How.ID, using = "searchDropdownBox")
	WebElement btn_CategoryList;

	@FindBy(id = "nav-logo-sprites")
	WebElement lnk_Home;
	@FindBy(id = "twotabsearchtextbox")
	WebElement txt_KeywordSearch;

	@FindBy(id = "nav-search-submit-button")
	WebElement btn_SearchIcon;
	
	@FindBy(xpath = "//span[contains(text(), 'ترتيب حسب:')]")
	WebElement lst_Sort;
	@FindBy(xpath = "//a[@id = 's-result-sort-select_1']")//[text()='السعر: من الأدنى إلى الأعلى']")
	WebElement itm_SortPriceAsc;
	@FindBy(xpath = "//a[text()='المتوسط تقييمات المستخدمين']")
	WebElement itm_SortReviewRank;

	@FindBy(id = "nav-cart-count")
	WebElement icn_NavCart;
	
	

	public homePage(WebDriver constructureDriver) {
		this.homePageDriver = constructureDriver;
		PageFactory.initElements(homePageDriver, this);
		wait = new WebDriverWait(homePageDriver, Duration.ofSeconds(5));
	}

	public void chooseCategory(String category) throws Throwable    {		
		HighlightElement(btn_CategoryList);
		btn_CategoryList.click();
		Thread.sleep(1000);
		category = "//select[@id = 'searchDropdownBox']//following::option[text() = '" + category + "']";
		By lst_CategoryItem = By.xpath(category);
		wait.until(ExpectedConditions.elementToBeClickable(lst_CategoryItem)).click();
		}
	
	public void searchForCategory() {
		HighlightElement(btn_SearchIcon);
		btn_SearchIcon.click();
	}
	
	public String listText()
	{
		String value = btn_CategoryList.getAttribute("value");
		//System.out.println("list text = " + value);
		return value;
	}
	
	
	public void searchByKeyWord(String keyWord) throws Exception {
		lnk_Home.click();
		HighlightElement(txt_KeywordSearch);
		txt_KeywordSearch.sendKeys(keyWord);
		Thread.sleep(1000);
		HighlightElement(btn_SearchIcon);
		btn_SearchIcon.click();	
	}
	
	public void sortByPriceAsc() throws Exception {
		Thread.sleep(1000);
		HighlightElement(lst_Sort);
		lst_Sort.click();
		wait.until(ExpectedConditions.elementToBeClickable(itm_SortPriceAsc));
		itm_SortPriceAsc.click();
	}
	
	public void sortByreviewRank() throws Exception {
		HighlightElement(lst_Sort);
		lst_Sort.click();
		Thread.sleep(1000);
		itm_SortReviewRank.click();
		Thread.sleep(3000);
	}
	
	public void addItemToCart() throws Throwable {
		Thread.sleep(2000);
		List<WebElement> btn_AddToCart =  homePageDriver.findElements(By.xpath("//button[text()='إضافة إلى عربة التسوق']"));
		System.out.println("Size is : "+ btn_AddToCart.size() + "text: " + btn_AddToCart.get(0).getText());
		wait.until(ExpectedConditions.elementToBeClickable(btn_AddToCart.get(0)));
		ScrollToElement(btn_AddToCart.get(0));
		btn_AddToCart.get(0).click();
	}
	
	public int cartItemsCount() throws Throwable {
		Thread.sleep(2000);
		int cartCount = Integer.parseInt(icn_NavCart.getText());
		return cartCount;
	}
	
	public void navigateCartItems() {
		
		icn_NavCart.click();
	}
	
	public void HighlightElement(WebElement element) {
		JavascriptExecutor JSE = (JavascriptExecutor) homePageDriver;
		JSE.executeScript("arguments[0].setAttribute('style', 'border: 4px solid red;')", element);
	}
	
	public void ScrollToElement(WebElement Element)
	{
		JavascriptExecutor JSE = (JavascriptExecutor) homePageDriver;
		JSE.executeScript("arguments[0].scrollIntoView();", Element);
	}

}