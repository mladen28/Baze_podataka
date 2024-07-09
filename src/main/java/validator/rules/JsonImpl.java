package validator.rules;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import utils.Constants;
import validator.rules.JsonRep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JsonImpl implements JsonRep {

    @Override
    public String doError(String thatclass) {
        //TODO IMPLEMENT DIS
        File f=new File("src/main/java/utils/rules.json");
        String sx="ne radi";
        try {
            Scanner s=new Scanner(f);

            Object o= JSONValue.parse(s.nextLine());
            JSONObject ar=(JSONObject)o;
            JSONObject jow=   (JSONObject)(ar.get(thatclass));
            sx="\nError name: "+jow.get(Constants.RULE_NAME)+"\n Description= "+jow.get(Constants.DESC)+
                    "\n hint:  "+jow.get(Constants.HINT);

            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sx;
    }
}