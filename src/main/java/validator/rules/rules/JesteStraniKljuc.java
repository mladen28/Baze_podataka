package validator.rules.rules;

import validator.rules.AbstractRule;

import java.util.ArrayList;

public class JesteStraniKljuc extends AbstractRule{

    public JesteStraniKljuc(String message) {
        super(message);
        this.setMessage("Nije strani kljuc");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {


        return true;
    }

}