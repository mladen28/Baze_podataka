package validator.rules.rules;

import app.AppCore;
import app.gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
import tree.TreeItem;
import validator.rules.AbstractRule;

import java.util.ArrayList;
import java.util.List;

public class KolonePostoje extends AbstractRule{

    private TreeItem root;

    public KolonePostoje(String message) {
        super(message);
        this.setMessage("Navedene kolone ili tabele ne postoje u bazi");
    }

    @Override
    public boolean examineQuery(ArrayList<String> query) {

        int size = query.size();
        int countC1=0, countC2=0, countK1=0, countK2=0, num=0, brojac =0;
        boolean flag = false, flag2 = true, delete = false, select = false, update = false, insert = false;
        String s,str,name;



        root = MainFrame.getInstance().getAppCore().getTree().getRoot();
        List<DBNode> children = ((DBNodeComposite)root.getDbNode()).getChildren();


        if(query.get(0).equals("DELETE"))
            delete = true;

        if(query.get(0).equals("SELECT"))
            select = true;

        if(query.get(0).equals("UPDATE"))
            update = true;

        if(query.get(0).equals("INSERT INTO"))
            insert = true;


        if(select) {
            for (int i = 0; i < size; i++) {

                s = query.get(i);
                if (s.equals("FROM")) {
                    num = i;
                    break;
                }

            }

            for (int k = num + 1; k < size; k++) {


                str = query.get(k);

                if (str.contains(",")) {
                    s = str.replace(",", "");
                } else
                    s = str;


                if (s.equals("WHERE") || s.equals("GROUP BY") || s.equals("USING") || s.equals("ON")) {
                    break;
                }

                if (s.equals("JOIN")) {
                    continue;
                }

                countC1++;


                for (DBNode c : children) {
                    brojac = 0;
                    List<DBNode> kolone = ((DBNodeComposite) c).getChildren();
                    name = c.getName().toString();


                    if (s.toLowerCase().equals(name)) {
                        countC2++;

                        if (!delete) {
                            for (int j = 1; j < num; j++) {
                                str = query.get(j);

                                brojac++;
                                if (str.contains(",")) {
                                    s = str.replace(",", "");
                                    flag = true;
                                } else
                                    s = str;

                                if (brojac == 1) {
                                    if (flag2)
                                        countK1++;
                                    for (DBNode kol : kolone) {
                                        name = kol.getName().toString();
                                        if (s.toLowerCase().equals(name)) {
                                            countK2++;
                                        } else if (s.startsWith("sum") || s.startsWith("avg") || s.startsWith("min") || s.startsWith("max") || s.startsWith("count(") || s.startsWith("SUM") || s.startsWith("AVG") || s.startsWith("MIN") || s.startsWith("MAX") || s.startsWith("COUNT")) {
                                            String[] ag = s.split("[()]");

                                            if (ag[1].equals(name)) {
                                                countK2++;
                                            }
                                        }

                                    }
                                    if (s.equals("*")) {
                                        countK2++;
                                    }
                                }
                                if (brojac == 2 || brojac == 3)
                                    continue;

                                if (flag) {
                                    brojac = 0;
                                    flag = false;
                                }


                            }
                        }
                        if (flag2) {
                            flag2 = false;
                        }

                        break;
                    }
                }


            }
            if(countC1==countC2 && countK1==countK2)
                return true;
            else
                return false;

        }

        if(update)
        {
            boolean flagD = false;
            int cnt1 = 0, cnt2 = 0;
            for (DBNode c : children)
            {
                if(query.get(1).equals(c.getName()))
                {
                    flagD = true;
                    List<DBNode> kolone = ((DBNodeComposite) c).getChildren();



                    for(int i = 3; i< query.size(); i++)
                    {

                        if(query.get(i).equals("WHERE"))
                            break;
                        cnt1++;
                        String[] strD = query.get(i).split("[=\\s+]");
                        for (DBNode kol : kolone){
                            if(strD[0].equals(kol.getName()))
                                cnt2++;
                        }
                    }



                }
            }

            if(flagD && cnt1==cnt2)
                return true;
            else
                return false;

        }



//        System.out.println(countC1);
//        System.out.println(countC2);
//
//        System.out.println(countK1);
//        System.out.println(countK2);


        return false;

    }

}
