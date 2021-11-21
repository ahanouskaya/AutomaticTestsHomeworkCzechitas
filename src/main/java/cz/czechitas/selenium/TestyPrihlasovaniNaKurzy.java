package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver fireFox;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        fireFox = new FirefoxDriver();
        fireFox.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
    }

    @Test
    public void parentAlreadyHasAccountShouldBeAbleSignInToTheApp() {

        fireFox.navigate().to("https://cz-test-jedna.herokuapp.com/");

        signInAccount("dahanouski@gmail.com","Ovechkin8");
    }


    @Test
    public void parentShouldBeAbleChooseCourseSignInToTheAppAndRegisterHisChild() {

        fireFox.navigate().to("https://cz-test-jedna.herokuapp.com/");

        goToHTMLCourseInformation();
        goToHTMLCourseRegistrationForm();
        signInAccount("dahanouski@gmail.com","Ovechkin8");
        fillHTMLCourseRegistrationForm("Denis", "Ahanouski","24.07.2013","Do you have discount?");
        goToSeeListOfCourseAlreadyRegistrated();

        Assertions.assertNotNull(fireFox.findElement(By.xpath("//td[text()='Denis Ahanouski']")));
    }
    @Test
    public void parentShouldBeAbleSignInToTheAppThenChooseCourseAndRegisterHisChild() {

        fireFox.navigate().to("https://cz-test-jedna.herokuapp.com/");

        signInAccount("dahanouski@gmail.com","Ovechkin8");
        goToHome();
        goToHTMLCourseInformation();
        goToHTMLCourseRegistrationForm();
        fillHTMLCourseRegistrationForm("Denis", "Ahanouski","24.07.2013","Do you have discount?");
        goToSeeListOfCourseAlreadyRegistrated();

        Assertions.assertNotNull(fireFox.findElement(By.xpath("//td[text()='Denis Ahanouski']")));
    }

    public void fillHTMLCourseRegistrationForm(String firstName, String lastName,
                   String birthdayFormatMMpointDDpointYYYY, String addNoteText) {

        fieldDateCourseRegistration();
        fieldChildFirstNameCourseRegistration(firstName);
        fieldChildSecondNameCourseRegistration(lastName);
        fieldChildBirthdayCourseRegistration(birthdayFormatMMpointDDpointYYYY);
        selectPaymentCourseRegistration();
        fieldAddNoteCourseRegistration(addNoteText);
        clickGDPRCourseRegistration();
        clickRegisterButtonCourseRegistration();
    }
    public void goToHome() {
        WebElement clickHome = fireFox.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com']"));
        clickHome.click();
    }

    public void goToHTMLCourseRegistrationForm() {
        WebElement clickHTMLRegisterForm = fireFox.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1']"));
        clickHTMLRegisterForm.click();
    }

    public void goToHTMLCourseInformation() {
        WebElement clickHTMLInformation = fireFox.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu']"));
        clickHTMLInformation.click();
    }

    public void goToSeeListOfCourseAlreadyRegistrated() {
        WebElement controlListOfRegistration = fireFox.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/zaci']"));
        controlListOfRegistration.click();
    }

    public void clickRegisterButtonCourseRegistration() {
        WebElement clickRegisterButton = fireFox.findElement(By.xpath("//input[@value = 'Vytvořit přihlášku']"));
        clickRegisterButton.click();
    }

    public void clickGDPRCourseRegistration() {
        WebElement clickGDPR = fireFox.findElement(By.xpath("//label[text() = 'Souhlasím s všeobecnými podmínkami a zpracováním osobních údajů.']"));
        clickGDPR.click();
    }

    public void fieldAddNoteCourseRegistration(String addNoteText) {
        WebElement addNote = fireFox.findElement(By.id("note"));
        addNote.sendKeys(addNoteText);
    }

    public void selectPaymentCourseRegistration() {
        WebElement selectPayment = fireFox.findElement(By.className("custom-control-label"));
        selectPayment.click();
    }

    public void fieldChildBirthdayCourseRegistration(String birthdayFormatMMpointDDpointYYYY) {
        WebElement fieldChildrenBirthday = fireFox.findElement(By.id("birthday"));
        fieldChildrenBirthday.sendKeys(birthdayFormatMMpointDDpointYYYY);
    }

    public void fieldChildSecondNameCourseRegistration(String secondName) {
        WebElement fieldChildSecondName = fireFox.findElement(By.id("surname"));
        fieldChildSecondName.sendKeys(secondName);
    }

    public void fieldChildFirstNameCourseRegistration(String firstName) {
        WebElement fieldChildFirstName = fireFox.findElement(By.id("forename"));
        fieldChildFirstName.sendKeys(firstName);
    }

    public void fieldDateCourseRegistration() {
        WebElement selectDate = fireFox.findElement(By.className("filter-option-inner-inner"));
        selectDate.click();
        WebElement selectDateStep2 = fireFox.findElement(By.id("bs-select-1-3"));
        selectDateStep2.click();
    }

    public void signInAccount(String email, String password) {
        WebElement clickSignIn = fireFox.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/prihlaseni']"));
        clickSignIn.click();
        fieldEmail(email);
        fieldPassword(password);
        buttonSignIn();
    }

    public void buttonSignIn() {
        WebElement clickButtonSignIn = fireFox.findElement(By.xpath("//*[@class='btn btn-primary']"));
        clickButtonSignIn.click();
    }

    public void fieldEmail(String userName) {
        WebElement addUsername = fireFox.findElement(By.id("email"));
        addUsername.sendKeys(userName);
    }

    public void fieldPassword(String password) {
        WebElement addPassword = fireFox.findElement(By.id("password"));
        addPassword.sendKeys(password);
    }

    @AfterEach
    public void tearDown() {
        fireFox.close();
    }
}
