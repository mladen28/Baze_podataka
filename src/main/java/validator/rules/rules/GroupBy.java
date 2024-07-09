package validator.rules.rules;

import validator.rules.AbstractRule;

import java.util.ArrayList;

public class GroupBy extends AbstractRule {

    public GroupBy(String message) {
        super(message);
        this.setMessage("Nije strani kljuc");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {




        return true;
    }

}