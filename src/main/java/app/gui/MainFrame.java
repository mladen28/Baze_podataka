package app.gui;

import app.AppCore;
import app.controller.ActionManager;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import tree.implementation.SelectionListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

@Data
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JTable jTable;
    private JScrollPane jsp;
    private JTree jTree;
    private JPanel left;
    private JToolBar toolBar;
    private ActionManager actionManager;
    private TextArea input;
    private JPanel panel;
    private BorderLayout box;
    private JTextPane textPane;

    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {

        actionManager = new ActionManager();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        toolBar = new Toolbar();
        this.add(toolBar,BorderLayout.NORTH);
        toolBar.setBackground(Color.GRAY);

//        input = new TextArea();
//        input.setSize(100,30);

        textPane = new JTextPane();


        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        jTable.setFillsViewportHeight(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        //panel.add(input);

        panel.add(new JScrollPane(textPane));
        panel.add(new JScrollPane(jTable));

        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
        initialiseTree();
    }

    private void initialiseTree() {
        DefaultTreeModel defaultTreeModel = appCore.loadResource();
        jTree = new JTree(defaultTreeModel);
        jTree.addTreeSelectionListener(new SelectionListener());
        jsp = new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        left = new JPanel(new BorderLayout());
        left.add(jsp, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
        pack();
    }


    @Override
    public void update(Notification notification) {


    }
}
