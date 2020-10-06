package com.defaultframework.Maven.testscripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.defaultframework.Maven.generic.ExcelLibrary;
import com.defaultframework.Maven.generic.Utilities;
import com.defaultframework.Maven.pages.CategoryPage;
import com.defaultframework.Maven.pages.HomePage;
import com.defaultframework.Maven.pages.OrderDetailPage;
import com.defaultframework.Maven.pages.ProductDetailPage;
//No QuickView Used with Excel
public class TC002 extends BaseTest
{
	@Test(description="Verify Whether the Added Product From Quick View is displayed in ODP")
	public void testItemAddedToKart()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		String sheetName = "TC002";
		
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productID = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		int quantity=Utilities.returnInteger(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 4);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.clickOnProduct(productID);
		pdp.increaseQuantity(quantity);
		pdp.selectSize(size);
		pdp.selectColor(color);
		pdp.clickOnAddToKart();
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productID), true);
	}
}
