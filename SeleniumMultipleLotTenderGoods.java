
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






public class SeleniumMultipleLotTenderGoods 
{
    public static void main(String[] args) 
    {
        
        System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\Gecodriver\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        
        capabilities.setCapability("marionette", true);
        
        Random rand = new Random(); 
        int ii = rand.nextInt(100000); 
        
        try
        {
            WebDriver driver = new FirefoxDriver();
         
            driver.get("http://192.168.3.8:8080/");
            ((JavascriptExecutor) driver).executeScript("return window.stop");
            try
            {
                    driver.manage().window().maximize();
                    //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                    String currentUrl = driver.getCurrentUrl();

                    //driver.findElement(By.id("txtEmailId"));

                    WebElement email = driver.findElement(By.id("txtEmailId"));
                    email.clear();			

                    WebElement password = driver.findElement(By.name("password"));
                    password.clear();

                    email.sendKeys("pauserrotdormowhs@gmail.com");					
                    password.sendKeys("egp12345");



                    WebElement login = driver.findElement(By.id("btnLogin"));
                    login.submit();


                    String menuPath = "//*[@id='headTabApp']";

                    WebDriverWait wait = new WebDriverWait(driver, 20);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(menuPath)));  // locating the main menu

                    WebElement menu = driver.findElement(By.xpath(menuPath));
                    Actions builder = new Actions(driver); 
                    builder.moveToElement(menu).build().perform();

                    String dropDownMenuPath = "//ul/li/a[contains(text(),'Create APP')]";

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropDownMenuPath))); 

                    WebElement menuOption = driver.findElement(By.xpath(dropDownMenuPath));
                    builder.moveToElement(menuOption).click().build().perform();

                    //Thread.sleep(1800);
                    String budgetType = "//*[@id='cmbBudgetType']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(budgetType)));
                    Select budgetTypeSelect = new Select(driver.findElement(By.xpath(budgetType)));
                    budgetTypeSelect.selectByIndex(1);


                    driver.findElement(By.xpath("//*[@id='ActivityName']")).sendKeys("TEST_Activitity"+ii);
                    driver.findElement(By.xpath("//*[@id='txtAppCode']")).sendKeys("Letter Ref. No"+ii);


                    String aPPType = "//*[@id='cmbdepoplanWork']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(aPPType)));
                    Select aPPTypeSelect = new Select(driver.findElement(By.xpath(aPPType)));
                    aPPTypeSelect.selectByIndex(3);        

                    login = driver.findElement(By.id("buttonNext"));
                    login.submit();

                    String procurementCategory = "//*[@id='cmbProcureNature']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(procurementCategory)));
                    Select procurementCategorySelect = new Select(driver.findElement(By.xpath(procurementCategory)));
                    procurementCategorySelect.selectByIndex(1);


                    driver.findElement(By.xpath("//*[@id='txtPackageNo']")).sendKeys("TEST_Package No"+ii);
                    driver.findElement(By.xpath("//*[@id='txtaPackageDesc']")).sendKeys("TEST_Package_Description"+ii);
                    
                    for(int i=1;i<=4;i++)
                    {
                        driver.findElement(By.xpath("//*[@id='linkAddLot']")).click();
                    }
                    
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    
                    //Add 5 packages to the lot 
                    
                    String lotDescription[] ={"","Computer","Router","Server","Monitor","Generator"};
                    
                    for(int i=1;i<=5;i++)
                    {
                        driver.findElement(By.xpath("//*[@id='txtLotNo_"+i+"']")).sendKeys(Integer.toString(i));
                        driver.findElement(By.xpath("//*[@id='txtLotDesc_"+i+"']")).sendKeys(lotDescription[i]);
                        driver.findElement(By.xpath("//*[@id='txtQuantity_"+i+"']")).sendKeys("10");
                        driver.findElement(By.xpath("//*[@id='txtUnit_"+i+"']")).sendKeys("NOS");
                        WebElement element = driver.findElement(By.xpath("//*[@id='txtEstimateCost_"+i+"']"));
                        driver.findElement(By.xpath("//*[@id='txtEstimateCost_"+i+"']")).sendKeys("10");
                        js.executeScript("$('#txtEstimateCost_"+i+"').trigger('change')");
                    }
                    
                    String category = "//*[@id='hrefCPV']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(category)));
                    driver.findElement(By.xpath(category)).click();
                    
                    String parentWindow = driver.getWindowHandle();
                    System.out.println("before "+driver.getTitle());
                    Set<String> s1 = driver.getWindowHandles();

                    Iterator<String> i1 =  s1.iterator();

                    while(i1.hasNext())
                    {
                        String jsTreePath = "//li[@id='170']/a";

                        String buttonPath = "//*[@id='btnGetCheckedNode']";
                        String childWindow = i1.next();

                        if(!parentWindow.equalsIgnoreCase(childWindow))
                        {
                            driver.switchTo().window(childWindow);

                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jsTreePath)));
                            driver.findElement(By.xpath(jsTreePath)).click();
                            System.out.println("before "+driver.getTitle());
                            //Thread.sleep(1000);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buttonPath)));
                            driver.findElement(By.xpath(buttonPath)).click();
                            //driver.close();
                        }
                    }


                    driver.switchTo().window(parentWindow);
                    System.out.println("before "+driver.getTitle());


                    String procurementType = "//*[@id='cmbProcureType']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(procurementType)));
                    Select procurementTypeSelect = new Select(driver.findElement(By.xpath(procurementType)));
                    procurementTypeSelect.selectByIndex(1);

                    String procurementMethod = "//*[@id='cmbProcureMethod']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(procurementMethod)));
                    Select procurementMethodSelect = new Select(driver.findElement(By.xpath(procurementMethod)));
                    procurementMethodSelect.selectByIndex(2);

                    String sourceofFund = "//*[@id='cmbSourceOfFund']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sourceofFund)));
                    Select sourceofFundSelect = new Select(driver.findElement(By.xpath(sourceofFund)));
                    sourceofFundSelect.selectByIndex(2);

                    WebElement login1 = driver.findElement(By.xpath("//*[@id='btnNext']"));

                    login1.click();


                    String datePath = "//*[@id='txtRfqdtadvtift']";


                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date dt = new Date();

                    Calendar cl = Calendar.getInstance();
                    cl.setTime(dt);;
                    cl.add(Calendar.DAY_OF_YEAR, 1);
                    dt=cl.getTime();

                    String str = df.format(dt);

                    System.out.println("the date today is " + str);



                    WebElement date=driver.findElement(By.id("txtRfqdtadvtift"));
                    String dateVal = str;
                    selectDateByJs(driver,date,dateVal);

                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    //jse.executeScript("calculateTotalNoofDays();");
                    js.executeScript("$('#txtRfqdtadvtift').trigger('blur')");


                    driver.findElement(By.xpath("//*[@id='txtRfqexpdtopenNo']")).sendKeys("10");
                    js.executeScript("$('#txtRfqexpdtopenNo').trigger('blur')");
                    driver.findElement(By.xpath("//*[@id='txtRfqdtsubevaRptNo']")).sendKeys("10");
                    js.executeScript("$('#txtRfqdtsubevaRptNo').trigger('blur')");
                    //driver.findElement(By.xpath("//*[@id='txtRfqexpdtopenNo']")).sendKeys("10");
                    js.executeScript("$('#txtRfqexpdtAppawdNo').trigger('blur')");
                    //driver.findElement(By.xpath("//*[@id='txtRfqexpdtopenNo']")).sendKeys("10");
                    js.executeScript("$('#txtRfqexpdtLtrIntAwdNo').trigger('blur')");
                    driver.findElement(By.xpath("//*[@id='txtRfqdtIssNOANo']")).sendKeys("10");
                    js.executeScript("$('#txtRfqdtIssNOANo').trigger('blur')");
                    driver.findElement(By.xpath("//*[@id='txtRfqexpdtSignNo']")).sendKeys("10");
                    js.executeScript("$('#txtRfqexpdtSignNo').trigger('blur')");


                    login = driver.findElement(By.xpath("//*[@id='btnSave']"));


                    login.click();


                    String createWorkflowLink = "//a[contains(@href,'CreateWorkflow.jsp')]";

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(createWorkflowLink))); 

                    menuOption = driver.findElement(By.xpath(createWorkflowLink));
                    builder.moveToElement(menuOption).click().build().perform();



                    String fileProcessingLink = "//a[contains(@href,'FileProcessing.jsp')]";

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fileProcessingLink))); 

                    menuOption = driver.findElement(By.xpath(fileProcessingLink));
                    builder.moveToElement(menuOption).click().build().perform();        



                    String editorFramePath = "//iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";

                    wait = new WebDriverWait(driver, 20);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editorFramePath)));

                    WebElement editorFrame = driver.findElement(By.xpath(editorFramePath));

                    driver.switchTo().frame(editorFrame);

                    WebElement body = driver.findElement(By.tagName("body"));

                    body.clear(); 
                    body.sendKeys("some text");

                    driver.switchTo().defaultContent();



                    String actionPath = "//*[@id='txtAction']";
                    wait = new WebDriverWait(driver, 20);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(actionPath)));

                    Select actionPathSelect = new Select(driver.findElement(By.xpath(actionPath)));
                    actionPathSelect.selectByIndex(1);


                    login = driver.findElement(By.xpath("//*[@id='tbnAdd']"));

                    login.click();
                        
                    currentUrl = driver.getCurrentUrl();



                    System.out.println(currentUrl);

                    String appId = grabUrlAppId(currentUrl);
                    System.out.println("Random "+ii);
                    
                    driver.findElement(By.xpath("//a[contains(@href,'Logout.jsp')]")).click();

                    email = driver.findElement(By.id("txtEmailId"));
                    email.clear();			

                    password = driver.findElement(By.name("password"));
                    password.clear();

                    email.sendKeys("hoparotdormowhs@gmail.com");					
                    password.sendKeys("egp12345");

                    login = driver.findElement(By.id("btnLogin"));
                    login.submit();

                    String jqueryGridPath = "//*[@id='list']";

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jqueryGridPath)));

                    String aPPID = appId+" (APP ID)";    
                    String linkAppID = "";

                    fileProcessingLink = "";

                    String beforeXpath = "//*[@id='list']/tbody/tr[";
                    String afterXpath = "]/td/a";

                    String beforeAppIDXpath = "//*[@id='list']/tbody/tr[";
                    String AfterAppIDXpath = "]/td[4]";

                    WebElement table = driver.findElement(By.id("list")); 
                    List<WebElement> allRows = table.findElements(By.tagName("tr")); 

                    for(int i=1;i<=allRows.size();i++)
                    {
                        linkAppID = driver.findElement(By.xpath(beforeAppIDXpath+i+AfterAppIDXpath)).getText();

                        if(linkAppID.equalsIgnoreCase(aPPID))
                        {
                            String s = beforeAppIDXpath+i+AfterAppIDXpath;
                            System.out.println(linkAppID);

                            fileProcessingLink = beforeAppIDXpath+i+"]/td/a[contains(@href,'FileProcessing.jsp')]";

                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fileProcessingLink)));     
                            driver.findElement(By.xpath(fileProcessingLink)).click();
                            break;

                        }
                        //System.out.println(linkAppID);
                    }    

                    //*[@id='list']/tbody/tr[4]/td/a[contains(@href,'FileProcessing.jsp')]
                    editorFramePath = "//iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                    chkEditor(driver, wait, editorFramePath);

                    String dropDownPath = "//*[@id='txtAction']";

                    selectDropdown(driver, wait, dropDownPath, 2);


                    String button = "//*[@id='tbnAdd']";

                    submitButton(driver, button);

                    driver.findElement(By.xpath("//a[contains(@href,'Logout.jsp')]")).click();

                    email = driver.findElement(By.id("txtEmailId"));
                    email.clear();			

                    password = driver.findElement(By.name("password"));
                    password.clear();

                    email.sendKeys("pauserrotdormowhs@gmail.com");					
                    password.sendKeys("egp12345");

                    login = driver.findElement(By.id("btnLogin"));
                    login.submit();


                    menuPath = "//*[@id='headTabApp']";
                    dropDownMenuPath = "//ul/li/a[contains(text(),'My APP')]";

                    dropDownMenuLink(driver, wait, menuPath, dropDownMenuPath, builder);    

                    jqueryGridPath = "//*[@id='list']";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jqueryGridPath)));


                    aPPID = appId;    
                    linkAppID = "";

                    String dashboardLink = "";

                    beforeXpath = "//*[@id='list']/tbody/tr[";
                    afterXpath = "]/td/a";

                    beforeAppIDXpath = "//*[@id='list']/tbody/tr[";
                    AfterAppIDXpath = "]/td[2]";

                    table = driver.findElement(By.id("list")); 
                    allRows = table.findElements(By.tagName("tr")); 

                    for(int i=2;i<allRows.size();i++)
                    {
                        linkAppID = driver.findElement(By.xpath(beforeAppIDXpath+i+AfterAppIDXpath)).getText();

                        if(linkAppID.equalsIgnoreCase(aPPID))
                        {
                            String s = beforeAppIDXpath+i+AfterAppIDXpath;
                            System.out.println(linkAppID);

                            dashboardLink = beforeAppIDXpath+i+"]/td/a[contains(@href,'APPDashboard.jsp')]";

                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dashboardLink)));     
                            driver.findElement(By.xpath(dashboardLink)).click();
                            break;

                        }
                        //System.out.println(linkAppID);
                    }    

                    submitButton(driver, "//a[contains(@href,'APPWorkflowView.jsp')]", wait);

                    //textarea[@id='txtremark']

                    driver.findElement(By.xpath("//textarea[@id='txtremark']")).sendKeys("TEST_Package No");

                    submitButton(driver, "//*[@id=\"btnsubmit\"]", wait);

                    submitButton(driver, "//*[@id=\"resultTable\"]/tbody/tr[2]/td[8]/a[2]", wait);

                    String confirmPath = "//*[@id='popup_ok']";

                    clickTenderPopUp(driver, confirmPath);

                    String url = driver.getCurrentUrl();

                    String tenderId = grabUrlTenderId(url);

                    driver.findElement(By.id("integrityPackcnk")).click();
                    
                    driver.findElement(By.xpath("//*[@id='txtinvitationRefNo']")).sendKeys("TestSelenium"+tenderId);

                    String dateID = "txtpreQualCloseDate";

                    String ClosingOpeningDate = getDate(driver,dateID,"CloseOpen");

                    jse = (JavascriptExecutor) driver;
                    jse.executeScript("$('#txtpreQualCloseDate').trigger('blur')");

                    String PublicationDateAndTime = getDate(driver,"txttenderpublicationDate","publication");
                    jse.executeScript("$('#txtpreQualCloseDate').trigger('blur')");

                    String LastDateAndTimeBidSecuritySubmission  = getDate(driver,"txtlastDateTenderSub","bidsecurity");
                    jse.executeScript("$('#txtlastDateTenderSub').trigger('blur')");

                    String TenderDocumentsellingdownloadinGDateTime  = getDate(driver,"txttenderLastSellDate","download");
                    jse.executeScript("$('#txttenderLastSellDate').trigger('blur')");

                    String getText = "";


                    editorFramePath = "//*[@id='cke_1_contents']/iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                    chkEditor(driver, wait, editorFramePath, "Eligibility of Bidder/Consultant");


                    editorFramePath = "//*[@id='cke_2_contents']/iframe[contains(@class, 'cke_wysiwyg_frame cke_reset')]";
                    chkEditor(driver, wait, editorFramePath, "Selenium Webdriver Test Tender: Brief Description of Goods <br>Multiple Lot</br> and Related Service :");

                    String ContractStartDate="";
                    String ContractEndDate="";
                    
                    for(int i=0;i<5;i++)
                    {
                        //driver.findElement(By.id("chkbidSecDeclaration_"+i+"")).click();
                        selectCheckBox(driver, wait,"//*[@id='chkbidSecDeclaration_"+i+"']");
                        
                        driver.findElement(By.xpath("//*[@id='locationlot_"+i+"']")).sendKeys("PA Office");
                        driver.findElement(By.xpath("//*[@id='tenderSecurityAmount_"+i+"']")).sendKeys("1000");
                        
                        ContractStartDate  = getDate(driver,"startTimeLotNo_"+i+"",1);
                        jse.executeScript("$('#startTimeLotNo_"+i+"').trigger('blur')");

                        ContractEndDate  = getDate(driver,"complTimeLotNo_"+i+"",2);
                        jse.executeScript("$('#complTimeLotNo_"+i+"').trigger('blur')");
                        Thread.sleep(200);
                    }
                    
                    
                    
                    
                    
                    submitButton(driver, "//*[@id='btnsubmit']", wait);

                    By by = By.xpath("//*[@id='spantxtpreQualCloseDate']/div[@class='reqF_1']");

                    Boolean elementID = FindElement(driver, by, 1);


                    if(elementID == true)
                    {
                        getText = driver.findElement(By.xpath("//*[@id='spantxtpreQualCloseDate']/div[@class='reqF_1']")).getText();
                    }
                    else
                    {
                        by = By.xpath("//*[@id='demoClose']");

                        elementID = FindElement(driver, by, 3);
                        if(elementID == true)
                        {
                            getText = driver.findElement(By.xpath("//*[@id='demoClose']")).getText();
                            if(getText.equalsIgnoreCase(""))
                            {
                                getText = driver.findElement(By.xpath("//*[@id='holiClose']")).getText();
                            }
                        }
                    }

                    if(getText.equalsIgnoreCase("Holiday!") || getText.equalsIgnoreCase("Closing and Opening Date can not be weekend!") || getText.equalsIgnoreCase("Weekend!") || getText.equalsIgnoreCase("Closing and Opening Date can not be holiday!"))
                    {
                        ClosingOpeningDate = getDate(driver, dateID, 43,"CloseOpen",ClosingOpeningDate);

                        jse.executeScript("$('#txtpreQualCloseDate').trigger('blur')");
                        submitButton(driver, "//*[@id='btnsubmit']", wait);
                    }
                    else
                    {
                        submitButton(driver, "//*[@id='btnsubmit']", wait);
                    }
                    
                    
                    
                    by = By.xpath("//*[@id='msgDeclaration_1']");

                    elementID = FindElement(driver, by, 1);
                    if(elementID == true)
                    {
                        getText = driver.findElement(By.xpath("//*[@id='msgDeclaration_1']")).getText();
                        if(getText.equalsIgnoreCase("Please select Bid Declaration Type"))
                        {
                            selectCheckBox(driver, wait,"//*[@id='msgDeclaration_1']");
                        }
                    }

                    driver.findElement(By.xpath("//*[@id='txttenderValidity']")).sendKeys("90");

                    js.executeScript("$('#txttenderValidity').trigger('blur')");
                    driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();

                    submitButton(driver, "//a[contains(@href,'TenderDocPrep.jsp')]", wait);
                    
                                

                        
                
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
        
        /*if(weUnchecked.isEnabled())
        {
            weUnchecked.click();
            Thread.sleep(200);
        }
        else
        {
            //wait = new WebDriverWait(driver, 10);
            //Thread.sleep(3000);
            //weUnchecked.click();
            //Thread.sleep(3000);
            //wait = new WebDriverWait(driver, 10);
        }
        
        WebElement element = driver.findElement(By.xpath(xpath));

        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        */
        
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