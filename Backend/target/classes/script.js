window.addEventListener("load", async () => {
    let response = await fetch("http://localhost:8000/Users/Login/CheckSession");
    let result = await response.json();

    if(result.successful){
        if (result.data.roleId == 1) {
            window.location.href="./Employee/"
        }
        if (result.data.roleId == 2) {
            window.location.href="./Manager/"
        }
    }
})

let loginbtn = document.getElementById("loginbtn")

loginbtn.addEventListener("click", async function(){
    let result = await login();
    if (result.successful) {
        if (result.data.roleId == 1) {
            window.location.href="./Employee/"
        }
        if (result.data.roleId == 2) {
            window.location.href="./Manager/"
        }
    }
});

async function login(e){
    //e.preventDefault();//prevents the form from refreshing

    let userNameInput = document.getElementById("username-input");
    let passwordInput = document.getElementById("password-input");
    let formData = new FormData(e);
    formData.append("username",userNameInput.value);
    formData.append("password",passwordInput.value);

    //console.log(formData);

    let response = await fetch("http://localhost:8000/Users/Login",{
        method: "POST",
        body: formData
    })
    let data = await response.json();
    console.log(data);
    return data;
}