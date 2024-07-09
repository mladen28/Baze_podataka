package validator.rules.rules;

import validator.rules.AbstractRule;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Character.isUpperCase;

public class RedosledIspravan extends AbstractRule {


    public RedosledIspravan (String message) {
        super(message);
        this.setMessage("Redosled iskaza nije ispravan");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {

        int size = query.size(), type =0, l= 0;

        ArrayList<String> select = new ArrayList<String>(Arrays.asList("SELECT","FROM","JOIN","WHERE","ORDER BY"));
        ArrayList<String> insert = new ArrayList<String>(Arrays.asList("INSERT","INTO","VALUES"));
        ArrayList<String> delete = new ArrayList<String>(Arrays.asList("DELETE","FROM","WHERE"));
        ArrayList<String> update = new ArrayList<String>(Arrays.asList("UPDATE","SET","WHERE"));

        String[][] matrix = {{"SELECT","FROM","JOIN","WHERE","ORDER BY"},{"INSERT","INTO","VALUES"}, {"DELETE","FROM","WHERE"}, {"UPDATE","SET","WHERE"} };

        if(query.get(0).equals(matrix[0][0]))
        {
            type = 0;
            l=5;
        }
        if(query.get(0).equals(matrix[1][0])) {
            type = 1;
            l=3;
        }
        if(query.get(0).equals(matrix[2][0])) {
            type = 2;
            l=3;
        }
        if(query.get(0).equals(matrix[3][0])) {
            type = 3;
            l=3;
        }

        int count = 0;

        boolean flag = false;
        boolean flag2 = false;


        for(int i = 0; i<size;i++)
        {
            flag = false;
            if(IsUpper(query.get(i)))
            {

                for(int j = 0; j<l-1;j++)
                {

                    if(query.get(i).equals(matrix[type][j])) {

                        flag = true;
                        break;
                    }
                }

                if(flag)
                {
                    flag2 = false;
                    for(int j = count; j<l-1;j++)
                    {


                        if(query.get(i).equals(matrix[type][j]))
                        {
                            flag2 = true;
                            break;
                        }
                        count++;

                    }
                    if(!flag2)
                        return false;

                }

            }



        }



        return true;
    }

    private boolean IsUpper(String str){

        int l = str.length();

        char c;

        for(int i =0 ; i<l; i++)
        {
            c = str.charAt(i);
            if(!isUpperCase(c)) {
                return false;
            }
        }

        return true;
    }
}
