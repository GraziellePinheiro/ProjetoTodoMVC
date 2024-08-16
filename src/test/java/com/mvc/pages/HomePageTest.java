package com.mvc.pages;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;


@DisplayName("Tests for Home page")
public class HomePageTest{ 
    private WebDriver browser;
    BaseUtils baseUtils = new BaseUtils();
    @BeforeEach
    public void setUp(){
        
        this.browser = baseUtils.openBrowser("https://todomvc.com/examples/react/dist/");
    }
    @AfterEach
    public void tearDown(){
        this.browser.quit();
    }

    @DisplayName("Confirma a presença do placeholder na caixa de entrada")
    @Test
    public void testOpenHomePage(){
       
        WebElement input = baseUtils.capturarInputField();
        String placeholder = input.getAttribute("placeholder");

        Assertions.assertEquals("What needs to be done?", placeholder);

    }

    @DisplayName("Confirma que a caixa de entrada está disponivel para adicionar itens")
    @Test
    public void testFieldAvailable(){
    
        WebElement input = baseUtils.capturarInputField();
        input.click();
        Assertions.assertTrue(input.isEnabled());
    }

    @DisplayName("Adiciona uma tarefa a lista")
    @Test
    public void testAddTaskToList(){
        
        String item1 = "Item 1";
        baseUtils.createTask(item1);

        List<WebElement> itensAdicionados = this.browser.findElements(By.cssSelector("ul.todo-list li"));
        WebElement primeiroItem = itensAdicionados.get(0).findElement(By.cssSelector("label[data-testid='todo-item-label']"));
        
        Assertions.assertEquals(item1, primeiroItem.getText());
    }
    @DisplayName("Valida que o rodapé esteja presente após inserir uma tarefa")
    @Test
    public void testBaseboard(){
        String item1 = "Item 1";
        baseUtils.createTask(item1);

        boolean rodape = this.browser.findElements(By.cssSelector("footer.footer")).size() > 0;
        Assertions.assertTrue(rodape);
    }

    @DisplayName("Validando icone de conclusão e remoção")
    @Test
    public void testCompletionAndRemovalButton(){
        String item1 = "Item 1";
        baseUtils.createTask(item1);

        WebElement primeiroItem = this.browser.findElement(By.cssSelector("ul.todo-list li"));
        WebElement radioButton = primeiroItem.findElement(By.className("toggle"));
        WebElement buttonX = primeiroItem.findElement(By.className("destroy"));

        Assertions.assertTrue(radioButton.isEnabled());
        Assertions.assertTrue(buttonX.isEnabled());
    }

    @DisplayName("Validar o icone de marcar todos no input")
    @Test
    public void testMarkAll(){

        String item1 = "Item 1";
        baseUtils.createTask(item1);

        WebElement markAll = this.browser.findElement(By.className("toggle-all"));
        Assertions.assertTrue(markAll.isEnabled());
    }

    @DisplayName("Validar que os filtros estão presentes no rodapé")
    @Test
    public void testFilters(){
        String item1 = "Item 1";
        baseUtils.createTask(item1);

        WebElement filtros = baseUtils.captureFilters();
        Assertions.assertTrue(filtros.isEnabled());
    }

    @DisplayName("Validar que o filtro all está selecionado")
    @Test
    public void testAllSelected(){
        
        baseUtils.createTask("Item 1");
        baseUtils.createTask("Item 2");


        baseUtils.validateFilters();
        
    }

}
