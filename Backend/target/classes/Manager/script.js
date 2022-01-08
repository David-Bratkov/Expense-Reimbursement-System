window.onload = async () => {

    let response = await fetch("http://localhost:8000/Users/Login/CheckSession");
    let result = await response.json();

    //console.log(result);

    if(!result.successful)
    window.location.href = "../";

    //go to sales dashboard if role is sales
    if(result.data.roleId == 1)
    window.location.href = "../Employee"

    //console.log(result.data.userId);

    getReimbursements(result.data.userId);
}


// Class notes:

// Jimbo is a nerdo

async function sortlist(id) {

    try {
        let response = await fetch(`http://localhost:8000/Reimbursements/${id}/Filtered`, {
            method: 'GET'
        });
        let data = await response.json();
        populateReimbursements(data, 2);

    }catch (error) {
        console.log(error);
    }
}


async function logout() {

    try {
        await fetch(`http://localhost:8000/Users/Login/Delete`, {
        method: 'DELETE'
        })

    window.location.href = "../";

    }catch (error) {
        console.log(error);
    }
}

async function updateTicket(choice, id, resolver) {

    // console.log(choice);
    // console.log(resolver);
    // console.log(id);

    Id = id;

    let formData = new FormData();
    formData.append("status_id", choice);
    formData.append("resolver", resolver);

    console.log(formData);

    await fetch(`http://localhost:8000/Reimbursements/${Id}`,{
        method: "PUT",
        body: formData
    })

    getReimbursements(resolver);

}

async function getReimbursements(id){

    try {
        let response = await fetch("http://localhost:8000/Reimbursements", {
            method: 'GET'
        });
        let data = await response.json();
        populateReimbursements(data, id);

    }catch (error) {
        console.log(error);
    }
}

async function getFullname(id){
    try {
        let response = await fetch(`http://localhost:8000/Users/${id}/UserInfo`, {
            method: 'GET'
        });
        let result = await response.json();
        //console.log(data);
        if (result.successful) {

            return (result.data.fullname);
        }
        

    }catch (error) {
        console.log(error);
    }
}

//add buttons functionality and logout button

