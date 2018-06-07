
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniummultiplelottendergoods;

import java.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import static org.openqa.selenium.remote.DesiredCapabilities.firefox;

/**
 *
 * @author Nitish Ranjan Bhowmik
 */






public class TenderMultipleLotDocument  
{
    public static void main(String[] args) 
    {
        
        System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\Gecodriver\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        
        capabilities.setCapability("marionette", true);
        
        Random rand = new Random(); 
        int ii = rand.nextInt(100000); 
        
        WebDriver driver = new FirefoxDriver();
        WebElement submi = null;
        Actions builder = new Actions(driver);
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        
        try
        {
            driver.get("http://192.168.3.8:8080/");
            ((JavascriptExecutor) driver).executeScript("return window.stop");
            try
            {
                driver.manage().window().maximize();
                
                String currentUrl = driver.getCurrentUrl();
                
                WebElement email = driver.findElement(By.id("txtEmailId"));
                    
                email.clear();			

                WebElement password = driver.findElement(By.name("password"));
                password.clear();

                email.sendKeys("pauserrotdormowhs@gmail.com");					
                password.sendKeys("egp12345");
                
                WebElement login = driver.findElement(By.id("btnLogin"));
                login.submit();
                
                
                String menuPath = "//*[@id='headTabTender']";
                String dropDownMenuPath = "//a[contains(text(),'My Tender')]";

                dropDownMenuLink(driver, wait, menuPath, dropDownMenuPath, builder);    


                submitButton(driver, "//a[contains(text(),'Under Preparation')]", wait);

                String tenderID = "1218,\nTestSelenium1218";    
                String linktenderID = "";

                String dashboardLink = "";

                //*[@id='resultTable']/tbody/tr[2]/td[7]/a[contains(@href,'TenderDashboard.jsp')]

                //*[@id='resultTable']/tbody/tr[4]

                WebElement table = driver.findElement(By.id("resultTable")); 
                List<WebElement> allRows = table.findElements(By.tagName("tr")); 

                String beforeAppIDXpath = "//*[@id='resultTable']/tbody/tr[";
                String AfterAppIDXpath = "]/td[2]";

                for(int i=1;i<allRows.size();i++)
                {
                    linktenderID = driver.findElement(By.xpath(beforeAppIDXpath+i+AfterAppIDXpath)).getText();

                    if(linktenderID.equalsIgnoreCase(tenderID))
                    {
                        String s = beforeAppIDXpath+i+AfterAppIDXpath;
                        System.out.println(linktenderID);

                        dashboardLink = beforeAppIDXpath+i+"]/td[7]/a[contains(@href,'TenderDashboard.jsp')]";

                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dashboardLink)));     
                        driver.findElement(By.xpath(dashboardLink)).click();
                        break;

                    }
                    //System.out.println(linkAppID);
                }    

                submitButton(driver, "//a[contains(@href,'TenderDocPrep.jsp')]", wait);

                String lotDescription[] ={"","Computer","Router","Server","Monitor","Generator"};

                String formTenderID="";
                String beforeActionLinkID="";
                String afterActionLinkID="";
                String fromDashBoardLinkID="";
                beforeAppIDXpath = "//table[";
                AfterAppIDXpath = "]/tbody/tr/th[contains(text(),'Form Name 2')]";

                String genearateXpath="";

                By by;
                Boolean flag = false;
                Boolean discountFrom = false;

                allRows = driver.findElements(By.xpath("//table"));
                String createFormWorkFlow = "";
                
                int docCount = 0;
                
