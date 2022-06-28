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

function buildProject(){
    let title = document.getElementById("projectTitle").value;
    let startDate = document.getElementById("startDate").value;
    let endDate = document.getElementById("endDate").value;
    let shortDescription = document.getElementById("shortDescription").value;
    let longDescription = document.getElementById("longDescription").value;
    let goals = [];
    for (let i = 1; i <= 2; i++){
        goals.push(document.getElementById("projectGoals" + i).value);
    }
    image = document.getElementById("logoFile").value;
    let project = new Project(title, startDate, endDate, shortDescription, longDescription, goals, image);
    items = JSON.parse(localStorage.getItem("projects")) || [];
    console.log(items);
    items.push(project);
    localStorage.setItem("projects", JSON.stringify(items));
    console.log(JSON.parse(localStorage.getItem("projects")));
    return project;
}

let button = document.getElementById("submitButton");
button.addEventListener("click", buildProject);