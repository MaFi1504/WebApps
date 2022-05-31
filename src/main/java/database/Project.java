package database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String logoPath;
    private LocalDateTime startDate;
    

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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
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
                    "INSERT INTO project (title, description, logo_path, start_date) " +
                            "VALUES ('" + title + "', '" + description + "', '" + logoPath + "', '" + startDate + "') " +
                            "RETURNING id");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Project getProjectById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM project WHERE id = " + id);
            if (rs.next()) {
                return RSToProject(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Project getProjectByTitle(String title) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM project WHERE title = '" + title + "'");
            if (rs.next()) {
                return RSToProject(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Project> getList() {
        Connection conn = Database.getDBConnection();
        ArrayList<Project> projects = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM project");
            while(rs.next()) {
                projects.add(RSToProject(rs));
            }
        } catch (SQLException e) {
                    throw new RuntimeException(e);
        }
        return projects;
    }

    private static Project RSToProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.id = rs.getInt("id");
        project.title = rs.getString("title");
        project.description = rs.getString("description");
        project.logoPath = rs.getString("logo_path");
        project.startDate = rs.getTimestamp("start_date").toLocalDateTime();
        return project;
    }
}