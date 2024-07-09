package app.controller;

public class ActionManager {

    private BulkImportAction bulkImportAction;
    private ExportAction exportAction;
    private PrettyAction prettyAction;
    private RunAction runAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        bulkImportAction = new BulkImportAction();
        exportAction = new ExportAction();
        prettyAction = new PrettyAction();
        runAction = new RunAction();
    }


    public BulkImportAction getBulkImportAction() {
        return bulkImportAction;
    }

    public void setDeafultAction(BulkImportAction bulkImportAction) {
        this.bulkImportAction = bulkImportAction;
    }

    public ExportAction getExportAction() {
        return exportAction;
    }

    public void setExportAction(ExportAction exportAction) {
        this.exportAction = exportAction;
    }

    public PrettyAction getPrettyAction(){
        return prettyAction;
    }

    public void setPrettyAction(PrettyAction prettyAction) {
        this.prettyAction = prettyAction;
    }

    public RunAction getRunAction(){
        return runAction;
    }

    public void setRunAction(RunAction runAction) {
        this.runAction = runAction;
    }


}
