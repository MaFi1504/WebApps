package database;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProjectAdapter implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String description;
    private String logoPath;
    private String startDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project toProject() {
        Project project = new Project();
        project.setId(this.id);
        project.setTitle(this.title);
        project.setDescription(this.description);
        project.setLogoPath(this.logoPath);
        project.setStartDate(LocalDateTime.parse(this.startDate));
        return project;
    }
}