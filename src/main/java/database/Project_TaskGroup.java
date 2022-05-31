package database;

import javax.xml.bind.annotation.XmlRootElement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement
@Entity
@Table(name = "project_taskgroup")
public class Project_TaskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "project_id")
    @OneToMany()
    private Collection<Project> ProjectId;
    @Column(name = "taskgroup_id")
    @OneToMany()
    private Collection<TaskGroup> TaskGroupId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
