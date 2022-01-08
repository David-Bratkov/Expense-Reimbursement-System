window.onload = async () => {
    
    try {

        let response = await fetch("http://localhost:8000/Users/Login/CheckSession");
        let result = await response.json();
    
        console.log(result);
    
        if(!result.successful)
        window.location.href = "../";

        if(result.data.roleId == 2)
        window.location.href = "../Manager"
    
        getSpecificReimbursements(result.data.fullname, result.data.userId);

    }catch (error){
        console.log(error);
        window.location.href = "../"
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

async function getSpecificReimbursements(fullname, id){

    try {
        let response = await fetch(`http://localhost:8000/Reimbursements/User/${id}`, {
            method: 'GET'
        });
        let data = await response.json();
        populateReimbursements(data, fullname);

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

function gotoTicket() {

    window.location.href = "./New-Ticket"

}


function populateReimbursements(reimbursementList, Fullname){

    let reimbContainerElem = document.getElementById("reimbursement-container");

    console.log(reimbursementList);

    let fullname = Fullname;

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
                <button class="btn btn-success">Accept</button>
                <button class="btn btn-danger">Deny</button>
            </div>
        </div>
    </div>
        
        */

    reimbursementList.forEach(async function(reimb, index){

        let resolver;

        if (reimb.status_id != 1) {
            resolver = await getFullname(reimb.resolver);
        }

        let reimbCard = document.createElement("div");
        reimbCard.className = "card";
        reimbCard.id = index;

        let reimbId = document.createElement("div");
        reimbId.className = "card-header";
        reimbId.innerHTML = ("Ticket #" + (index+1));

        let reimbBody = document.createElement("div");
        reimbBody.className = "card-body";

        let reimbCardText = document.createElement("div");
        reimbCardText.className = "card-text";

        let reimbUserId = document.createElement("div");
        reimbUserId.className = "name";
        reimbUserId.innerHTML = ("User: " + fullname)

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
        reimbElemResolver.innerHTML = ("Resolver: " + await resolver);

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

        let reimbElemButton1 = document.createElement("button");
        reimbElemButton1.className = "btn btn-success";
        reimbElemButton1.innerHTML = "Accept";

        let reimbElemButton2 = document.createElement("button");
        reimbElemButton2.className = "btn btn-danger";
        reimbElemButton2.innerHTML = "Deny";

        reimbElemButtons.appendChild(reimbElemButton1);
        reimbElemButtons.appendChild(reimbElemButton2);

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
            reimbElemStatus.innerHTML = "This ticket is Pending";
            reimbBody.appendChild(reimbElemStatus);
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

        console.log(reimbCard);

        reimbContainerElem.appendChild(reimbCard);

    });

    
    
}