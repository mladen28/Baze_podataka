package utils;

import org.json.simple.JSONObject;
import validator.rules.rules.KolonePostoje;
import validator.rules.rules.Obavezni;
import validator.rules.rules.RedosledIspravan;
import validator.rules.rules.WhereAgr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Json {

    public Json(){
        initRules();
    }

    private void initRules() {
        JSONObject jo = new JSONObject();
        jo.put(KolonePostoje.class, initRuleKolone());
        jo.put(WhereAgr.class, initWhereAgr());
        jo.put(RedosledIspravan.class, initRedosledIspravan());
        jo.put(Obavezni.class, initObavezni());


        File f = new File("src/main/java/utils/rules.json");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(jo.toJSONString());


            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject initRuleKolone() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Incorrect Row or Table");
        jsonObject.put(Constants.HINT, "Check atribute and entity names");
        jsonObject.put(Constants.DESC, "All atrributes and entities must exist in the data base");

        return jsonObject;

    }

    private JSONObject initWhereAgr() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Fukcija agregacije u WHERE izrazu");
        jsonObject.put(Constants.HINT, "Uklonite funkciju agregacije iz WHERE izraza");
        jsonObject.put(Constants.DESC, "Funkcija agregacije ne sme da postoji u WHERE izrazu");

        return jsonObject;

    }

    private JSONObject initRedosledIspravan() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Nepravilan redosled");
        jsonObject.put(Constants.HINT, "ispravite redosled kljucnih reci");
        jsonObject.put(Constants.DESC, "Kljucne reci moraju biti u ispravno redosledu");

        return jsonObject;

    }

    private JSONObject initObavezni() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Fale obavezni delovi upita");
        jsonObject.put(Constants.HINT, "Dodajte select i from");
        jsonObject.put(Constants.DESC, "Morate imate i select i from u upitu");

        return jsonObject;

    }
}

