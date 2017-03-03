package GraphicalInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Tymek on 03.03.2017.
 */
public class TableWindow extends Window {
    private DefaultTableModel tableModel;



    void setTableModel(JTable table, String[] colNames){
        DefaultTableModel model = new DefaultTableModel();

        for (String name : colNames) {
            model.addColumn(name);
        }

        this.tableModel = model;

        table.setModel(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
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

}
