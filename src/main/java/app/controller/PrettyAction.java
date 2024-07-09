package app.controller;

import app.gui.MainFrame;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.ArrayList;

public class PrettyAction extends AbstractBPAction {

    public PrettyAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.ALT_MASK));
        //putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Pretty");
        putValue(SHORT_DESCRIPTION, "Pretty");
    }

    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        String tmp = MainFrame.getInstance().getTextPane().getText().toString();
        MainFrame.getInstance().getTextPane().setText("");

        String str = removeNewLine(tmp);

        str = str.toLowerCase();

        String[] splited = str.split("[\\s+]");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(splited));

        int size = list.size();
        int i = 0;

        while(i<size){
            String h = list.get(i);
            if(h.equals("right") && list.get(i+1).equals("join"))
            {
                list.set(i,"right join");
                h="right join";
                list.remove(i+1);
            }
            if(h.equals("order") && list.get(i+1).equals("by"))
            {
                list.set(i,"order by");
                h="order by";
                list.remove(i+1);
            }
            if(h.equals("group") && list.get(i+1).equals("by"))
            {
                list.set(i,"group by");
                h="group by";
                list.remove(i+1);
            }
            if(h.equals("insert") && list.get(i+1).equals("into"))
            {
                list.set(i,"insert into");
                h="insert into";
                list.remove(i+1);
            }
            if(h.equals("cross") && list.get(i+1).equals("join"))
            {
                list.set(i,"cross join");
                h="cross join";
                list.remove(i+1);
            }
            if(h.equals("select") || h.equals("from") || h.equals("delete") || h.equals("right join") || h.equals("on") || h.equals("update") ||
                    h.equals("where") || h.equals("like") || h.equals("asc") ||
                    h.equals("desc") || h.equals("order by") || h.equals("insert into") || h.equals("values") ||
                    h.equals("set") || h.equals("as") || h.equals("group by") || h.equals("having") || h.equals("join") || h.equals("cross join") ||
                    h.equals("using") || h.equals("and") || h.startsWith("sum") || h.startsWith("min") || h.startsWith("max") || h.startsWith("avg")
                    || h.startsWith("count("))
            {

                    if(h.equals("select")|| h.equals("from") || h.equals("where") || h.equals("group by") || h.equals("order by") || h.equals("right join")|| h.equals("on") || h.equals("join") || h.equals("values") ||
                            h.equals("insert into") || h.equals("cross join"))
                    {
                        h = h.toUpperCase();
                        if (i != 0){
                            appendToPane(MainFrame.getInstance().getTextPane(), "\n", Color.BLACK);
                        }

                    }

                    if(h.startsWith("sum") || h.startsWith("max") || h.startsWith("min") || h.startsWith("avg") || h.startsWith("count("))
                    {
                        String[] s = h.split("[()]");
                        appendToPane(MainFrame.getInstance().getTextPane(), s[0], Color.BLUE);
                        appendToPane(MainFrame.getInstance().getTextPane(), "("+s[1]+")", Color.BLACK);
                        appendToPane(MainFrame.getInstance().getTextPane(), " ", Color.BLACK);

                    }
                    else{
                        h = h.toUpperCase();
                        appendToPane(MainFrame.getInstance().getTextPane(), h, Color.BLUE);
                        appendToPane(MainFrame.getInstance().getTextPane(), " ", Color.BLACK);

                    }


            }
            else
            {
                appendToPane(MainFrame.getInstance().getTextPane(),h,Color.BLACK);
                appendToPane(MainFrame.getInstance().getTextPane()," ",Color.BLACK);
            }

            size = list.size();

            i++;
        }


    }

    public String removeNewLine(String input){
        String output = "";
        String subString[] = input.split("\\r?\\n|\\r");

        for (String s : subString){
            output = output + s;
        }

        return output;
    }


}