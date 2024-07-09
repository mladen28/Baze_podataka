package validator;

import app.gui.MainFrame;
import validator.rules.AbstractRule;
import validator.rules.JsonImpl;
import validator.rules.JsonRep;
import validator.rules.RuleInit;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ValidatorImpl implements Validator{

    private String warning;
    private boolean validation;
    private ArrayList<AbstractRule> rules;
    private int counter = 1;
    private RuleInit ruleInit;
    private JsonRep rep;
    //private String query;


    public ValidatorImpl(){
        this.warning = "";
        this.validation = true;
        rules = new ArrayList<>();
        ruleInit = new RuleInit();
        rules.addAll(ruleInit.initialiseRules());
        rep = new JsonImpl();

    }

    public void validateQuery(String input){

        validation =true;

        String tmp = removeNewLine(input);

      //  System.out.println(tmp);

        String[] split = tmp.split("[\\s+]");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(split));

        int size = split.length;





        for(int i = 0; i<list.size(); i++) {

            String s = list.get(i);
            if(s.equals("select") || s.equals("from") || s.equals("where") || s.equals("using")|| s.equals("join") )
            {
                list.set(i,s.toUpperCase());
            }
            if(s.equalsIgnoreCase("right") && list.get(i+1).equals("join"))
            {
                list.set(i,"RIGHT JOIN");
                list.remove(i+1);
            }
            if(s.equalsIgnoreCase("insert") && list.get(i+1).equals("into"))
            {
                list.set(i,"INSERT INTO");
                list.remove(i+1);
            }
            if(s.equalsIgnoreCase("group") && list.get(i+1).equalsIgnoreCase("by"))
            {
                list.set(i,"GROUP BY");
                list.remove(i+1);
            }
            if(s.startsWith("\""))
            {

                String str = s;
                int j = 1;
                boolean flag = true;

                while(flag)
                {
                    System.out.println(list.get(i+j));
                    str = str + " " + list.get(i+j);
                    list.remove(i+j);
                    j++;
                    if(!list.get(i+j).endsWith("\""))
                        flag = false;
                }
                list.set(i, str);

            }


        }
        String error;

        for(String r: list)
        {
           // System.out.println(r);

        }
        for(AbstractRule rule : rules){
            if(!rule.examineQuery(list)){
                //System.out.println(validation);
                validation = false;
                error = rep.doError(rule.getClass().toString());
                warning =  warning + counter++ + ". " + rule.getMessage() + "\n";
                System.out.printf(error);
            }
        }



        String f = "";
        for(int i = 0; i<size; i++) {

            f = f + split[i] +" ";
           // System.out.println(f);
        }
        System.out.println(validation);


        if(validation == true) {
            MainFrame.getInstance().getAppCore().readDataFromTable(input);
        } else {
            JOptionPane.showMessageDialog(null, warning,"Upozorenje",JOptionPane.WARNING_MESSAGE);

            //System.out.println(error,re);
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
