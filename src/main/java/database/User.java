package database;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "human")
@NamedQueries({
        @NamedQuery(name = "user.getAll", query = "SELECT u FROM User u")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String name;
    @Column(name = "profile_picture")
    private String profilePicture;


    public String getName(){return name;}

    public void setName(String  name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePicture(){return profilePicture;}

    public void setProfilePicture(String  profilePath) {
        this.profilePicture = profilePath;
    }
}
