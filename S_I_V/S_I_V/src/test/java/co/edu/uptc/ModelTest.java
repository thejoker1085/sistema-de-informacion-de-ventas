package co.edu.uptc;

import co.edu.uptc.model.Model;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.MovementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
    }

    @Test
    void addPersonIncreasesPersonList() {
        model.addPerson("Juan", "Perez", Gender.MASCULINE, LocalDate.of(2000, 1, 1));
        assertEquals(1, model.getAllPersons().size());
    }

    @Test
    void personQueuePreservesFifoOrder() {
        model.addPerson("Ana", "Lopez", Gender.FEMININE, LocalDate.of(1995, 3, 10));
        model.addPerson("Luis", "Garcia", Gender.MASCULINE, LocalDate.of(1998, 7, 22));
        assertEquals("Ana", model.getAllPersons().get(0).getFirstName());
    }

    @Test
    void addProductIncreasesProductList() {
        model.addProduct("ARROZ", MeasurementUnit.KILO, 2500.0);
        assertEquals(1, model.getAllProducts().size());
    }

    @Test
    void productDescriptionIsUppercaseByDefault() {
        model.addProduct("arroz integral", MeasurementUnit.KILO, 3000.0);
        assertEquals("ARROZ INTEGRAL", model.getAllProducts().get(0).getDescription());
    }

    @Test
    void accountingBalanceCalculatesCorrectly() {
        model.addAccountingMovement("Venta", MovementType.INCOME, 1000.0, LocalDateTime.now());
        model.addAccountingMovement("Gasto", MovementType.EXPENSE, 300.0, LocalDateTime.now());
        assertEquals(700.0, model.getAccountingBalance(), 0.001);
    }

    @Test
    void accountingStackPreservesLifoOrder() {
        model.addAccountingMovement("Primero", MovementType.INCOME, 100.0, LocalDateTime.now());
        model.addAccountingMovement("Segundo", MovementType.INCOME, 200.0, LocalDateTime.now());
        assertEquals("Segundo", model.getAllAccountingMovements().get(0).getDescription());
    }
}
