package app.controller;

import app.gui.MainFrame;
import app.model.CSVFormat;
import com.opencsv.CSVReader;
import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import tree.TreeItem;

import java.awt.event.ActionEvent;


import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static javax.swing.Action.*;

public class BulkImportAction extends AbstractBPAction {

    public BulkImportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.ALT_MASK));
        //putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Bulk Import");
        putValue(SHORT_DESCRIPTION, "Bulk Import");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        TreePath path = ((JTree) MainFrame.getInstance().getJTree()).getSelectionPath();

        TreeItem node = (TreeItem) path.getLastPathComponent();
        DBNodeComposite model = (DBNodeComposite) node.getDbNode();


        JFileChooser Fcoose = new JFileChooser();
        Fcoose.setFileFilter(new CSVFormat());

        File file = null;
        if(Fcoose.showSaveDialog(MainFrame.getInstance()) == Fcoose.APPROVE_OPTION)
            file = new File(Fcoose.getSelectedFile().toString());
        else return;

        String upit = "INSERT INTO %s(%s) VALUES (%s)";

        try {
            FileReader fr = new FileReader(file);
            CSVReader csvR = new CSVReader(fr);

            String [] next;
            String header = "";
            String [] headers = new String[model.getChildren().size()];

            int i = 0;

            next = csvR.readNext();

            for(String s: next) {
                header = header + s + ", ";
                headers[i++] = s;
            }

            System.out.println(header);

            for (int j = 0; j<headers.length; j++) {
                System.out.println(headers[j]);
            }

            StringBuilder sb = new StringBuilder(header);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);

            while((next = csvR.readNext()) != null) {
                String data = "";
                i = 0;
                for(String s: next) {
                    Attribute a = (Attribute) model.getChildByName(headers[i++]);
                    if (a.getAttributeType().equals(AttributeType.CHAR) || a.getAttributeType().equals(AttributeType.VARCHAR) || a.getAttributeType().equals(AttributeType.DATE)) {
                        s = "'" + s + "'";
                    }
                    data += s + ",";
                }

                StringBuilder sb1 = new StringBuilder(data);
                sb1.deleteCharAt(sb1.length() - 1);
                System.out.println(String.format(upit, model.toString(), sb, sb1));
                MainFrame.getInstance().getAppCore().citaj(model.toString(), String.format(upit, model.toString(), sb, sb1));

            }

        }
         catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}