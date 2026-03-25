package co.edu.uptc.view.menu;

import co.edu.uptc.config.MessageProvider;
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
        boolean active = true;
        while (active) {
            printMenu();
            int option = reader.readOption(1, 5);
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> presenter.requestRemovePerson();
                case 3 -> presenter.requestListPersons();
                case 4 -> presenter.requestExportPersons();
                case 5 -> active = false;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n" + MessageProvider.get("menu.persons.title"));
        System.out.println(MessageProvider.get("menu.persons.add"));
        System.out.println(MessageProvider.get("menu.persons.remove"));
        System.out.println(MessageProvider.get("menu.persons.list"));
        System.out.println(MessageProvider.get("menu.persons.export"));
        System.out.println(MessageProvider.get("menu.persons.back"));
    }

    private void handleAdd() {
        String firstName = reader.readString("prompt.firstname");
        String lastName = reader.readString("prompt.lastname");
        var gender = reader.readGender();
        var birthDate = reader.readDate();
        presenter.requestAddPerson(firstName, lastName, gender, birthDate);
    }
}