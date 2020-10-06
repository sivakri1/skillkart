package com.defaultframework.Maven.testscripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.defaultframework.Maven.generic.ExcelLibrary;
import com.defaultframework.Maven.generic.Utilities;
import com.defaultframework.Maven.pages.CategoryPage;
import com.defaultframework.Maven.pages.HomePage;
import com.defaultframework.Maven.pages.OrderDetailPage;
import com.defaultframework.Maven.pages.ProductDetailPage;
public class TC007 extends BaseTest
{
	@Test(description="verify the unit price and total price in ODP")
	public void testProductPriceInODP()
	{
		String sheetName="TC007";
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productID = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		
		HomePage hp = new HomePage(driver, webActionUtil);
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.clickOnProduct(productID);
		String PDPUnitPrice = pdp.getProductUnitPrice(); 
		pdp.selectSize(size);
		pdp.selectColor(color);
		pdp.clickOnAddToKart();
		OrderDetailPage odpPage = pdp.clickOnProccedToCheckout();
		/*Assert.assertEquals(odpPage.verifyUnitPrice("3","$5"),
					true, "Actual Price Displayed::"+odpPage.getODPUnitPrice("3"));*/
		Assert.assertEquals(odpPage.getODPUnitPrice(productID), PDPUnitPrice);
	}
}
