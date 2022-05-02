package Rest;

import Database.Project;
import java.time.LocalDateTime;

/**
 * Adapter solveing the issue
 * - Date is not directly parseable from json useing JAXB
 *
 * @author Florian Fehring
 */
public class ProjektAdapter {

    private String titel;
    private String startdate;
    private Long id;

    public void setTitel(String titel) {
        this.titel = titel;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public Project toProject() {
        Project proj = new Project();
        proj.setId(this.id);
        proj.setTitle(this.titel);
        proj.setStartdate(LocalDateTime.parse(this.startdate));  //yyyy-mm-ddTss:mm:ss
        return proj;                                               //date-time without a time-zone in the ISO-8601
    }
}
