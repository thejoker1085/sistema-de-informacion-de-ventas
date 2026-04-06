package co.edu.uptc.view.panels;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.pojo.MeasurementUnit;
import co.edu.uptc.pojo.Product;
import co.edu.uptc.utils.ComponentFactory;
import co.edu.uptc.utils.DynamicTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {

    private final PresenterInterface presenter;
    private final DynamicTableModel<Product> tableModel;
    private JTextField txtDesc, txtPrice;
    private JComboBox<MeasurementUnit> cbUnit;
    private Runnable backAction;

    public ProductPanel(PresenterInterface presenter, Runnable backAction) {
        this.presenter = presenter;
        this.backAction = backAction;
        this.tableModel = buildTableModel();
        setLayout(new BorderLayout(10, 10));
        add(buildForm(), BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);
    }

    private DynamicTableModel<Product> buildTableModel() {
        return new DynamicTableModel<>(
                List.of("ID", "Descripción", "Unidad", "Precio"),
                List.of(Product::getId, Product::getDescription, Product::getUnit, Product::getPrice)
        );
    }

    private JPanel buildForm() {
        JPanel p = ComponentFactory.createGridPanel(2, 4, 5, 5);
        txtDesc = ComponentFactory.createTextField(10);
        txtPrice = ComponentFactory.createTextField(10);
        cbUnit = new JComboBox<>(MeasurementUnit.values());
        fillForm(p);
        return p;
    }

    private void fillForm(JPanel p) {
        p.add(ComponentFactory.createLabel("Descripción:")); p.add(txtDesc);
        p.add(ComponentFactory.createLabel("Unidad:")); p.add(cbUnit);
        p.add(ComponentFactory.createLabel("Precio:")); p.add(txtPrice);
        p.add(ComponentFactory.createButton("Adicionar", e -> save()));
        p.add(ComponentFactory.createButton("Retirar", e -> remove()));
    }

    private void save() {
        try {
            double price = Double.parseDouble(txtPrice.getText());
            String description = txtDesc.getText();
            MeasurementUnit unit = (MeasurementUnit) cbUnit.getSelectedItem();
            presenter.addProduct(description, unit, price);
            txtDesc.setText("");
            txtPrice.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remove() {
        try {
            presenter.removeProduct();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel buildFooter() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        p.add(ComponentFactory.createButton("Exportar a CSV", e -> presenter.exportProducts()));
        p.add(ComponentFactory.createButton("Regresar", e -> backAction.run()));
        return p;
    }

    public void updateData(List<Product> products) {
        tableModel.setData(products);
    }
}