package Models;


import java.util.ArrayList;

/**
 * Created by lenovo on 4/29/2017.
 */
public abstract class MappedClass {
    //Getters and Setters
    protected abstract String getAttributes(); //this method should return the attributes to be inserted in a table
    //insert into table(id, name) values() => id, name are attributes

    protected abstract String getValues();    //this method should return the values to be inserted in a table
    //insert into table() values(1, "example") => 1, "example" are values

    protected abstract String getOptions();   //this method should return the options to be used in select query
    // select * from table - select id, name from table => *, id, name are options

    protected abstract String getWhere();     //this method should return the Where statement to be used in select query

    public abstract int getId();           //this method should return the id of the object

    protected abstract Object get();          //this method should handle the get requests and inside each variation of get
    //it should extract the data and create objects

    //Methods
    public void add() {
        DBInterface instance = DBInterface.getInstance();
        String table = this.getClass().getSimpleName();
        if(table.equals("Admin") || table.equals("Bidder") || table.equals("Seller") || table.equals("Member")) {
            table = "SystemUser";
        }
        String attributes = getAttributes();
        String values = getValues();

        instance.insert(table, attributes, values);
    }

    public void delete() {
        DBInterface instance = DBInterface.getInstance();
        String table = this.getClass().getSimpleName();
        if(table.equals("Admin") || table.equals("Bidder") || table.equals("Seller") || table.equals("Member")) {
            table = "SystemUser";
        }

        instance.delete(table, this.getId());
    }

    public void update() {
        DBInterface instance = DBInterface.getInstance();
        String table = this.getClass().getSimpleName();
        if(table.equals("Admin") || table.equals("Bidder") || table.equals("Seller") || table.equals("Member")) {
            table = "SystemUser";
        }

        instance.update(table, this.getOptions(), this.getId(), this.getWhere());
    }

    public  ArrayList<Object[]> getAll() {
        DBInterface instance = DBInterface.getInstance();
        ArrayList<Object[]> results = null;
        String table = this.getClass().getSimpleName();
        if(table.equals("Admin") || table.equals("Bidder") || table.equals("Seller") || table.equals("Member")) {
            table = "SystemUser";
        }

        results = instance.select(table);

        //extract data and create list of objects
        return results;
    }

    public ArrayList<Object[]> getUsingOptions() {
        DBInterface instance = DBInterface.getInstance();
        ArrayList<Object[]> results = null;
        String table = this.getClass().getSimpleName();
        if(table.equals("Admin") || table.equals("Bidder") || table.equals("Seller") || table.equals("Member")) {
            table = "SystemUser";
        }

        results = instance.select(table, this.getOptions());

        //extract data and create list of objects
        return results;
    }

    public ArrayList<Object[]> getUsingOptionsWhere() {
        DBInterface instance = DBInterface.getInstance();
        ArrayList<Object[]> results = null;
        String table = this.getClass().getSimpleName();
        if(table.equals("Admin") || table.equals("Bidder") || table.equals("Seller") || table.equals("Member")) {
            table = "SystemUser";
        }

        results = instance.select(table, this.getOptions(), this.getWhere());

        //extract data and create list of objects
        return results;
    }

}