                for(int i=1;i<=allRows.size();i++)
                {
                    if(docCount < 5)
                    {
                        String ss = beforeAppIDXpath+i+AfterAppIDXpath;
                        by = By.xpath(beforeAppIDXpath+i+AfterAppIDXpath);
                        flag = FindElement(driver, by, 1);
                        if(flag == true)
                        {
                            formTenderID = driver.findElement(By.xpath(beforeAppIDXpath+i+AfterAppIDXpath)).getText();
                            createFormWorkFlow = driver.findElement(By.xpath(beforeAppIDXpath+i+AfterAppIDXpath)).getText();
                            if(formTenderID.equalsIgnoreCase("Form Name 2"))
                            {
                                docCount++;
                                beforeActionLinkID = beforeAppIDXpath+i+"]/tbody/tr[";                        
                                afterActionLinkID = "]/td[3]/a[contains(@href,'TenderTableDashboard.jsp')]";
                                for(int j=1;j<=5;j++)
                                {
                                    fromDashBoardLinkID = beforeActionLinkID+j+afterActionLinkID;
                                    by = By.xpath(fromDashBoardLinkID);
                                    flag = FindElement(driver, by, 1);
                                    if(flag == true)
                                    {
                                        submitButton(driver, fromDashBoardLinkID, wait);
                                        printUrl(driver);
                                        submitButton(driver, "//a[contains(text(),'Fill up the Tables')]", wait);
                                        printUrl(driver);

                                        genearateXpath = "//*[@id='frmTableCreation']/table[2]/tbody/tr/td[contains(text(),'Discount Form')]";
                                        by = By.xpath(genearateXpath);
                                        discountFrom = FindElement(driver, by, 1);

                                        if(discountFrom == true)
                                        {
                                            submitButton(driver, "//*[@id='sucolumnbBtnCreateEdit']", wait);
                                            driver.switchTo().alert().accept();                    
                                            submitButton(driver, "//a[contains(text(),'Tender Document')]", wait);
                                        }
                                        else
                                        {
                                            submitButton(driver, "//a[contains(text(),'Add Row')]", wait);
                                            //submitButton(driver, "//a[contains(text(),'Form Dashboard')]", wait);
                                            docFiilUp(driver, wait);
                                            submitButton(driver, "//*[@id='sucolumnbBtnCreateEdit']", wait);
                                            driver.switchTo().alert().accept();

                                            //submitButton(driver, "//a[contains(text(),'Form Dashboard')]", wait);
                                            submitButton(driver, "//a[contains(text(),'Tender Document')]", wait);
                                            printUrl(driver);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                beforeAppIDXpath = "//table[";
                AfterAppIDXpath = "]/tbody/tr/td/a";
                
                String createIndex = "";
                
                allRows = driver.findElements(By.xpath("//table"));
                docCount = 0;
                
                for(int i=1;i<=allRows.size();i++)
                {
                    if(docCount < 5)
                    {
                        createIndex = beforeAppIDXpath+i+AfterAppIDXpath;
                        by = By.xpath(beforeAppIDXpath+i+AfterAppIDXpath);

                        flag = FindElement(driver, by, 1);
                        if(flag == true)
                        {
                            createFormWorkFlow = driver.findElement(By.xpath(createIndex)).getText();
                            if(createFormWorkFlow.equalsIgnoreCase("Create"))
                            {
                                docCount++;
                                driver.findElement(By.xpath("//table/tbody/tr/td/a[contains(text(),'Create')]")).click();
                                submitButton(driver, "//*[@id='saveoredit']", wait);
                                submitButton(driver, "//a[contains(@href,'TenderDocPrep.jsp')]", wait);
                            }
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                
                
                
              
            }        
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public static void selectCheckBox(WebDriver driver, WebDriverWait wait, String xpath) throws InterruptedException
    {
        wait = new WebDriverWait(driver, 15);
        
        //WebElement weUnchecked = driver.findElement(By.xpath(xpath));
        WebElement elementChkBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        
        if(!elementChkBox.isSelected()) 
        {
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", elementChkBox);
        }
    }
    
    
    public static void docFiilUp(WebDriver driver, WebDriverWait wait) throws InterruptedException
    {
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='FormMatrix']/tbody/tr"));
        
        String genearateXpath="";
        
        String beforeXpath = "//*[@id='FormMatrix']/tbody/tr[";
        String middleXpath = "]/td[";
        String lastXpath = "]/input";
        
        String textAreaXpath = "]/textarea";
        
        By by;
        Boolean flag1 = false, flag2= false;
        
        int row =1;
        
        String description = "Computer";
        String fillDocUpID = "";
      
        for(int i=2;i<=rows.size();i++)
        {
            Thread.sleep(2000);
            for(int j=3;j<=8;j++)
            {
                genearateXpath = beforeXpath+i+middleXpath+j+lastXpath;
                by = By.xpath(beforeXpath+i+middleXpath+j+lastXpath);
                flag1 = FindElement(driver, by, 1);
                if(flag1 == true)
                {
                    if(j==3)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys(Integer.toString(row));
                    }
                    if(j == 5)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("Lot");
                    }
                    if(j == 6)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("50");
                    }
                    if(j == 7)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("PA Office");
                    }
                    if(j == 8)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys("10");
                    }
                }
                else
                {
                    genearateXpath = beforeXpath+i+middleXpath+j+textAreaXpath;
                    by = By.xpath(beforeXpath+i+middleXpath+j+textAreaXpath);
                    flag2 = FindElement(driver, by, 1);
                    if(flag2 == true)
                    {
                        driver.findElement(By.xpath(genearateXpath)).sendKeys(description);
                    }
                }
                    
            }
            row++;
            description = "Router";
        }
    }
    
    public static String getDate(WebDriver driver, String dateID, int yearInc)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);;
        cl.add(Calendar.YEAR, yearInc);
        dt=cl.getTime();

        String date = df.format(dt);

        System.out.println("the date today is " + date);
        
        WebElement element = driver.findElement(By.id(dateID));
        
        selectDateByJs(driver,element,date);
        
        return date;
    }
    
    public static String getDate(WebDriver driver, String dateID, String parameterID)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);;
        int dateInc = 0;
        if(parameterID.equalsIgnoreCase("publication"))
        {
            dateInc = 1;
        }
        else if(parameterID.equalsIgnoreCase("CloseOpen"))
        {
            dateInc = 40;
        }
        else if(parameterID.equalsIgnoreCase("bidsecurity"))
        {
            dateInc = 31;
        }
        else if(parameterID.equalsIgnoreCase("download"))
        {
            dateInc = 14;
        }
        
        
        cl.add(Calendar.DAY_OF_YEAR, dateInc);
        dt=cl.getTime();

