const api = "http://localhost:8080/api-v1/";

class Project{

    constructor(title, shortDescription, image, startDate){
        this.title = title;
        this.shortDescription = shortDescription;
        this.image = image;
        this.startDate = startDate;
    }

    calculateProjectTime(){
        let projectTime = new Date();
        projectTime.setTime(this.startDate.getTime());
        projectTime.setMonth(projectTime.getMonth() + 1);
        projectTime.setDate(projectTime.getDate() - 1);
        return projectTime;
    }
}

class TaskGroup{
    constructor(title, shortDescription){
        this.title = title;
        this.shortDescription = shortDescription;
    }
}

class Artefact{
    constructor(title, shortDescription){
        this.title = title;
        this.shortDescription = shortDescription;
    }
}

class Project_Artefact{
    constructor(project, artefact){
        this.project = project;
        this.artefact = artefact;
    }
    getArtefact(){
        return this.artefact;
    }
    getProject(){
        return this.project;
    }
}

class SortProjects{
    constructor(projects){
        this.projects = projects;
    }
    sortStartDate(){
        this.projects.sort(function(a, b){
            if(a.startDate < b.startDate){
                return -1;
            }
            if(a.startDate > b.startDate){
                return 1;
            }
            return 0;
        });
    }
    sortProjectTime(){
        this.projects.sort(function(a, b){
            if(a.calculateProjectTime() < b.calculateProjectTime()){
                return -1;
            }
            if(a.calculateProjectTime() > b.calculateProjectTime()){
                return 1;
            }
            return 0;
        });
    }

    getProjects(){
        return this.projects;
    }
}