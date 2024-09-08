package database;

import person.Person;

import java.sql.*;
import java.util.*;

public class DBController {
    private String fileDB;
    public Statement statmt;
    public ResultSet resSet;

    private String table = "person";
    private String name = "name";
    private String age = "age";

    private static Connection connection;

    public DBController(String fileDB) throws ClassNotFoundException, SQLException {
        this.fileDB = fileDB;
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.fileDB);
        this.createDB();
    }

    private void createDB() throws SQLException {
        String query = String.format("CREATE TABLE IF NOT EXISTS '%s' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, '%s' text, '%s' INTEGER)", this.table, this.name, this.age);
        statmt = connection.createStatement();
        statmt.execute(query);
    }

    public void insertDB(Person obj) throws SQLException {
        String query = String.format("INSERT INTO %s ('%s', '%s') VALUES ('%s', %s)", this.table, this.name, this.age, obj.name, obj.age);
        statmt.execute(query);
    }

    public void deleteDB(Integer id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE id=%d", this.table, id);
        statmt.execute(query);
    }

    public void updateDB(Integer id, Person obj) throws SQLException {
        String query = String.format("UPDATE %s SET %s='%s', %s=%s", this.table, this.name, obj.name, this.age, obj.age);
        statmt.execute(query);
    }

    public List<Person> readDB() throws SQLException {
        List<Person> result = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", this.table);
        resSet = statmt.executeQuery(query);
        while (resSet.next())
        {
            String name = resSet.getString(this.name);
            Integer age = resSet.getInt(this.age);

            Person obj = new Person(name, age);
            result.add(obj);
        }

        return result;
    }
}
