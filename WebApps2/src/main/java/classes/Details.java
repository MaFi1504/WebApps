package classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//import javax.xml.bind.annotation.XmlRootElement;
//
//
///**
// * Represents a project
// *
// * @author ffehring
// */
//@XmlRootElement
//@Entity
//@Table(name = "tbl_Projekt")
//@NamedQueries({
//        @NamedQuery( name="projekt.findAll",
//                query="SELECT p FROM Project p")
//})


public class Details implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String logopath;

    private LocalDateTime startdate;      //date-time without a time-zone in the ISO-8601
    // such as  2022-04-25T10:15:30



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

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}