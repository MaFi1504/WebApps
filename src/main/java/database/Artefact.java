package database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Artefact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String plannedWorkTime;

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

    public String getPlannedWorkTime() {
        return plannedWorkTime;
    }

    public void setPlannedWorkTime(String plannedWorkTime) {
        this.plannedWorkTime = plannedWorkTime;
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
                    "INSERT INTO artefact (title, description, planned_work_time) " +
                            "VALUES ('" + title + "', '" + description + "', '" + plannedWorkTime + "') " +
                            "RETURNING id");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Artefact getArtefactById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM artefact WHERE id = " + id);
            if (rs.next()) {
                return RSToArtefact(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Artefact getArtefactByTitle(String title) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM artefact WHERE title = '" + title + "'");
            if (rs.next()) {
                return RSToArtefact(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Artefact> getArtefactsByUserId(int userId) {
        Connection conn = Database.getDBConnection();
        ArrayList<Artefact> artefacts = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM artefact WHERE user_id = " + userId);
            while (rs.next()) {
                artefacts.add(RSToArtefact(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artefacts;   //returns null if no artefacts found
    }

    public static ArrayList<Artefact> getArtefactsByProjectId(int projectId) {
        Connection conn = Database.getDBConnection();
        ArrayList<Artefact> artefacts = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM artefact WHERE id IN " +
                    "(SELECT artefact_id FROM project_artefact WHERE project_id = " + projectId + ")");
            while (rs.next()) {
                artefacts.add(RSToArtefact(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artefacts;
    }

    public static ArrayList<Artefact> getList() {
        Connection conn = Database.getDBConnection();
        ArrayList<Artefact> artefacts = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM artefact");
            while (rs.next()) {
                artefacts.add(RSToArtefact(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artefacts;
    }

    private static Artefact RSToArtefact(ResultSet rs) {
        Artefact artefact = new Artefact();
        try {
            artefact.setId(rs.getInt("id"));
            artefact.setTitle(rs.getString("title"));
            artefact.setDescription(rs.getString("description"));
            artefact.setPlannedWorkTime(rs.getString("planned_work_time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artefact;
    }
}
