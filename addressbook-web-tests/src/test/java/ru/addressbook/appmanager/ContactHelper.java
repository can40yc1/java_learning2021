package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnToHome() {
        click(By.linkText("home"));
    }

    public void fillContactForm(ContactData contactData, boolean creating) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("phone2"), contactData.getSecondaryPhone());
        attach(By.name("photo"), contactData.getPhoto());


        if (creating) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeleting() throws InterruptedException {
        Thread.sleep(1000);
        wd.switchTo().alert().accept();
    }

    public void initContactModification(ContactData contact) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + contact.getId())).click();
    }

    public void submitContactpModification() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        NavigationHelper navigationHelper = new NavigationHelper(wd);
        GroupHelper groupHelper = new GroupHelper(wd);
        navigationHelper.groupPage();
        if (!groupHelper.isThereAGroupWithName(contactData.getGroup())) {
            groupHelper.create(new GroupData().withName(contactData.getGroup()));
        }
        navigationHelper.homePage();
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        returnToHome();
    }

    public void delete(ContactData contact) throws InterruptedException {
        selectContactById(contact.getId());
        deleteSelectedContact();
        confirmContactDeleting();
        returnToHome();
    }

    public void modify(ContactData contact) {
        initContactModification(contact);
        fillContactForm(contact, false);
        submitContactpModification();
        returnToHome();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void getContactDetails(ContactData contact) {
        WebElement element = wd.findElement(By.xpath(String.format("//tr[@name=\"entry\"]//td//input[@id=\"" + contact.getId() + "\"]")));
        String allEmails = element.findElement(By.xpath(".//..//..//td[5]")).getText();
        String allPhones = element.findElement(By.xpath(".//..//..//td[6]")).getText();
        contact.withAllPhones(allPhones)
                .withAllEmails(allEmails);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact);
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String secondaryPhone = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail1(email1)
                .withEmail2(email2)
                .withEmail3(email3)
                .withSecondaryPhone(secondaryPhone);
    }
}
