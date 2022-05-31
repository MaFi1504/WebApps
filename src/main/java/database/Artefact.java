package database;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "artefact")
@NamedQueries({
        @NamedQuery(name = "artefact.getList", query = "SELECT a FROM Artefact a"),
        @NamedQuery(name = "artefact.getById", query = "SELECT a FROM Artefact a WHERE a.id = :id"),
        @NamedQuery(name = "artefact.getByTitle", query = "SELECT a FROM Artefact a WHERE a.title = :title")

})
public class Artefact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "planned_work_time")
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
}
