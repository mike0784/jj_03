import person.Person;
import database.DBController;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        String file = "ser";

        Person obj = new Person("Mike", 23);

        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream object = new ObjectOutputStream(f);
        object.writeObject(obj);
        object.close();

        FileInputStream ff = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(ff);
        Person objIn = null;
        try {
            objIn = (Person) objectIn.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(objIn.toString());


        ArrayList<Person> objects = new ArrayList<Person>();
        objects.add(new Person("Mike", 12));
        objects.add(new Person("Nik", 34));
        objects.add(new Person("Mariy", 43));
        objects.add(new Person("Hilary", 22));
        DBController controller = new DBController("proba.db");

        for (Person item: objects) {
            controller.insertDB(item);
        }

        List<Person> res = controller.readDB();
        for (Person item: res) {
            System.out.println(item.toString());
        }
        System.out.println();
        controller.deleteDB(1);
        res = controller.readDB();
        for (Person item: res) {
            System.out.println(item.toString());
        }
    }
}