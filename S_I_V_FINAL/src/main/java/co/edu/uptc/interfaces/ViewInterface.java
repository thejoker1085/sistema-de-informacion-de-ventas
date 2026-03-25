package co.edu.uptc.interfaces;

import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;

import java.util.List;

public interface ViewInterface {
    void setPresenter(PresenterInterface presenter);
    void start();
    void showSuccess(String message);
    void showError(String message);
    void displayPersons(List<Person> persons);
    void displayProducts(List<Product> products);
    void displayAccountingMovements(List<AccountingMovement> movements, double balance);
}