function populateReimbursements(reimbursementList, id){

    let reimbContainerElem = document.getElementById("reimbursement-container");
    let Id = id
    //console.log(reimbursementList);
    reimbContainerElem.innerHTML = "";
    /*

    <div class="card">
        <div class="card-header">
            <h5>Ticket #[id]</h5>
        </div>
        <div class="card-body">
            <div class="card-text">
                <div class="name">
                    <span>Name: </span>
                    <span>Big Nerd</span>
                </div>
                <div class="amount">
                    <span>Amount: </span>
                    <span>$big ones</span>
                </div>
                <div class="submitted-time">
                    <span>Submitted Time:</span>
                    <span>Yesterday</span>
                </div>
                <div class="resolved-time">
                    <span>Resolved Time: </span>
                    <span>Tomorrow</span>
                </div>
                <div class="description">
                    <span>Description: </span>
                    <span>jim is a nerd this is a long description that might break everything so I am going to try it out anyways and see how bad it looks on the webpage</span> 
                </div>
                <div class="author">
                    <span>Author: </span>
                    <span>You</span>
                </div>
                <div class="resolver">
                    <span>Resolver:</span>
                    <span>Me</span>
                </div>
                <div class="type">
                    <span>Type: </span>
                    <span>Food</span>
                </div>
                <div class="status">
                    <span>Status: </span>
                    <span>Dead</span>
                </div>
            </div>
            <p class="card-text">Please Select a resolution for this ticket</p>
            <div class="buttons">
                <button class="btn btn-success" onclick="updateTicket(2, ${index})">Accept</button>
                <button class="btn btn-danger" onclick="updateTicket(3, ${index})">Deny</button>
            </div>
        </div>
    </div>
        
        */

    reimbursementList.forEach(async function(reimb, index){

        //console.log(reimb);
        //console.log(index);
        //console.log(id);

        let fullname = await getFullname(reimb.author);

        let resolver;

        if (reimb.status_id != 1) {
            resolver = await getFullname(reimb.resolver);
        }

        //console.log(await fullname);

        let reimbCard = document.createElement("div");
        reimbCard.className = "card";
        reimbCard.id = reimb.id;

        let reimbId = document.createElement("div");
        reimbId.className = "card-header";
        reimbId.innerHTML = ("Ticket #" + (reimb.id));

        let reimbBody = document.createElement("div");
        reimbBody.className = "card-body";

        let reimbCardText = document.createElement("div");
        reimbCardText.className = "card-text";

        let reimbUserId = document.createElement("div");
        reimbUserId.className = "name";
        reimbUserId.innerHTML = ("Name: " + await fullname);

        let reimbElemAmount = document.createElement("div");
        reimbElemAmount.className = "amount";
        reimbElemAmount.innerHTML = ("Amount: $" + reimb.amount);

        let reimbElemSubmittedTime = document.createElement("div");
        reimbElemSubmittedTime.className = "reimbSubmittedTime";
        reimbElemSubmittedTime.innerHTML = ("Submitted Time: " + reimb.submitted_time);

        let reimbElemResolvedTime = document.createElement("div");
        reimbElemResolvedTime.className = "reimbResolvedTime";
        reimbElemResolvedTime.innerHTML =("Resolved Time: " + reimb.resolved_time);

        let reimbElemDesciption = document.createElement("div");
        reimbElemDesciption.className = "reimbDesciption";
        reimbElemDesciption.innerHTML = ("Description: " + reimb.description);

        let reimbElemResolver = document.createElement("div");
        reimbElemResolver.className = "reimbResolver";
        reimbElemResolver.innerHTML = ("Resolver : " + await resolver);

        let reimbElemType = document.createElement("div");
        reimbElemType.className = "reimbType";
        if (reimb.type_id == 1) {
            reimbElemType.innerHTML = "Type: Lodging";
        }
        if (reimb.type_id == 2) {
            reimbElemType.innerHTML = "Type: Travel";
        }
        if (reimb.type_id == 3) {
            reimbElemType.innerHTML = "Type: Food";
        }
        if (reimb.type_id == 4) {
            reimbElemType.innerHTML = "Type: Other";
        }

        let reimbElemStatus = document.createElement("p");

        let reimbElemButtons = document.createElement("div");
        reimbElemButtons.className = "buttons";
        reimbElemButtons.innerHTML = `
            <button class="btn btn-success" onclick="updateTicket(2, ${reimb.id}, ${Id})">Accept</button>
            <button class="btn btn-danger" onclick="updateTicket(3, ${reimb.id}, ${Id})">Deny</button>`

        // let reimbElemButton1 = document.createElement("button");
        // reimbElemButton1.className = "btn btn-success";
        // reimbElemButton1.innerHTML = "Accept";
        // reimbElemButton1.addEventListener(onclick, updateTicket(2, index, false));

        // let reimbElemButton2 = document.createElement("button");
        // reimbElemButton2.className = "btn btn-danger";
        // reimbElemButton2.innerHTML = "Deny";
        // reimbElemButton2.addEventListener(onclick, updateTicket(3, index, false));

        // reimbElemButtons.appendChild(reimbElemButton1);
        // reimbElemButtons.appendChild(reimbElemButton2);

        reimbElemType.id = reimb.status_id;

        reimbCard.appendChild(reimbId);
        reimbCardText.appendChild(reimbUserId);
        reimbCardText.appendChild(reimbElemAmount);
        reimbCardText.appendChild(reimbElemSubmittedTime);
        if (reimb.status_id != 1) {
            reimbCardText.appendChild(reimbElemResolvedTime);
            reimbCardText.appendChild(reimbElemResolver);
        }
        reimbCardText.appendChild(reimbElemDesciption);

        reimbBody.appendChild(reimbCardText);

        reimbCardText.appendChild(reimbElemType);
        if (reimb.status_id == 1) {
            reimbElemStatus.innerHTML = "Please Select a resolution for this ticket";
            reimbBody.appendChild(reimbElemStatus);
            reimbBody.appendChild(reimbElemButtons);
        }
        if (reimb.status_id == 2) {
            reimbElemStatus.innerHTML = "This ticket has been Approved";
            reimbBody.appendChild(reimbElemStatus);
        }
        if (reimb.status_id == 3) {
            reimbElemStatus.innerHTML = "This ticket has been Denied";
            reimbBody.appendChild(reimbElemStatus);
        }

        reimbCard.appendChild(reimbBody);

        //console.log(reimbCard);

        reimbContainerElem.appendChild(reimbCard);

    });

    
    
}