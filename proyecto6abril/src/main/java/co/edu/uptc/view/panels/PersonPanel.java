package co.edu.uptc.view.panels;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.pojo.Gender;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.utils.ComponentFactory;
import co.edu.uptc.utils.DynamicTableModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PersonPanel extends JPanel {

    private final PresenterInterface presenter;
    private final DynamicTableModel<Person> tableModel;
    private JTextField txtFirstName, txtLastName;
    private JComboBox<Gender> cbGender;
    private JSpinner spinDate;
    private Runnable backAction;

    public PersonPanel(PresenterInterface presenter, Runnable backAction) {
        this.presenter = presenter;
        this.backAction = backAction;
        this.tableModel = buildTableModel();
        setLayout(new BorderLayout(10, 10));
        add(buildForm(), BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);
    }

    private DynamicTableModel<Person> buildTableModel() {
        return new DynamicTableModel<>(
                List.of("ID", "Nombre", "Apellido", "Género", "Fecha de Nacimiento"),
                List.of(Person::getId, Person::getFirstName, Person::getLastName, Person::getGender, Person::getBirthDate)
        );
    }

    private JPanel buildForm() {
        JPanel p = ComponentFactory.createGridPanel(2, 4, 5, 5);
        txtFirstName = ComponentFactory.createTextField(10);
        txtLastName = ComponentFactory.createTextField(10);
        cbGender = new JComboBox<>(Gender.values());
        spinDate = new JSpinner(new SpinnerDateModel());
        fillForm(p);
        return p;
    }

    private void fillForm(JPanel p) {
        p.add(ComponentFactory.createLabel("Nombre:")); p.add(txtFirstName);
        p.add(ComponentFactory.createLabel("Apellido:")); p.add(txtLastName);
        p.add(ComponentFactory.createLabel("Género:")); p.add(cbGender);
        p.add(ComponentFactory.createLabel("Fecha Nacimiento:")); p.add(spinDate);
        p.add(ComponentFactory.createButton("Adicionar", e -> save()));
        p.add(ComponentFactory.createButton("Retirar", e -> remove()));
    }

    private JPanel buildFooter() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        p.add(ComponentFactory.createButton("Exportar a CSV", e -> presenter.exportPersons()));
        p.add(ComponentFactory.createButton("Regresar", e -> backAction.run()));
        return p;
    }

    private void save() {
        try {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            Gender gender = (Gender) cbGender.getSelectedItem();
            LocalDate date = LocalDate.now();
            presenter.addPerson(firstName, lastName, gender, date);
            txtFirstName.setText("");
            txtLastName.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remove() {
        try {
            presenter.removePerson();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData(List<Person> persons) {
        tableModel.setData(persons);
    }
}
