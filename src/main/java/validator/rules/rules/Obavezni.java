package validator.rules.rules;

import validator.rules.AbstractRule;

import java.util.ArrayList;

public class Obavezni extends AbstractRule {

    public Obavezni(String message) {
        super(message);
        this.setMessage("Fale obavezne kljucne reci");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {

        int l = query.size();

        boolean select = false;
        boolean from = false;
        String s = "";

        for(int i = 0; i<l;i++)
        {
            s= query.get(i);
            if(s.equals("SELECT"))
                select = true;
            if(s.equals("FROM"))
                from = true;

        }

        if(select && from)
            return true;

        return false;
    }

}