        String date = df.format(dt);

        System.out.println(parameterID+" date is " + date);
        
        WebElement element = driver.findElement(By.id(dateID));
        
        selectDateByJs(driver,element,date);
        
        return date;
    }
    
    
    
    public static String getDate(WebDriver driver, String dateID, int dateIncreament, String ParameterID, String ClosingOpeningDate)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);;
        cl.add(Calendar.DAY_OF_YEAR, dateIncreament);
        dt=cl.getTime();

        String date = df.format(dt);

        System.out.println("the date today is " + date);
        
        WebElement element = driver.findElement(By.id(dateID));
        
        selectDateByJs(driver,element,date);
        
        return date;
    }
    
    public static String grabUrlAppId(String url)
    {
        int firstIndex = url.indexOf("ID=");
        int lastIndex = url.indexOf("&msg");
        String appID = "";
        for(int i=firstIndex+3;i<lastIndex;i++)
        {
            appID = appID + url.charAt(i);
        }
        System.out.println(appID);
        return appID;
    }
    
    
    
    public static String grabUrlTenderId(String url)
    {
        
        int firstIndex = url.indexOf('=');
       
        String tenderId = "";
        
        for(int i=firstIndex+1;i<url.length();i++)
        {
            tenderId = tenderId + url.charAt(i);
        }
        System.out.println(tenderId);
        return tenderId;
    }
    
    
    public static void submitButton(WebDriver driver, String editorFramePath)
    {
        driver.findElement(By.xpath("//*[@id='tbnAdd']")).click();
        
    }
    
    
    public static void selectDropdown(WebDriver driver, WebDriverWait wait,String dropDownPath, int selectIndex)
    {
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropDownPath)));
        Select dropDownValueSelect = new Select(driver.findElement(By.xpath(dropDownPath)));
        dropDownValueSelect.selectByIndex(selectIndex);
        
    }
    
    
    public static void submitButton(WebDriver driver, String linkPath, WebDriverWait wait)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(linkPath)));
        driver.findElement(By.xpath(linkPath)).click();
        
    }
    
    public static void dropDownMenuLink(WebDriver driver, WebDriverWait wait,String menuPath, String dropDownMenuPath, Actions builder)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(menuPath)));  // locating the main menu
        WebElement menu = driver.findElement(By.xpath(menuPath));
        builder.moveToElement(menu).build().perform();
                
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropDownMenuPath))); 
        WebElement menuOption = driver.findElement(By.xpath(dropDownMenuPath));
        builder.moveToElement(menuOption).click().build().perform();
        
    }
    
    
    
    
    public static void selectDateByJs(WebDriver driver,WebElement element, String dateVal)
    {
        JavascriptExecutor js =((JavascriptExecutor)driver);
        js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", element);
    }
    
    
    public static void chkEditor(WebDriver driver,WebDriverWait wait, String editorFramePath, String content)
    {
        //String editorFramePath = "//iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editorFramePath)));
                
        WebElement editorFrame = driver.findElement(By.xpath(editorFramePath));

        driver.switchTo().frame(editorFrame);

        WebElement body = driver.findElement(By.tagName("body"));

        body.clear(); 
        body.sendKeys(content);
                
        driver.switchTo().defaultContent();
    }
    
    
    
    public static void chkEditor(WebDriver driver,WebDriverWait wait, String editorFramePath)
    {
        //String editorFramePath = "//iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editorFramePath)));
                
        WebElement editorFrame = driver.findElement(By.xpath(editorFramePath));

        driver.switchTo().frame(editorFrame);

        WebElement body = driver.findElement(By.tagName("body"));

        body.clear(); 
        body.sendKeys("some text");
                
        driver.switchTo().defaultContent();
    }
    
    public static void printUrl(WebDriver driver)
    {
        String currentUrl = driver.getCurrentUrl();
    
        System.out.println(currentUrl);
    }
    
    public static Boolean FindElement(WebDriver driver, By by, int timeoutInSeconds)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until( ExpectedConditions.presenceOfElementLocated(by) ); //throws a timeout exception if element not present after waiting <timeoutInSeconds> seconds
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public static void clickTenderPopUp(WebDriver driver, String confirmPath)
    {
        try 
        {
            Thread.sleep(1000);
            //element = driver.findElement(By.xpath("//*[@id='popup_container']"));            
        
            WebElement element = driver.findElement(By.xpath(confirmPath));
            
            element.click();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(SeleniumMultipleLotTenderGoods.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    
    
    
}