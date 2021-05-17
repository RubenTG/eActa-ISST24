let url_alumno='http://localhost:8080/students/'+localStorage.getItem("usuario") +'/subjects';

function mensaje(revisada){
    if(revisada==="NO_REVISADA"){
      muestra="Se procederá a revisar tu nota";

    }else if(revisada==="PENDIENTE"){
      muestra="Ya has solicitado revisión";
    }else{
      muestra="Tu nota ya es definitiva";
    }
      return muestra;

} 
/*
function pasaPam(i,asignatura,codigo){
  document.getElementById('card-ISST'+[i]).addEventListener('click', function (e) {
    $.ajax({
      url : 'tu_url'
      data : data, 
      method : 'post', 
      dataType : 'json',
      success : function(response){
             //codigo de exito
      },
      error: function(error){
             //codigo error
      }
});
  })
}
*/
async function getSubjects(url_alumno){

  var myHeaders = new Headers();
  myHeaders.append("Authorization", "Bearer " + localStorage.getItem("access_token"));

  var requestOptions = {
      method: 'GET',
      headers: myHeaders
  };

 fetch(url_alumno, requestOptions)
  .then(res=> {
  if (res.status===401){localStorage.clear();location.href="login.html";}
    res.json().then( alumno => {
        var tabla = document.getElementById("tabla_alumno");
        for (var i = 0; i < alumno.subjects.length; i++) {
          console.log(alumno.subjects[i].name)
          console.log(alumno.subjects[i].revisada)
          
                  var definitiva=(alumno.subjects[i].definitiva)?alumno.subjects[i].nota:"---";
                  var fila =' <tr><td>'+alumno.subjects[i].name+'</td><td>'+alumno.subjects[i].nota+'</td><td>'+definitiva+
                  '</td><td>'+ 
                  '<button type="button" id="revision_alumno'+i+ '" '+
                      ' teacher="'+alumno.subjects[i].teacher+'" code="'+alumno.subjects[i].code+'" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Revisión</button>'+
                  '</td> </tr>';

                  tabla.insertAdjacentHTML('afterbegin', fila);
                  document.getElementById('revision_alumno' +i).addEventListener('click', (e) => llama(e));
                 // nombre.insertAdjacentHTML('afterbegin', alumno.studentName);
                 document.getElementById("nombre_alumno").innerHTML = alumno.studentName; 
                 //mensajeEnvio=mensaje(alumno.subjects[i].revisada);
                
                // document.getElementById("alert_revision").innerHTML = mensajeEnvio; 
              }
    
      })

    });

}

getSubjects(url_alumno);


// Example POST method implementation:
async function postData(url = '', data = {}) {
  // Default options are marked with *
   const response = await fetch(url, {
    method: 'PATCH', // *GET, POST, PUT, DELETE, etc.

    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+localStorage.getItem("access_token")
    },
    
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: JSON.stringify(data) 
  });
  if (response.status===401){localStorage.clear();location.href="login.html";}
  return response; 
}

async function llama(e){
  postData('http://localhost:8080/students/'+localStorage.getItem("usuario")+'/subjects/'+e.target.getAttribute("code")+'/teachers/'+e.target.getAttribute("teacher"), 
  {  revisada: "PENDIENTE" })
  .then(data => {

    if(data.status === 400) {
      data.json().then(res => {
        console.log(res.message); // JSON data parsed by `data.json()` call
        document.getElementById("alert_revision").innerHTML = res.message;
      })
      
    } else {
      document.getElementById("alert_revision").innerHTML = 'Se va a revisar tu nota';
    }
   
    
    
  });
}