package database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class TaskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            Statement statement = conn.createStatement();
            this.id = statement.executeUpdate(
                    "INSERT INTO taskgroup (title, description) " +
                            "VALUES ('" + title + "', '" + description + "') " +
                            "RETURNING id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteFromDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate(
                    "DELETE FROM taskgroup WHERE id = " + this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateInDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate(
                    "UPDATE taskgroup SET title = '" + title + "', description = '" + description + "' WHERE id = " + this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<TaskGroup> getList(){
        ArrayList<TaskGroup> list = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM taskgroup");
            while (rs.next()) {
                list.add(RSToTaskGroup(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static TaskGroup getTaskGroupById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM taskgroup WHERE id = " + id);
            if (rs.next()) {
                return RSToTaskGroup(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TaskGroup getTaskGroupByTitle(String title) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM taskgroup WHERE title = '" + title + "'");
            if (rs.next()) {
                return RSToTaskGroup(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    private static TaskGroup RSToTaskGroup(ResultSet rs) {
        TaskGroup taskGroup = new TaskGroup();
        try {
            taskGroup.setId(rs.getInt("id"));
            taskGroup.setTitle(rs.getString("title"));
            taskGroup.setDescription(rs.getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskGroup;
    }
}

