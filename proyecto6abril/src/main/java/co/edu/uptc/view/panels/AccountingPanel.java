package co.edu.uptc.view.panels;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.pojo.AccountingMovement;
import co.edu.uptc.pojo.MovementType;
import co.edu.uptc.utils.ComponentFactory;
import co.edu.uptc.utils.DynamicTableModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class AccountingPanel extends JPanel {

    private final PresenterInterface presenter;
    private final DynamicTableModel<AccountingMovement> tableModel;
    private JTextField txtDesc, txtAmount;
    private JComboBox<MovementType> cbType;
    private JLabel lblBalance;
    private Runnable backAction;

    public AccountingPanel(PresenterInterface presenter, Runnable backAction) {
        this.presenter = presenter;
        this.backAction = backAction;
        this.tableModel = buildTableModel();
        setLayout(new BorderLayout(10, 10));
        add(buildForm(), BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);
    }

    private DynamicTableModel<AccountingMovement> buildTableModel() {
        return new DynamicTableModel<>(
                List.of("Descripción", "Tipo", "Monto", "Fecha"),
                List.of(AccountingMovement::getDescription, 
                        AccountingMovement::getType, AccountingMovement::getAmount, AccountingMovement::getFormattedDateTime)
        );
    }

    private JPanel buildForm() {
        JPanel p = ComponentFactory.createGridPanel(2, 4, 5, 5);
        txtDesc = ComponentFactory.createTextField(10);
        txtAmount = ComponentFactory.createTextField(10);
        cbType = new JComboBox<>(MovementType.values());
        fillForm(p);
        return p;
    }

    private void fillForm(JPanel p) {
        p.add(ComponentFactory.createLabel("Descripción:")); p.add(txtDesc);
        p.add(ComponentFactory.createLabel("Tipo:")); p.add(cbType);
        p.add(ComponentFactory.createLabel("Monto:")); p.add(txtAmount);
        p.add(ComponentFactory.createButton("Adicionar movimiento", e -> save()));
    }

    private JPanel buildFooter() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        p.add(ComponentFactory.createButton("Listar movimientos", e -> presenter.listAccountingMovements()));
        p.add(ComponentFactory.createButton("Exportar a CSV", e -> presenter.exportAccountingMovements()));
        p.add(ComponentFactory.createButton("Regresar", e -> backAction.run()));
        lblBalance = ComponentFactory.createLabel("Balance Total: $0.00");
        lblBalance.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblBalance);
        return p;
    }

    private void save() {
        try {
            validateAndAddMovement();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Monto numérico inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateAndAddMovement() {
        double amount = Double.parseDouble(txtAmount.getText());
        String description = txtDesc.getText();
        MovementType type = (MovementType) cbType.getSelectedItem();
        presenter.addAccountingMovement(description, type, amount, LocalDateTime.now());
        clearFields();
        presenter.listAccountingMovements();
    }

    private void clearFields() {
        txtDesc.setText("");
        txtAmount.setText("");
    }

    public void updateData(List<AccountingMovement> movements, double balance) {
        tableModel.setData(movements);
        lblBalance.setText(String.format("Balance Total: $%.2f", balance));
    }
}