package validator.rules.rules;

import validator.rules.AbstractRule;

import java.util.ArrayList;

public class Promenljive extends AbstractRule {

    public Promenljive(String message) {
        super(message);
        this.setMessage("Promneljive nisu iskorsicene");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {



        if(query.get(0).equals("create") && query.get(2).equals("replace"))
        {
            if(query.get(4).contains("("))
            {
                String[] str = query.get(4).split("[() ,]");

                for(int i = 5; i<query.size(); i++)
                {
                    for(int j =1; j<str.length;j++)
                    {
                        if(query.get(i).equals(str[j]))
                        {

                        }
                    }
                }

            }
        }


        return true;
    }

}