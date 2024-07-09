package app;

import app.gui.MainFrame;

public class Main {

    public static void main(String[] args) {
        //AppCore appCore = new AppCore();
        AppCore.getInstance().initialise();
        MainFrame mainFrame = MainFrame.getInstance();
       // mainFrame.setAppCore(appCore);
        mainFrame.setAppCore(AppCore.getInstance());

    }

}
