async function getProjects(){
    const response = await fetch(
        api + "projects",
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });
    return response.json();
}

async function getProject(id){
    const response = await fetch(
        api + "projects/" + id,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });
    return response.json();
}

async function postProject(project){
    const response = await fetch(
        api + "projects",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(project)
        });
    return response.json();
}

async function putProject(project){
    const response = await fetch(
        api + "projects/" + project.id,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(project)
        });
    return response.json();
}

function createProject(data){
    return new Project(data.id, data.title, data.shortDescription, data.startDate, data.endDate, data.tasks, data.artefacts, data.projectArtefacts);
}

function createProjectList(data){
    let projects = [];
    for(let i = 0; i < data.length; i++){
        projects.push(createProject(data[i]));
    }
    return projects;
}

getProjects().then(data => {
    projects = createProjectList(data);
    console.log(projects);
}).catch(error => {
    console.log(error);
});
