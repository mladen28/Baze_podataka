package validator.rules.rules;

import validator.rules.AbstractRule;

import java.util.ArrayList;

public class WhereAgr extends AbstractRule {


    public WhereAgr(String message) {
        super(message);
        this.setMessage("Ne moze funkcija agregacije u Where izrzu");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {

        int size = query.size();
        int num=0;
        String s;

        for(int i = 0; i<size; i++) {

            s = query.get(i);
            if (s.equalsIgnoreCase("WHERE")) {
                num = i;
                break;
            }

        }

        if(num>2) {

            for (int k = num; k < size; k++) {

                if (query.get(k).startsWith("sum") || query.get(k).startsWith("avg") || query.get(k).startsWith("min") || query.get(k).startsWith("max") || query.get(k).startsWith("count")) {
                    return false;

                }

            }
        }


        return true;
    }
}
