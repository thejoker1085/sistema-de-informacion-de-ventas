package co.edu.uptc;

import co.edu.uptc.model.Model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Example test class for the Model.
 */
class ModelTest {

    @Test
    void testModelCreation() {
        Model model = new Model();
        assertNotNull(model, "Model should not be null");
    }
}
