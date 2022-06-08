async function getArtefacts(){
    const response = await fetch(
        api + "artefacts",
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });
    return response.json();
}

async function getArtefact(id){
    const response = await fetch(
        api + "artefacts/" + id,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });
    return response.json();
}

async function postArtefact(artefact){
    const response = await fetch(
        api + "artefacts",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(artefact)
        });
    return response.json();
}

async function putArtefact(artefact){
    const response = await fetch(
        api + "artefacts/" + artefact.id,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(artefact)
        });
    return response.json();
}

function createArtefact(data){
    return new Artefact(data.id, data.title, data.shortDescription, data.image, data.startDate, data.endDate, data.tasks, data.projectArtefacts);
}

function createArtefactList(data){
    let artefacts = [];
    for(let i = 0; i < data.length; i++){
        artefacts.push(createArtefact(data[i]));
    }
    return artefacts;
}

getArtefacts().then(data => {
    let artefacts = createArtefactList(data);
    console.log(artefacts);
});
