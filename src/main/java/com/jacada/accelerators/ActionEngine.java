/** Purpose: This class is the group of Selenium accelerators. 
* It contains all the wrapper functions for every event in the fluxx-Grant Approval Workflow 
* Author: Satyadeep Behera
*/

package com.jacada.accelerators;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.jacada.support.ReportStampSupport;

/**
 *  ActionEngine is a wrapper class of Selenium actions
 */
@SuppressWarnings("all")
public class ActionEngine extends TestEngine {
	public WebDriverWait wait;

	/**
	 * @param locator 
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */

	public boolean click_actual(By locator, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).click();
		
			Thread.sleep(3000);
			flag = true; 
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				SuccessReport("Click", "Click action performed on  "+stepDescription);
			} else {
				failureReport("Click", "Unable to Click on  "+stepDescription);
				count = count+1;

			}
		}
		return flag;
	}

	
	/**
	 * @param locator 
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */

	public boolean click(By locator, String clickStep ,String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).click();
			Thread.sleep(3000);
			flag = true; 
		} catch (Exception e) {
			e.printStackTrace();
			if(isReq)
				throw new Exception("Perform "+clickStep+"~Unable to perform "+clickStep);
			else
				return false;
		} 
		return flag;
	}
	
	
	public boolean click_validate(By locator, String clickStep ,String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).click();
			Thread.sleep(3000);
			flag = true; 
			SuccessReport(clickStep, "'"+clickStep+"' performed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			if(isReq)
				throw new Exception("Perform "+clickStep+"~Unable to perform '"+clickStep+"'");
			else
				return false;
		} 
		return flag;
	}
	
	public boolean btn_click(By locator, String clickStep ,String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).click();
			Thread.sleep(1000);
			flag = true; 
		} catch (Exception e) {
			e.printStackTrace();
			if(isReq)
				throw new Exception("Perform "+clickStep+"~Unable to perform '"+clickStep+"'");
			else
				return false;
		}
		return flag;
	}

	/**
	 * This method returns check existence of element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Textbox, checkbox etc)
	 * @return: Boolean value(True or False)
	 * @throws NoSuchElementException
	 */
	public boolean isElementPresent(By by, String stepDescription)
			throws Throwable {
		try {
			driver.findElement(by).isDisplayed();
			flag = true;
			return true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} 
	}
	
	public boolean isElementDisplayed(By by, String stepDescription)
			throws Throwable {
		//boolean flag = false;
		try {
			driver.findElement(by);
			flag = true;
			return true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} 
	}

	public boolean isPopUpElementPresent(By by, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			if (driver.findElement(by).isDisplayed())
				flag = true;
			else
				flag = false;
			return flag;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (flag) {
				SuccessReport("IsElementPresent ", stepDescription);
			} else {
				failureReport("IsElementPresent", stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public boolean type_actual (By locator, String testdata, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			Thread.sleep(3000);
			driver.findElement(locator).sendKeys(testdata);
			Thread.sleep(2000);
			flag = true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (flag) {
				SuccessReport("Enter ", stepDescription+" is Entered");
			} else{
				failureReport("Enter ", stepDescription+" is not Entered");
				count = count+1;
			}
		}
		return flag;
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public boolean type (By locator, String testdata, String fieldName,String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			//Thread.sleep(3000);
			driver.findElement(locator).sendKeys(testdata);
			Thread.sleep(1000);
			flag = true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			if(isReq)
				throw new Exception("Type the input in "+fieldName+" field~Unable to type the input in "+fieldName+" field");
			else
				return false;
		} 
		return flag;
	}
	
	
	public boolean type_input (By locator, String testdata, String fieldName,String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			//Thread.sleep(1000);
			driver.findElement(locator).sendKeys(testdata);
			//Thread.sleep(1000);
			flag = true;
			SuccessReport("Enter "+fieldName, fieldName+" successfully entered as :'"+testdata+"'");
		} catch (Exception e) {

			System.out.println(e.getMessage());
			if(isReq)
				throw new Exception("Type the input in "+fieldName+" field~Unable to type the input in "+fieldName+" field");
			else
				return false;
		} 
		return flag;
	}
	
	
	public boolean type_input1 (By locator, String testdata, String fieldName,String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			//Thread.sleep(1000);
			driver.findElement(locator).sendKeys(testdata);
			Thread.sleep(1000);
			flag = true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			if(isReq)
				throw new Exception("Type the input in "+fieldName+" field~Unable to type the input in "+fieldName+" field");
			else
				return false;
		} 
		return flag;
	}
	
	
	public boolean typeAction(By locator, String text,String fieldName , boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			//new Actions(driver).moveToElement(mo).build().perform();
			new Actions(driver).moveToElement(mo).sendKeys(text).build().perform();
			flag = true;
			SuccessReport("Enter "+fieldName, fieldName+" successfully entered as :'"+text+"'");
			return true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			//return false;
			if(isReq)
				throw new Exception("Type the input in "+fieldName+" field~Unable to type the input in "+fieldName+" field");
			else
				return false;
		}  
	}
	
	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:link,menus etc..)
	 * 
	 */
	public boolean mouseover(By locator, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			new Actions(driver).moveToElement(mo).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Perform "+stepDescription+"~Unable to perform "+stepDescription);
			else
				return false;
		} 
	}
	
	
	public boolean mouseScroll(String by, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,1000)");
			flag = true;
			return true;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Scroll down~Unable to scroll down");
			else
				return false;
		} 
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves by a given offset, then releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param xOffset
	 *            : Horizontal move offset.
	 * 
	 * @param yOffset
	 *            : Vertical move offset.
	 * 
	 */
	public boolean draggable(By source, int x, int y, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {

			WebElement dragitem = driver.findElement(source);
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("Draggable ",stepDescription);			
			} else {
				failureReport("Draggable ",stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves to the location of the target element, then
	 * releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param target
	 *            : Element to move to and release the mouse at.
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Button,image etc..)
	 * 
	 */
	public boolean draganddrop(By source, By target, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = driver.findElement(source);
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("DragAndDrop ",stepDescription);
			} else {
				failureReport("DragAndDrop ",stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * To slide an object to some distance
	 * 
	 * @param slider
	 *            : Action to be performed on element
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public boolean slider(By slider, int x, int y, String stepDescription)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement dragitem = driver.findElement(slider);
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("Slider ", stepDescription);
			} else {
				failureReport("Slider ", stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * To right click on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public boolean rightclick(By by, String stepDescription)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToRightClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.contextClick(elementToRightClick).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("RightClick ",stepDescription);
			} else {
				failureReport("RightClick ",stepDescription);
				count = count+1;
			}
		}
	}
	
	
	/**
	 * To scroll on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public boolean mouseScroll(By by)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToScroll = driver.findElement(by);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",elementToScroll);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} 
	}
	
	/**
	 * To double click on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public boolean doubleclick(By by, String stepDescription, String clickStep, boolean isReq)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToDoubleClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.doubleClick(elementToDoubleClick).perform();
			flag = true;
			SuccessReport(clickStep, "'"+clickStep+"' performed successfully");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(isReq)
				throw new Exception("Perform "+clickStep+"~Unable to perform '"+clickStep+"'");
			else
				return false;
		} 
	}


	/**
	 * Wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */

	public boolean waitForTitlePresent(By locator) throws Throwable {

		boolean flag = false;
		boolean bValue = false;

		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					
					driver.wait(50);
				
				}
			} 
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				SuccessReport("WaitForTitlePresent ","Launched successfully expected Title");
			} else {
				failureReport("WaitForTitlePresent ", "Title is wrong");
				count = count+1;
			}
		}
		return bValue;
	}

	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return Whether or not the element is displayed
	 */

	public boolean waitForElementPresent(By by, String locator, boolean isReq)
			throws Throwable {
		try {
			//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 60);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {             
			// TODO: handle exception
			if(isReq)
				throw new Exception("Verify the existence of \""+locator+"\"~Falied to locate the element \""+locator+"\"");
			else
				return false;
		} 

	}


	
	/**
	 * This method Click on element and wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param waitElement
	 *            : Element name wish to wait for that (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 */
	public boolean clickAndWaitForElementPresent(By locator,
			By waitElement, String stepDescription) throws Throwable {
		boolean flag = false;
		try {
			//click(locator, stepDescription);
			waitForElementPresent(waitElement, stepDescription, true);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag){
				SuccessReport("ClickAndWaitForElementPresent ",stepDescription);
			} else {
				failureReport("ClickAndWaitForElementPresent ",stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * Select a value from Drop down using send keys
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish type in drop down list
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Year Drop down, items
	 *            List box etc..)
	 * 
	 */
	public boolean selectBySendkeys(By locator, String value,
			String stepDescription) throws Throwable {

		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
		//	driver.findElement(By.xpath("")).(Keys.ENTER);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("Select ",stepDescription);		
			} else {
				failureReport("Select ",stepDescription);
				count = count+1;
				
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param index
	 *            : Index of value wish to select from dropdown list.
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public boolean selectByIndex(By locator, int index,
			String stepDescription) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Select ", stepDescription);
			} else {
				failureReport("Select ", stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish to select from dropdown list.
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public boolean selectByValue(By locator, String value,
			String stepDescription) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("Select",stepDescription);
			} else {
				failureReport("Select",stepDescription);
				count = count+1;
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public boolean selectByVisibleText(By locator, String visibletext,
			String stepDescription, boolean isReq) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByVisibleText(visibletext);
			flag = true;
			SuccessReport("Select "+stepDescription, stepDescription+" successfully selected as :'"+visibletext+"'");
			return true;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Select "+visibletext+"~Unable to Select "+visibletext);
			else
				return false;
		} 
	}
	
	
	
	
	
	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public boolean selectByPartialText(By locator, String visibletext,
			boolean swewFlag, String textDescription, boolean isReq) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			if(!swewFlag)
			{
				for(WebElement option : s.getOptions())
				{
					if(option.getText().toLowerCase().contains(visibletext.toLowerCase()))
					{
						s.selectByVisibleText(option.getText());
						flag = true;
						SuccessReport("Select "+textDescription, textDescription+" successfully selected as :'"+visibletext+"'");
						return true;
					}
				}
			}
			else if(visibletext.contains("::"))
			{
				String[] a = visibletext.split("[::]");
				String startsWithTxt=visibletext.split("[::]")[0].trim();
				String endsWithTxt=visibletext.split("[::]")[2].trim();
				
				
				for(WebElement option : s.getOptions())
				{
					//if(option.getText().toLowerCase().contains(startsWithTxt.toLowerCase()) && option.getText().toLowerCase().contains(endsWithTxt.toLowerCase()))
					if(option.getText().toLowerCase().contains(endsWithTxt.toLowerCase()))
					{
						s.selectByVisibleText(option.getText());
						Thread.sleep(3000);
						flag = true;
						SuccessReport("Select "+textDescription, textDescription+" successfully selected as :'"+visibletext+"'");
						return true;
					}
				}
			}
			
			else if(visibletext.contains(","))
			{
				String startsWithTxt=visibletext.split("[,]")[0].trim();
				String endsWithTxt=visibletext.split("[,]")[1].trim();
				
				
				for(WebElement option : s.getOptions())
				{
					if(option.getText().toLowerCase().contains(startsWithTxt.toLowerCase()) && option.getText().toLowerCase().contains(endsWithTxt.toLowerCase()))
					{
						s.selectByVisibleText(option.getText());
						Thread.sleep(3000);
						flag = true;
						SuccessReport("Select "+textDescription, textDescription+" successfully selected as :'"+visibletext+"'");
						return true;
					}
				}
			}
			else
			{
				for(WebElement option : s.getOptions())
				{
					if(option.getText().toLowerCase().contains(visibletext.toLowerCase()))
					{
						s.selectByVisibleText(option.getText());
						flag = true;
						SuccessReport("Select "+textDescription, textDescription+" successfully selected as :'"+visibletext+"'");
						return true;
					}
				}
			}
			
			return false;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Select "+textDescription+"~Unable to select "+textDescription);
			else
				return false;
		} finally {
			if (!flag) {
				throw new Exception("Select "+textDescription+"~Unable to select "+textDescription);		
			} 
		}
	}
	
	
	
	
	

	/**
	 * SWITCH TO WINDOW BY USING TITLE
	 * 
	 * @param windowTitle
	 *            : Title of window wish to switch
	 * 
	 * @param count
	 *            : Selenium launched Window id (integer no)
	 * 
	 * @return: Boolean value(true or false)
	 * 
	 */
	//

	public Boolean switchWindowByTitle(String windowTitle, int count)
			throws Throwable {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();


			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count-1]);

			if (driver.getTitle().contains(windowTitle)){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectWindow","Navigated to the window with title: \""+windowTitle+"\"");
			} else {
				failureReport("SelectWindow","The Window with title: \""+windowTitle+"\"is not Selected");
				count = count+1;
			}
		}
	}

	/**
	  * switchWindowByTitle1
	  * This method is to switch window by title 
	  * @param windowTitle
	  * @throws Throwable
	  */

	 public  void switchWindowByTitle1(String windowTitle) throws Throwable {
		 try
		 {

			  Set<String> windows=driver.getWindowHandles();
		
			  for (String cWindow : windows) {
		
			   driver.switchTo().window(cWindow);
			   if(driver.getTitle().equals(windowTitle))
			   {
			    System.out.println("switch to child window");
			    break;
			   }
			   else
			   {
			    System.out.println("Still in Parent window trying to switch to child window");
			   }
		
		
			  }
		 }
		 catch (Exception e) {
				throw new Exception("Launch Fluxx application~Unable to Launch fluxx");
			}
					  


	 }
	public Boolean switchToNewWindow()
			throws Throwable {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				SuccessReport("SelectWindow","Window is Navigated with title:");				
			} else {
				failureReport("SelectWindow","The Window with title: is not Selected");
				count = count+1;
			}
		}
	}


	public Boolean switchToOldWindow()
			throws Throwable {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				SuccessReport("SelectWindow","Focus navigated to the window with title:");			
			} else {
				failureReport("SelectWindow","The Window with title: is not Selected");
				count = count+1;
			}
		}
	}


	/**
	 * Function To get column count and print data in Columns
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: Returns no of columns.
	 * 
	 */
	public int getColumncount(By locator) throws Exception {

		WebElement tr = driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}

	/**
	 * Function To get row count and print data in rows
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: returns no of rows.
	 */
	public int getRowCount(By locator) throws Exception {

		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	public boolean Alert() throws Throwable {
		boolean presentFlag = false;
		Alert alert = null;

		try {

			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				SuccessReport("Alert ","The Alert is handled successfully");		
			} else{
				failureReport("Alert ", "There was no alert to handle");
				count = count+1;
			}
		}

		return presentFlag;
	}

	/**
	 * To launch URL
	 * 
	 * @param url
	 *            : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public boolean launchUrl(String url) throws Throwable {
		boolean flag = false;
		try {

			driver.navigate().to(url);
			 
            Thread.sleep(3000);
            driver.manage().window().maximize();
			if(isAlertPresent()){
				driver.switchTo().alert().accept();
			}

			ImplicitWait();
			flag = true;
			return true;
		} catch (Exception e) {
			
				throw new Exception("Launch Fluxx application~Unable to Launch fluxx");
			
		} 
	}


	public boolean isAlertPresent() 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (Exception Ex) 
		{ 
			return false; 
		}   // catch 
	}


	/**
	 * This method verify check box is checked or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * 
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 * 
	 */
	public boolean isChecked(By locator, String stepDescription)
			throws Throwable {
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (flag) {
				SuccessReport("IsChecked", stepDescription);
				// throw new ElementNotFoundException("", "", "");
			} else {
				failureReport("IsChecked",stepDescription);
				count = count+1;
			}
		}
		return bvalue;
	}

	/**
	 * Element is enable or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, UserName
	 *            Textbox etc..)
	 * 
	 * @return: boolean value (True: if the element is enabled, false: if it not
	 *          enabled).
	 * 
	 */

	public boolean isEnabled(By locator, String stepDescription)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (flag) {
				SuccessReport("IsEnabled ", stepDescription);
			} else {
				failureReport("IsEnabled ", stepDescription);
				count = count+1;
			}
		}
		return value;
	}

	/**
	 * Element visible or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @return: boolean value(True: if the element is visible, false: If element
	 *          not visible)
	 * 
	 */

	public boolean isVisible(By locator, String stepDescription)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {

			if(driver.findElement(locator).isDisplayed()){
				value = true;
				flag = true;}
		} catch (Exception e) {
			flag = false;
			value = false;

		} 
		return value;
	}

	/**
	 * Get the CSS value of an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, label color
	 *            etc..)
	 * 
	 * @param cssattribute
	 *            : CSS attribute name wish to verify the value (id, name,
	 *            etc..)
	 * 
	 * @return: String CSS value of the element
	 * 
	 */

	public String getCssValue(By locator, String cssattribute,
			String stepDescription) throws Throwable {
		String value = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, "stepDescription")) {
				value = driver.findElement(locator).getCssValue(cssattribute);
				flag = true;
			}
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("GetCssValue ",stepDescription);
			} else {
				failureReport("GetCssValue ",stepDescription);
				count = count+1;
			}
		}
		return value;
	}

	/**
	 * Check the expected value is available or not
	 * 
	 * @param expvalue
	 *            : Expected value of attribute
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name of element wish to assert
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public boolean assertValue(String expvalue, By locator,
			String attribute, String stepDescription) throws Throwable {

		boolean flag = false;
		try {

			Assert.assertEquals(expvalue, getAttribute(locator, attribute, stepDescription));
			flag = true;
		} catch (Exception e) {

		} 
		catch (AssertionError e) {

		}

		finally {
			if (flag) {
				SuccessReport("AssertValue ",stepDescription+" is present in the page");				
				return false;
			} else {
				failureReport("AssertValue ",stepDescription+" is not present in the page");
				count = count+1;
			}
		}
		return flag;
	}

	/**
	 * Check the text is presnt or not
	 * 
	 * @param text
	 *            : Text wish to assert on the page.
	 * 
	 */
	public boolean assertTextPresent(String text, String stepDescription) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertTrue(isTextPresent(text));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertTextPresent ",stepDescription);
				return false;
			} else {
				failureReport("AssertTextPresent ",stepDescription);
				count = count+1;
			}
		}
		return flag;
	}

	/**
	 * Assert element present or not
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public boolean assertElementPresent(By by, String stepDescription)
			throws Throwable {

		boolean flag = false;
		try {
			Assert.assertTrue(isElementPresent(by, stepDescription));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertElementPresent ",stepDescription);
				return false;
			} else {
				failureReport("AssertElementPresent ",stepDescription);
				count = count+1;
			}
		}
		return flag;

	}

	/**
	 * Assert element present is not
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public boolean assertElementNotPresent(By by, String stepDescription)
			throws Throwable {

		boolean flag = false;
		try {
			if(!isElementPresent(by, stepDescription));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertElementNotPresent ",stepDescription);
				return false;
			} else {
				failureReport("AssertElementNotPresent ",stepDescription);
				count = count+1;
			}
		}
		return flag;

	}
	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 */

	public boolean assertText(By by, String text, String stepDescription) throws Throwable {
		boolean flag = false;
		try {

			Assert.assertEquals(getText(by, text).trim(), text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("AssertText ",stepDescription);				
				return false;
			} else {
				failureReport("AssertText ",stepDescription);
				count = count+1;
			}
		}

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public boolean verifyText(By by, String text, String stepDescription)
			throws Throwable {
		boolean flag = false;
		String vtxt="";

		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			vtxt = getText(by, stepDescription).trim();
			System.out.println("Text:"+vtxt);
			if(vtxt.equals(text.trim()))
			{ flag = true;
			return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("VerifyText ",stepDescription, "Expected: "+text, "Actual: "+vtxt);

			} else {
				failureReport("VerifyText ",stepDescription, "Expected: "+text, "Actual: "+vtxt);
			}
		}
	}

	

	/**
	 * @return: return title of current page.
	 * 
	 * @throws Throwable
	 */

	public String getTitle() throws Throwable {

		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			SuccessReport("Title ","Title of the page is: \""+text+"\"");
		}
		return text;
	}

	/**
	 * Assert Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public boolean asserTitle(String title) throws Throwable {
		boolean flag = false;

		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title
					+ "')]");
			if (waitForTitlePresent(windowTitle)) {
				Assert.assertEquals(getTitle(), title);
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			if (flag) {
				SuccessReport("AsserTitle ","Page title is verified: \""+title+"\"");				
			} else {
				failureReport("AsserTitle ","Page title is not matched with: \""+title+"\"");
				count = count+1;
			}
		}

	}

	/**
	 * Verify Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public boolean verifyTitle(String title) throws Throwable {

		boolean flag = false;

		try {
			getTitle().equals(title);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (flag) {
				SuccessReport("VerifyTitle ","Page title is verified: \""+title+"\"");				
			} else {
				failureReport("VerifyTitle ","Page title is not matched with: \""+title+"\"");
				count = count+1;
			}
		}
	}

	/**
	 * Verify text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on the current page.
	 * 
	 */
	public boolean verifyTextPresent(String text) throws Throwable {
		boolean flag = false;
		;
		if ((driver.getPageSource()).contains(text)) {
			SuccessReport("VerifyTextPresent ","\""+text+"\"is present in the page");
		} else {
			failureReport("VerifyTextPresent ","\""+text+"\"is not present in the page");
			count = count+1;

		}
		return flag;
	}


	/**
	 * Verify text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on the current page.
	 * 
	 */
	public boolean verifyTextNotPresent(String text) throws Throwable {
		boolean flag = false;
		;
		if (!(driver.getPageSource()).contains(text)) {
			SuccessReport("VerifyTextPresent ","\""+text+"\"is not present in the page");
		} else {
			failureReport("VerifyTextPresent ","\""+text+"\"is present in the page");
			count = count+1;
		}
		return flag;
	}
	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name wish to assert the value.
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:label, SignIn Link etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public String getAttribute(By by, String attribute,
			String stepDescription) throws Throwable {
		String value = "";
		if (isElementPresent(by, stepDescription)) {
			value = driver.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/**
	 * Text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on current page
	 * 
	 * @return: boolean value(true: if Text present, false: if text not present)
	 */

	public boolean isTextPresent(String text) throws Throwable {

		boolean value = false;
		if(driver.getPageSource().contains(text)){
			value = true;
			flag = true;
		}else{
			System.out.println("is text "+text+" present  " + value);
			flag = false;
		}
		if (value) {
			SuccessReport("IsTextPresent ", "\""+text+"\"is present in the page ");		
		} else  {
			failureReport("IsTextPresent ", "\""+text+"\"is  not present in the page ");
			count = count+1;
		}
		return value;
	}



	/**
	 * The innerText of this element.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:label text, SignIn Link
	 *            etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public String getText(By locator, String stepDescription)
			throws Throwable {
		String text = "";
		//boolean flag = false;
		try {
			if (isElementPresent(locator, stepDescription)) {
				text = driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public String getTextFromField(By locator, String stepDescription, boolean isReq)
			throws Throwable {
		String text = "";
		//boolean flag = false;
		try {
			if (isElementPresent(locator, stepDescription)) {
				text = driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isReq)
				throw new Exception("Get text of "+stepDescription+"field~Unable to get the text from "+stepDescription+"field");
			else
				return "false";
		}
		return text;
	}

	public String getValue(String locator, String stepDescription)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (driver.findElement(By.xpath(locator)).isDisplayed()) {
				text = driver.findElement(By.xpath(locator)).getAttribute(
						"value");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				SuccessReport("GetValue ", stepDescription);
			} else {
				failureReport("GetValue ", stepDescription);
				count = count+1;
			}
		}
		return text;
	}

	public int getElementsSize(By locator, String stepDescription)
			throws Throwable {
		int text = 0;
		try {
			if (driver.findElement(locator).isDisplayed()) {
				text = driver.findElements(locator).size();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * 
	 * @param fileName
	 *            : FileName screenshot save in local directory
	 * 
	 */
	public void screenShot(String fileName) {
		File scrFile;
		try {
			if(configProps.getProperty("ExecuteInBrowserstack").equals("True"))
			{
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			}

			else{
				scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			}


			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Click on the Link
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:SignIn Link, menu's
	 *            etc..)
	 */

	public boolean mouseHoverByJavaScript(By locator, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("MouseOver ",stepDescription);				
			} else {
				failureReport("MouseOver ",stepDescription);
			}
		}
	}

	public boolean JSClick(By locator, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			if(isReq)
				throw new Exception("Perform "+stepDescription+"~Unable to perform "+stepDescription);
			else
				return false;
		} 
		return flag;
	}

	/**
	 * This method switch the focus to selected frame using frame index
	 * 
	 * @param index
	 *            : Index of frame wish to switch
	 * 
	 */
	public boolean switchToFrameByIndex(int index) throws Throwable {
		boolean flag = false;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ", " Frame with index \""+index+"\" is selected");
			} else {
				failureReport("SelectFrame ", " Frame with index \""+index+"\" is not selected");
				count = count+1;
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue
	 *            : Frame ID wish to switch
	 * 
	 */
	public boolean switchToFrameById(String idValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame", "Frame with Id \""+idValue+"\" is selected");			
			} else {
				failureReport("SelectFrame", "Frame with Id \""+idValue+"\" is not selected");
				count = count+1;
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 */
	public boolean switchToFrameByName(String nameValue)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ", "Frame with Name \""+nameValue+"\" is selected");			
			} else {
				failureReport("SelectFrame ", "Frame with Name \""+nameValue+"\" is not selected");
				count = count+1;
			}
		}
	}

	/**
	 * This method switch the to Default Frame.
	 * 
	 * @throws Throwable
	 */
	public boolean switchToDefaultFrame() throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ","Frame with Name is selected");
			} else {
				failureReport("SelectFrame ","The Frame is not selected");
				count = count+1;
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * 
	 */
	public boolean switchToFrameByLocator(By lacator, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(driver.findElement(lacator));
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ", "Frame with Name \""+stepDescription+"\" is selected");
			} else {
				failureReport("SelectFrame ", "Frame \""+stepDescription+"\" is not selected");
				count = count+1;
			}
		}
	}

	/**
	 * This method wait selenium until element present on web page.
	 */
	public void ImplicitWait() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public boolean waitUntilTextPresents(By by, String 
			expectedText, String stepDescription) throws Throwable {
		wait = new WebDriverWait(driver, 120);
		boolean flag = false;

		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
					expectedText));

			flag = true;
			return  true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport(" WaitUntilTextPresent ",stepDescription);
			} else {
				failureReport("WaitUntilTextPresent ",stepDescription);
				count = count+1;
			}

		}

	}

	/**
	 * Click on Element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * @throws StaleElementReferenceException
	 *             - If the element no longer exists as initially defined
	 */

	public boolean click1(WebElement locator, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Click ", stepDescription);
			} else {
				failureReport("Click ", stepDescription);
				count = count+1;
			}
		}

	}

	/**
	 * 
	 * This method wait driver until given driver time.
	 * 
	 */
	public WebDriverWait driverwait() {

		wait = new WebDriverWait(driver, 30);
		return wait;
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */

	public boolean waitForVisibilityOfElement(By by, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Wait until visible for the element: '"+stepDescription+"'~Element '"+stepDescription+"' not visible");
			else
				return false;
		} 
	}
	
	/**
	 * This method wait selenium until element to be clicked on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */

	public boolean waitForElementToBeClickable (By by, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			flag = true;
			return true;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Click '"+stepDescription+"'~Unable to click the element '"+stepDescription+"'");
			else
				return false;
		} 
	}

	/**
	 * This method wait driver until Invisibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */
	public boolean waitForInVisibilityOfElement(By by, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			if(isReq)
				throw new Exception("Wait until visible for the element: '"+stepDescription+"'~Element '"+stepDescription+"' not visible");
			else
				return false;
		}

	}

	public List<WebElement> getElements(By locator) {

		List<WebElement> ele = driver.findElements(locator);

		return ele;
	}

	public boolean assertTextMatching(By by, String text,
			String stepDescription) throws Throwable {
		boolean flag = false;
		try {
			String ActualText = getText(by, text).trim();
			if (ActualText.contains(text)) {
				flag = true;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("Assert Text Matching" ,stepDescription);				
			} else {
				failureReport("Assert Text Matching" ,stepDescription);
				count = count+1;
			}
		}

	}

	// QuickFlix Funcations added

	public boolean isElementDisplayed(WebElement element)
			throws Throwable {
		boolean flag = false;
		for (int i = 0; i < 200; i++) {
			if (element.isDisplayed()) {
				flag = true;
				break;
			} else {
				Thread.sleep(50);
			}
		}
		return flag;
	}

	public void executeJavaScriptOnElement(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	
	public void closeCards(By locator1,By locator2) throws Throwable {
		try
		{
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			if (isElementDisplayed(locator2, "Closecard")) {
				int noOfCards = driver.findElements(locator2).size();
				for (WebElement card : driver.findElements(locator2)) {
					waitForElementToBeClickable(locator2, "", false);
					Thread.sleep(1000);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", card);					
					if(isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					//driver.switchTo().alert().accept();
					Thread.sleep(1000);
				}
				
			}
			if (isElementDisplayed(locator2, "Closecard")) {
				int noOfCards = driver.findElements(locator2).size();
				for (WebElement card : driver.findElements(locator2)) {
					waitForElementToBeClickable(locator2, "", false);
					Thread.sleep(1000);
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", card);
					if(isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
				}
				
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			String s = e.getMessage();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}
	}
	
	

	public boolean hitKey(By locator, Keys keyStroke, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(keyStroke);
			flag = true;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Type ",stepDescription);
			} else {
				failureReport("Type ",stepDescription);
				count = count+1;
			}
		}
	}

	public Collection<WebElement> getWebElementsByTagInsideLocator(
			By locator, String tagName, String stepDescription) throws Throwable {
		boolean flag = false;
		Collection<WebElement> elements;
		try {
			WebElement element = driver.findElement(locator);
			elements = element.findElements(By.tagName(tagName));
			flag = true;
		} catch (NoSuchElementException e) {
			throw e;
		} finally {
			if (flag) {
				SuccessReport("Type ",stepDescription);
			} else{
				failureReport("Type",stepDescription);
				count = count+1;
			}
		}
		return elements;
	}


	public void mouseOverElement(WebElement element, String stepDescription)
			throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				SuccessReport("MouseOver ",stepDescription);		
			} else {
				failureReport("MouseOver ",stepDescription);
				count = count+1;
			}
		}
	}

	public void SuccessReport(String strStepName, String strStepDes)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:
			break;

		case 2:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				screenShot("Results/HTML/Screenshots/"
						+ strStepDes.replaceAll("[^\\w]", "_") 
						+ ".jpeg");
			}
			onSuccess(strStepName, strStepDes);
			break;
			
		default:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				screenShot("Results/HTML/Screenshots/"
						+ strStepDes.replaceAll("[^\\w]", "_")
						+ ".jpeg");
			}
			onSuccess(strStepName, strStepDes);
			break;
		}
	}

	public void SuccessReport(String strStepName, String strStepDes, String expectedText, String actualText)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:
			break;

		case 2:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				screenShot("Results/HTML/Screenshots/"
						+ strStepDes.replaceAll("[^\\w]", "_") 
						+ ".jpeg");
			}
			onSuccess(strStepName, strStepDes, expectedText, actualText);
			break;

		default:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				screenShot("Results/HTML/Screenshots/"
						+ strStepDes.replaceAll("[^\\w]", "_")
						+ ".jpeg");
			}
			onSuccess(strStepName, strStepDes, expectedText, actualText);
			break;
		}
	}

	public void failureReport(String strStepName, String strStepDes)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		String stepExecTime =  ReportStampSupport.stepExecTime();
		switch (intReporterType) {
		case 1:
			flag = true;
			break;

		case 2:
			screenShot("Results/HTML/Screenshots/"+ strStepDes.replaceAll("[^\\w]", "_")+stepExecTime+".jpeg");
			flag = true;
			onFailure(strStepName, strStepDes, stepExecTime);
			break;

		default:
			flag = true;
			screenShot("Results/HTML/Screenshots/"+ strStepDes.replaceAll("[^\\w]", "_")+ReportStampSupport.stepExecTime()+".jpeg");
			onFailure(strStepName, strStepDes, stepExecTime);
			break;
		}

	}

	public void failureReport(String strStepName, String strStepDes, String expectedText, String actualText)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		String stepExecTime =  ReportStampSupport.stepExecTime();
		switch (intReporterType) {
		case 1:
			flag = true;
			break;

		case 2:
			screenShot("Results/HTML/Screenshots/"
					+ strStepDes.replaceAll("[^\\w]", "_")+stepExecTime+".jpeg");
			flag = true;
			onFailure(strStepName, strStepDes, stepExecTime, expectedText, actualText);
			break;

		default:
			flag = true;
			screenShot("Results/HTML/Screenshots/"
					+ strStepDes.replaceAll("[^\\w]", "_")+ReportStampSupport.stepExecTime()+".jpeg");
			onFailure(strStepName, strStepDes, stepExecTime, expectedText, actualText);
			break;
		}
	}

	public void warningReport(String strStepName, String strStepDes)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:
			flag = true;
			break;

		case 2:
			screenShot("Results/HTML/Screenshots/"
					+ strStepDes.replaceAll("[^\\w]", "_") 
					+ ".jpeg");
			flag = true;
			onWarning(strStepName, strStepDes);
			break;

		default:
			flag = true;
			screenShot("Results/HTML/Screenshots/"
					+ strStepDes.replaceAll("[^\\w]", "_")
					+ ".jpeg");
			onWarning(strStepName, strStepDes);
			break;
		}

	}

	public boolean verifyElementPresent(By by, String stepDescription)
			throws Throwable {

		boolean flag = false;

		try {
			if (isElementPresent(by, stepDescription)){       
				flag= true;   

			} 
		} catch(Exception e){                     
			System.out.println(e.getMessage());
			e.printStackTrace(); 
		}finally {
			if (flag) {
				SuccessReport("VerifyElementPresent ",stepDescription);   				

			} else {
				failureReport("VerifyElementPresent ",stepDescription);
				count = count+1;
			}
		}
		return flag;
	}

	 	
	
	/***
	 * Wait
	 * @param time
	 * @return
	 * @throws Throwable
	 */
	public boolean gSleep(long time)
			throws Throwable {

		boolean flag = false;

		try {
			Thread.sleep(time);
			SuccessReport("Wait","Waiting for "+time+" milliSeconds");                   


		} catch(Exception e){                     
			System.out.println(e.getMessage());
			e.printStackTrace();                    


		}

		return flag;

	}

	/**
	 * This method used type value in to text box or text area and click on Enter
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public boolean typeAndEnter(By locator, String testdata,String fieldName, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			String Data[] = testdata.split(";");
			driver.findElement(locator).clear();  
			//Thread.sleep(2000);

			for (int i = 0; i < Data.length; i++) {
				driver.findElement(locator).sendKeys(Data[i]);
				driver.findElement(locator).sendKeys(Keys.ENTER);
				//Thread.sleep(2000);
			}
			SuccessReport("Enter "+fieldName, fieldName+" successfully entered as :'"+testdata+"'");
			flag = true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			if(isReq)
				throw new Exception("Type the input in "+fieldName+" field~Unable to type the input in "+fieldName+" field");
			else
				return false;
		} 
		return flag;
	}
	
	
	/**
	 * This method used type value in to text box or text area and click on Enter
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public boolean typeClear(By locator, String testdata,String fieldName, String stepDescription, boolean isReq)
			throws Throwable {
		boolean flag = false;
		try {
			String Data[] = testdata.split(";");
			driver.findElement(locator).clear();  
			Thread.sleep(2000);

			for (int i = 0; i < Data.length; i++) {
				driver.findElement(locator).sendKeys(Data[i]);
				driver.findElement(locator).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
			}
			SuccessReport("Enter "+fieldName, fieldName+" successfully entered as :'"+testdata+"'");
			flag = true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			if(isReq)
				throw new Exception("Type the input in "+fieldName+" field~Unable to type the input in "+fieldName+" field");
			else
				return false;
		} 
		return flag;
	}
	
	

	/**
	 * This method gets the count on WebElements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */
	public int getCount(By by,int expectedSize,String stepDescription)
			throws Throwable {
		int count;
		List<WebElement> elements;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			elements = driver.findElements(by);
			count = elements.size();
		}catch(Exception e){
			count = 0;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Count:"+count);

		if (count == expectedSize) {
			SuccessReport("VerifyText ",stepDescription);      
		} else {
			failureReport("VerifyText ",stepDescription);   
			count = count+1;
		}
		return count;

	}


	/**
	 * verifyTextAttribute on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public boolean verifyTextAttribute(By by, String text, String attribute, String stepDescription)
			throws Throwable {
		boolean flag = false;
		String vtxt="";

		try {

			vtxt = getAttribute(by, attribute, stepDescription).trim();
			System.out.println("verify attribut Text:"+vtxt);
			if(vtxt.equals(text.trim()))
			{ flag = true;
			return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("VerifyText ",stepDescription, "Expected: "+text, "Actual: "+vtxt);						
			} else {
				failureReport("VerifyText ",stepDescription, "Expected: "+text, "Actual: "+vtxt);
				count = count+1;
			}
		}
	}

	/**
	 * verify Partial Text 
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param stepDescription
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public boolean verifyPartialText(By by, String text, String stepDescription)
			throws Throwable {
		boolean flag = false;
		String vtxt="";

		try {

			vtxt = getText(by, stepDescription).trim();
			System.out.println("Text:"+vtxt);
			if(vtxt.contains(text.trim()))
			{ flag = true;
			return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("VerifyText ",stepDescription, "Expected: "+text, "Actual: "+vtxt);						
			} else {
				failureReport("VerifyText ",stepDescription, "Expected: "+text, "Actual: "+vtxt);
				count = count+1;
			}
		}
	}

	/***
	 * setVisibility
	 * @param locator:Action performed on that element
	 * @return
	 * @throws Throwable
	 */

	public boolean setVisibility(By locator) throws Throwable {
		boolean flag = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;                       
			String strJS = "document.querySelectorAll(\"" + locator + "\")[0]";
			js.executeScript(strJS + ".style.display = \"none\";");
			js.executeScript(strJS + ".style.visibility = 'visible';");
			/*js.executeScript(strJS + ".style.opacity = 1;");
                         js.executeScript(strJS + ".style.width = '1px';");
                         js.executeScript(strJS + ".style.height = '1px';");*/
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			/*if (flag) {
                              SuccessReport("Select",stepDescription);
                       } else {
                              failureReport("Select",stepDescription);
                       }*/
		}
	}

}
