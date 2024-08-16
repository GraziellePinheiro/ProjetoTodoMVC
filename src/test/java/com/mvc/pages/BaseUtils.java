package com.mvc.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseUtils {
    private WebDriver browser;

    public WebDriver openBrowser(String url){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chocolatey\\bin\\chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.manage().window().maximize();
        this.browser.get(url);
        return browser;
    }

    public WebElement capturarInputField(){
        return browser.findElement(By.id("todo-input"));
    }
    
    public void createTask(String item){
        WebElement input = capturarInputField();
        input.click();
        input.sendKeys(item);
        input.sendKeys(Keys.ENTER);
    }

    public WebElement captureFooter(){
        return browser.findElement(By.cssSelector("footer.footer"));
    }

    public WebElement captureCounter(){
        return browser.findElement(By.cssSelector("todo-count"));
    }

    public WebElement captureFilters(){
        return browser.findElement(By.className("filters"));
    }

    public void validateFilters() {
        WebElement filtersList = captureFilters(); // Método para capturar a lista de filtros
        
        // Captura dos filtros
        WebElement allFilter = filtersList.findElement(By.xpath("//a[@href='#/' and text()='All']"));
        WebElement activeFilter = filtersList.findElement(By.xpath("//a[@href='#/active' and text()='Active']"));
        WebElement completedFilter = filtersList.findElement(By.xpath("//a[@href='#/completed' and text()='Completed']"));
        
        // Validação do filtro "All"
        String allFilterClass = allFilter.getAttribute("class");
        boolean isAllSelected = allFilterClass.contains("selected");
        System.out.println("Filtro 'All' está selecionado: " + isAllSelected);
        
        // Validação do filtro "Active"
        String activeFilterClass = activeFilter.getAttribute("class");
        boolean isActiveSelected = activeFilterClass.contains("selected");
        System.out.println("Filtro 'Active' está selecionado: " + isActiveSelected);
        
        // Validação do filtro "Completed"
        String completedFilterClass = completedFilter.getAttribute("class");
        boolean isCompletedSelected = completedFilterClass.contains("selected");
        System.out.println("Filtro 'Completed' está selecionado: " + isCompletedSelected);
        
        // Condicionais para validar a presença e o estado dos filtros
        if (isAllSelected) {
            System.out.println("O filtro 'All' está selecionado.");
        } else {
            System.out.println("O filtro 'All' não está selecionado.");
        }
    
        if (isActiveSelected) {
            System.out.println("O filtro 'Active' está selecionado.");
        } else {
            System.out.println("O filtro 'Active' não está selecionado.");
        }
    
        if (isCompletedSelected) {
            System.out.println("O filtro 'Completed' está selecionado.");
        } else {
            System.out.println("O filtro 'Completed' não está selecionado.");
        }
    }
    


}
