package Database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class Project_TaskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private long ProjectId;
    private long TaskGroupId;

    public Long getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Long projectId) {
        this.ProjectId = ProjectId;
    }

    public Long getTaskGroupId() {
        return TaskGroupId;
    }

    public void setTaskGroupId(Long taskGroupId) {
        this.TaskGroupId = TaskGroupId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
