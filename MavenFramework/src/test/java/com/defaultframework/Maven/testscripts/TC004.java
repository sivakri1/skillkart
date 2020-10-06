package com.defaultframework.Maven.testscripts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.defaultframework.Maven.generic.ExcelLibrary;
import com.defaultframework.Maven.generic.Utilities;
import com.defaultframework.Maven.pages.CategoryPage;
import com.defaultframework.Maven.pages.HomePage;
import com.defaultframework.Maven.pages.OrderDetailPage;
import com.defaultframework.Maven.pages.ProductDetailPage;
public class TC004 extends BaseTest
{
	@DataProvider
	public Object[][] getData()
	{
		return ExcelLibrary.getAllExcelSheetData(XL_PATH, "TC004");
	}
	
	@Test(dataProvider="getData", description="Test With Multiple Sets Of Data")
	public void testMultipleItemAddedToKartUsingExcel(String menuItem,
													  String productID,
													  String quantity,
													  String size,
													  String color)
	{
		HomePage hp = new HomePage(driver, webActionUtil);
				
		productID = Utilities.getIntText(productID);
		int quant=Utilities.returnInteger(quantity);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.OpenProductInQuickView(productID);
		Assert.assertNotEquals(pdp, null);
		pdp.addItemToKart(quant, size, color);
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productID), true);
	}
}
