package database;

import resource.DBNode;
import resource.data.Row;

import java.util.List;

public interface Database{

    DBNode loadResource();

    List<Row> readDataFromTable(String tableName);

    List<Row> citaj(String tableName, String upit);



}
