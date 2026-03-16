package co.edu.uptc.view.menu;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.view.InputReader;

public class PersonMenu {

    private final PresenterInterface presenter;
    private final InputReader reader;

    public PersonMenu(PresenterInterface presenter, InputReader reader) {
        this.presenter = presenter;
        this.reader = reader;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Persons Menu ---");
            System.out.println("  1. Add person");
            System.out.println("  2. List persons");
            System.out.println("  3. Export to CSV");
            System.out.println("  4. Back");
            int option = reader.readInt("  Select", 1, 4);
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> presenter.requestListPersons();
                case 3 -> presenter.requestExportPersons();
                case 4 -> running = false;
            }
        }
    }

    private void handleAdd() {
        String firstName = reader.readString("  First name");
        String lastName  = reader.readString("  Last name");
        var gender    = reader.readGender();
        var birthDate = reader.readDate("  Birth date");
        presenter.requestAddPerson(firstName, lastName, gender, birthDate);
    }
}
