package co.edu.uptc.utils;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DynamicTableModel<T> extends AbstractTableModel {

    private final List<String> columns;
    private final List<Function<T, Object>> extractors;
    private List<T> data = new ArrayList<>();

    public DynamicTableModel(List<String> columns, List<Function<T, Object>> extractors) {
        this.columns = columns;
        this.extractors = extractors;
    }

    public void setData(List<T> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return columns.size(); }
    @Override public String getColumnName(int col) { return columns.get(col); }
    
    @Override 
    public Object getValueAt(int row, int col) {
        return extractors.get(col).apply(data.get(row));
    }
}