import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.*;
import jxl.*;
import jxl.write.*;

public class ExcelExporter {

    void fillData(JTable table, File file) {

        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 1, 
                            model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dataFill() {
        String[][] data = {{"Housewares", "Rs.1275.00"},
            {"Pets", "Rs.125.00"}, {"Electronics", "Rs.2533.00"},
            {"Menswear", "Rs.497.00"}
        };
        int numberCol = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Headers:"));
        String[] headers = new String[numberCol];
        for(int i=1; i<= numberCol;i++){
            headers[i-1] = JOptionPane.showInputDialog("Enter the "+i+" Header Name:");
        }       
        
        JFrame frame = new JFrame("JTable to Excel");
        DefaultTableModel model = new DefaultTableModel(data, headers);
        final JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        JButton export = new JButton("Export to Excel");
        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    ExcelExporter exp = new ExcelExporter();
                    exp.fillData(table, new File("C:\\Users\\kalra\\Desktop\\result.xls"));
                    JOptionPane.showMessageDialog(null, "Data saved at " +
                            "'C:\\Users\\kalra\\Desktop\\result.xls' successfully", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.getContentPane().add("Center", scroll);
        frame.getContentPane().add("South", export);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}