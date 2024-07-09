package app.controller;

import app.AppCore;
import app.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RunAction extends AbstractBPAction {

    public RunAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.ALT_MASK));
        //putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Run");
        putValue(SHORT_DESCRIPTION, "Run");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String input = MainFrame.getInstance().getTextPane().getText();
        //System.out.println(input);
        AppCore.getInstance().getValidator().validateQuery(input);


    }
}