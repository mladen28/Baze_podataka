package validator.rules;

import validator.rules.rules.*;


import java.util.ArrayList;

public class RuleInit {


    private ArrayList<AbstractRule> rules = new ArrayList<>();
    String msg;


    public ArrayList initialiseRules(){
        //rules.add(new KolonePostoje(msg));
        rules.add(new JesteStraniKljuc(msg));
        rules.add(new WhereAgr(msg));
        rules.add(new RedosledIspravan(msg));
        rules.add(new GroupBy(msg));
        rules.add(new Promenljive(msg));
        rules.add(new Obavezni(msg));


        return rules;
    }



}