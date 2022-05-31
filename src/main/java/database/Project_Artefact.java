package database;

import javax.xml.bind.annotation.XmlRootElement;

import javax.persistence.*;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "project_artefact")
public class Project_Artefact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "work_time")
    private String work_time;

    public String getWork_time() {return work_time;}

    public void setWork_time(String work_time){
        this.work_time = work_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}