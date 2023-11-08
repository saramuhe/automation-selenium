package org.demoqa;


import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TextBox {


    public static void main(String[] args) throws IOException {
        String fullName = "Brayan Alexis Silva Ramirez";
        String email = "brayan_silva82151@elpoli.edu.co";
        String currentAddress = "Carrera 48 No. 7–151, El Poblado - Antioquia";
        String permananentAddress = "Medellin - Antioquia";
        File screenshotFile;

        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        String baseUrl = "https://demoqa.com/text-box";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement inputFullName = driver.findElement(By.xpath("//input[@id=\"userName\"]"));
        inputFullName.sendKeys(fullName);

        WebElement inputEmail = driver.findElement(By.xpath("//input[@id=\"userEmail\"]"));
        inputEmail.sendKeys(email);

        WebElement textAreaCurrentAddress = driver.findElement(By.xpath("//textarea[@id=\"currentAddress\"]"));
        textAreaCurrentAddress.sendKeys(currentAddress);

        WebElement textAreaPermananentAddress =
                driver.findElement(By.xpath("//textarea[@id=\"permanentAddress\"]"));
        textAreaPermananentAddress.sendKeys(permananentAddress);

        screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshot_formulary.png"));

        WebElement btnSubmit = driver.findElement(By.xpath("//button[@id=\"submit\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(900,500)", "");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        btnSubmit.click();

        screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshot_submit.png"));

        String outputName = driver.findElement(By.xpath("//p[@id=\"name\"]")).getText();
        String outputEmail = driver.findElement(By.xpath("//p[@id=\"email\"]")).getText();
        String outputCurrentAddress = driver.findElement(By.xpath("//p[@id=\"currentAddress\"]")).getText();
        String outputPermanentAddress = driver.findElement(By.xpath("//p[@id=\"permanentAddress\"]")).getText();

        Assert.assertEquals("Name:" + fullName, outputName);
        Assert.assertEquals("Email:" + email, outputEmail);
        Assert.assertEquals("Current Address :" + currentAddress, outputCurrentAddress);
        Assert.assertEquals("Permananet Address :" + permananentAddress, outputPermanentAddress);

        driver.quit();

    }


}