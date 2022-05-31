package database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class Project_TaskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ProjectId;
    private int TaskGroupId;

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(long projectId) {
        this.ProjectId = ProjectId;
    }

    public int getTaskGroupId() {
        return TaskGroupId;
    }

    public void setTaskGroupId(long taskGroupId) {
        this.TaskGroupId = TaskGroupId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
