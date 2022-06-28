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

function fetchFromLocalStorage(){
    items = JSON.parse(localStorage.getItem("projects")) || [];
    console.log(items);
    if(items.length > 0){
        document.getElementById("projectTitle").innerText = items[0].title;
        document.getElementById("projectShortDescriptionValue").innerText = items[0].shortDescription;
        document.getElementById("projectLongDescriptionValue").innerText = items[0].longDescription;
        for(let i = 1; i <= items[0].goals.length; i++){
            document.getElementById("goal" + i).innerText = items[0].goals[i - 1];
        }
    }
}

fetchFromLocalStorage();