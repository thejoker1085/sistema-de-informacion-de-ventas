package co.edu.uptc.config;

import co.edu.uptc.model.DataStructureStrategyFactory;
import co.edu.uptc.model.StrategyBasedCollectionManager;
import co.edu.uptc.model.persistence.CsvReader;
import co.edu.uptc.model.persistence.CsvWriter;
import co.edu.uptc.model.service.AccountingService;
import co.edu.uptc.model.service.PersonService;
import co.edu.uptc.model.service.ProductService;
import co.edu.uptc.model.validation.ValidationRule;
import co.edu.uptc.model.validation.Validator;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.pojo.Product;

import java.util.List;

public class BusinessServiceFactory {

    private final CsvWriter csvWriter = new CsvWriter("data");
    private final CsvReader csvReader = new CsvReader("data");

    public BusinessServiceFactory() {
        ConfigLoader.getConfigProperty("app.language");
    }

    public PersonService createPersonService() {
        int nameMinLen = ConfigLoader.getIntProperty("person.name.min.length");
        int nameMaxLen = ConfigLoader.getIntProperty("person.name.max.length");
        int lastNameMinLen = ConfigLoader.getIntProperty("person.lastname.min.length");
        int lastNameMaxLen = ConfigLoader.getIntProperty("person.lastname.max.length");
        
        var validator = new Validator<>(List.of(
                new ValidationRule<Person>(
                    p -> p.getFirstName().length() >= nameMinLen && p.getFirstName().length() <= nameMaxLen,
                    "error.person.name.length"),
                new ValidationRule<Person>(
                    p -> p.getLastName().length() >= lastNameMinLen && p.getLastName().length() <= lastNameMaxLen,
                    "error.person.lastname.length"),
                new ValidationRule<Person>(p -> p.getAge() >= 0, "msg.error.age")
        ));
        var manager = new StrategyBasedCollectionManager<Person>(
                DataStructureStrategyFactory.queueAppendStrategy(), DataStructureStrategyFactory.queueDequeueStrategy()
        );
        return new PersonService(manager, validator, csvWriter);
    }

    public ProductService createProductService() {
        double priceMax = ConfigLoader.getDoubleProperty("product.price.max");
        int descMinLen = ConfigLoader.getIntProperty("product.description.min.length");
        int descMaxLen = ConfigLoader.getIntProperty("product.description.max.length");
        
        var validator = new Validator<>(List.of(
                new ValidationRule<Product>(p -> p.getPrice() > 0, "msg.error.price"),
                new ValidationRule<Product>(p -> p.getPrice() <= priceMax, "error.product.price.max"),
                new ValidationRule<Product>(
                    p -> p.getDescription().length() >= descMinLen && p.getDescription().length() <= descMaxLen,
                    "error.product.description.length")
        ));
        var manager = new StrategyBasedCollectionManager<Product>(
                DataStructureStrategyFactory.stackPushStrategy(), DataStructureStrategyFactory.queueDequeueStrategy()
        );
        return new ProductService(manager, validator, csvWriter);
    }

    public AccountingService createAccountingService() {
        var validator = new Validator<>(List.of(
                new ValidationRule<AccountingMovement>(m -> m.getAmount() > 0, "msg.error.amount")
        ));
        var manager = new StrategyBasedCollectionManager<AccountingMovement>(
                DataStructureStrategyFactory.queueAppendStrategy(), DataStructureStrategyFactory.listRemoveLastStrategy()
        );
        return new AccountingService(manager, validator, csvWriter, csvReader); 
    }
}