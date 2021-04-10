import usuarios from './mock-data.js';
var user={name:undefined,pass:undefined,isAdmin: undefined};
var uriProf = "index.html";
var uriAlum = "ALUMNO.html";
var uri;

document.getElementById("inputEmailAddress").value = "";
document.getElementById("inputPassword").value = "";
const login = (url) => {
    document.getElementById("init").onclick = function () {
        location.href = uri; // location.href = location.href + "/currentpath/additional/params/here"
    }
}



var input1 = document.getElementById("inputEmailAddress");
var input2 = document.getElementById("inputPassword");

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

const warning = (input,mensaje,boolean) => {
    
var aviso= document.getElementById(input);
 
    aviso.innerHTML=boolean?'<p style= "color:red;font-size:13px">'+mensaje+'</p>':"";
    //input.insertAdjacentHTML('beforeend', aviso);
}
