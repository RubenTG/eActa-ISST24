
var user = { name: undefined, pass: undefined, isAdmin: undefined };
var uriProf = "index.html";
var uriAlum = "ALUMNO.html";
var uri;

document.getElementById("inputEmailAddress").value = "";
document.getElementById("inputPassword").value = "";

var input1 = document.getElementById("inputEmailAddress");
var input2 = document.getElementById("inputPassword");


//location.href = uri; // location.href = location.href + "/currentpath/additional/params/here"


/*
userMail="usuario@upm.es";
localStorage.setItem("userMail",userMail);



input1.addEventListener("change", function () {
   
   aunt(input1.value);
});

function aunt(Usuario) {
    for (let i = 0; i < usuarios.length; i++) {
        console.log(usuarios[i].profile.username)
        if (usuarios[i].profile.username === Usuario) {
            user.name = Usuario;
            user.pass = usuarios[i].profile.password; 
            user.isAdmin=usuarios[i].profile.isAdmin; 
            document.getElementById("passPrueba").innerHTML = ""; 
            warning("mailPrueba","",false);
            return;
            
        }
    }  
    document.getElementById("passPrueba").innerHTML = "";  
    warning("mailPrueba","Usuario no válido",true);
    
}

input2.addEventListener("change", function () {
    
    if (user.pass!==undefined && user.pass === input2.value) {
        warning("passPrueba","",false);
        uri=user.isAdmin?uriProf:uriAlum;
        login(uri);

    } else if (user===undefined || user.length === 0) {
        warning("passPrueba"," Password incorrecta",true);
    } else {
        warning("passPrueba","Password incorrecta",true);
    }
});
*/
const warning = (input,mensaje,boolean) => {
    
var aviso= document.getElementById(input);
 
    aviso.innerHTML=boolean?'<p style= "color:red;font-size:13px">'+mensaje+'</p>':"";
    //input.insertAdjacentHTML('beforeend', aviso);
}



async function login() {
    document.getElementById("init").onclick = async function () {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
        myHeaders.append("Access-Control-Allow-Origin", "*");

        var urlencoded = new URLSearchParams();
        urlencoded.append("grant_type", "password");
        urlencoded.append("client_id", "app-upm");
        urlencoded.append("client_secret", "4b3c8e72-5d3e-4f38-aeef-a335fc0dbddb");
        urlencoded.append("username", input1.value);
        urlencoded.append("password", input2.value);

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: urlencoded,
            redirect: 'follow'
        };

        var url = "http://localhost:8083/auth/realms/upm/protocol/openid-connect/token";

        const response = await fetch(url, requestOptions);

        if(response.status == 200) {

            response.json().then(body => {
                const accessTokenDecoded = window.jwt_decode(body.access_token);
                let roles = accessTokenDecoded.realm_access.roles;

                localStorage.setItem("roles", roles);
                localStorage.setItem("usuario", input1.value);
                localStorage.setItem("access_token", body.access_token);
                console.log(body.access_token)
                localStorage.setItem("refresh_token", body.refresh_token);

                if(roles.includes("teacher")) {
                    location.href = "index.html";
                } else if(roles.includes("student")) {
                    location.href = "ALUMNO.html";
                } else {
                    location.href = "secretaria.html";
                }

               

            });

        } else if(response.status == 401){


            warning("passPrueba","usuario no válido",true);

        }

      

    }
}

login();

/*fetch('http://localhost:8080/teachers/1111/subjects/10/students/33', {
  method: 'POST',
  mode: 'no-cors',
  body: { nota: 42 },
})
.then(response => response.json())
.then(result => {
  console.log('Success:', result);
})
.catch(error => {
  console.error('Error:', error);
});*/