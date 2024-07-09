package app.controller;

import app.gui.MainFrame;
import app.model.CSVFormat;
import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportAction extends AbstractBPAction {

    public ExportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.ALT_MASK));
        //putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (MainFrame.getInstance().getJTable().getRowCount() != 0 && MainFrame.getInstance().getJTable().getColumnCount() != 0) {

            JFileChooser Fchoose = new JFileChooser();
            Fchoose.setFileFilter(new CSVFormat());

            File file = null;
            if (Fchoose.showSaveDialog(MainFrame.getInstance()) == Fchoose.APPROVE_OPTION) ;
            {
                file = new File(Fchoose.getSelectedFile().toString());
            }


            String[] headers = new String[MainFrame.getInstance().getJTable().getColumnCount()];
            for(int i = 0; i< MainFrame.getInstance().getJTable().getColumnCount(); i++){
                headers[i] = MainFrame.getInstance().getJTable().getColumnName(i);
            }

            try {
                FileWriter fw = new FileWriter(file);

                CSVWriter writer = new CSVWriter(fw, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

                writer.writeNext(headers);
                String[] data = new String[MainFrame.getInstance().getJTable().getColumnCount()];
                for(int j = 0; j <MainFrame.getInstance().getJTable().getRowCount(); j++ ) {
                    for (int i = 0; i < MainFrame.getInstance().getJTable().getColumnCount(); i++) {
                        if (MainFrame.getInstance().getJTable().getValueAt(j, i) != null) {
                            data[i] = MainFrame.getInstance().getJTable().getValueAt(j, i).toString();
                        }
                    }
                    writer.writeNext(data);
                }

                writer.close();
            } catch (IOException l) {
                l.printStackTrace();
            }
        }


    }
}