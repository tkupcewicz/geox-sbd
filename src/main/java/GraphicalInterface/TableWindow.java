package GraphicalInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Tymek on 03.03.2017.
 */
public class TableWindow extends Window {
    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel2;



    void setTableModel(JTable table, String[] colNames){
        DefaultTableModel model = new DefaultTableModel();

        for (String name : colNames) {
            model.addColumn(name);
        }

        this.tableModel = model;

        table.setModel(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
    }

    void setTableModel2(JTable table, String[] colNames){
        DefaultTableModel model = new DefaultTableModel();
        System.out.println(colNames);
        for (String name : colNames) {
            model.addColumn(name);
        }

        this.tableModel2 = model;

        table.setModel(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
    }

    void addRow(Object[] row){
        this.tableModel.addRow(row);
    }

    void setRows(ArrayList<Object[]> rows){

        this.tableModel.setRowCount(0);

        for (Object[] row : rows){
            addRow(row);
        }
    }

    void addRow2(Object[] row){
        this.tableModel2.addRow(row);
    }

    void setRows2(ArrayList<Object[]> rows){

        this.tableModel2.setRowCount(0);

        for (Object[] row : rows){
            addRow2(row);
        }
    }

    void setOrderRows(ArrayList<Object[]> rows){
        for (Object[] row : rows){
            addRow2(new Object[]{row[0], row[1], row[2]});
        }
    }


    void setProductRows(ArrayList<Object[]> rows){
        for (Object[] row : rows){
            addRow(new Object[]{row[0], row[1], row[2], row[4]});
        }
    }

    void clearProductRows(){
        int rowCount = this.tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            this.tableModel.removeRow(i);
        }
        this.tableModel.setRowCount(0);
    }

    void updateProductsTable(){
        tableModel.fireTableDataChanged();
    }

    void updateOrdersTable(){
        tableModel2.fireTableDataChanged();
    }

}
