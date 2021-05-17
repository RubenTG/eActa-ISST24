
var codigo = localStorage.getItem("codigo");
console.log(codigo)
let url='http://localhost:8080/teachers/'+localStorage.getItem("usuario")+'/subjects/'+codigo+'/students';
console.log(url)
document.getElementById("nameAsignatura").innerHTML=localStorage.getItem("asignatura");
var nota1;


async function getSubjects(url){
   
    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + localStorage.getItem("access_token"));

    var requestOptions = {
        method: 'GET',
        headers: myHeaders
    };

    fetch(url, requestOptions)
     .then(res=> {
   if(res.status===401){location.href="login.html";localStorage.clear()} 
       res.json().then( profesor => {
        //document.getElementById("nameAsignatura").innerHTML=profesor.subjectName;
        console.log(profesor);
        var fila="";
           var tabla = document.getElementById("tabla_profesor");
           var tabla_rev= document.getElementById("revisiones");
           for (var i = 0; i < profesor.students.length; i++) {
               if(profesor.students[i].definitiva===false) nota1=profesor.students[i].nota;
            var provisional=(profesor.students[i].definitiva===false)?profesor.students[i].nota:"---"; 
            var definitiva=(profesor.students[i].definitiva)?profesor.students[i].nota:"---";
                     fila =' <tr><td>'+profesor.students[i].studentName+'</td><td>'+profesor.students[i].nota+'</td><td>'+definitiva+'</td><td>'+profesor.students[i].revisada + 
                     '</td><td> <button type="button"  id ="revButton'+i+'" '+' studentMail="'+profesor.students[i].mailStudent+
                     '" class="btn btn-primary" data-toggle="modal" data-target="#updateModal">Revisar</button> </tr>';

                     if(profesor.students[i].revisada==="PENDIENTE"){tabla_rev.insertAdjacentHTML('beforeend', fila);
                    }else{
                        tabla.insertAdjacentHTML('beforeend', fila);
                    }
                     
                     //tabla.innerHTML=fila;
                    // nombre.insertAdjacentHTML('afterbegin', profesor.studentName);
                    document.getElementById("nombre_profesor").innerHTML = profesor.teacherName; 
                    //document.getElementById('revButton'+i).addEventListener('click', (e) => pasaPam(e));
                    document.getElementById('revButton'+i).addEventListener('click', (e) => {
                      var opcion = prompt("Introduzca calificación:", "");
                     if (opcion == null || opcion == "") {
                        mensaje = "Has cancelado o introducido el nombre vacío";
                        } else {
                            pasaPam(e,opcion);
                            }
                        });
                          
                
                 }
          
         })
   
       });
   
   }

async function postData(url = '', data = {}) {
     const response = await fetch(url, {
      method: 'PATCH', 
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+ localStorage.getItem("access_token")
      },
      referrerPolicy: 'no-referrer', 
      body: JSON.stringify(data) 
    });
    
    return response.json(); 
  }
  
  async function pasaPam(e, nota){
   
      console.log("esto es una prueba");
       
      postData('http://localhost:8080/teachers/'+localStorage.getItem("usuario")+'/subjects/'+codigo+'/students/'+e.target.getAttribute("studentMail")+'', { nota: nota, revisada: "REVISADA" })
      .then(data => {
        console.log(data); 
      });
      
 
}


async function uploadMarks() {
    document.getElementById('readCSV').addEventListener('click',  function (e) {
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
    if (regex.test($("#marksCSV").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var file = document.getElementById('marksCSV').files[0];
            console.log(file);
            var reader = new FileReader();           
            reader.readAsText(file);
            reader.onload = function (e) {
                
                Papa.parse(reader.result, {
                    header: true,
                    skipEmptyLines: true,
                    complete: async function (result) {

                        //Pinta los resultados en la tabla
                       // var marksTable = $("#marksTable").DataTable();
                       // marksTable.rows.add(result.data).draw();
                   
                      var list = { "califications" :[]  };
  

  
 
                        //Preparar el JSON y enviarlo
                        for(let i=0;i<result.data.length;i++){
                           
                            list.califications.push({
                                    "studentName":  result.data[i].studentName,
                                    "nota": parseFloat(result.data[i].nota),
                                    "definitiva": false,
                                    " subjectName": localStorage.getItem("asignatura"),
                                    "code": localStorage.getItem("codigo"),
                                    "mailStudent":  result.data[i].mailStudent,
                                    "mailTeacher": localStorage.getItem("usuario"),
                                    "revisada":"NO_REVISADA"
                            });
                          
                        }
                        
                        
                        console.log(localStorage.getItem("codigo"));
                        //TODO: Poner ruta del post de las notas
                       let response = await fetch( 'http://localhost:8080/teachers/'+localStorage.getItem("usuario")+'/allStudents',{
                        
                       
                      headers: {
                    'Content-Type': 'application/json',
                     'Authorization': 'Bearer '+localStorage.getItem("access_token")
                          },
                       
 
                            method: 'POST',
                            body: JSON.stringify(list)
                          });
                          
                           // let data = await response.json();
                            
                          /* La forma adecuada de que esto funcione es que la tabla vuelva a realizar
                             la llamada ajax y a partir de ahí se repinte. Para hacerlo, descomenta las
                             dos proximas líneas
                            var marksTable = $("#marksTable").DataTable();
                            marksTable.draw();
                          */


                        if(response.status===200){alert("Se actualizaron las notas") }
                        else if(response.status===500){

                            response.json().then(body => alert(body.message))

                            
                        }
                        else if(response.status===401){localStorage.clear();location.href="login.html" }
                    }
                }

                );
            }
            reader.onerror = function () {
                alert(reader.error);
            };
        } else {
            alert("This browser does not support HTML5.");
        }
    } else {
        alert("Please upload a valid CSV file.");
    }
})}


function updateMarkModal(e) {
    return (['<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModal' + name + '" aria-hidden="true" >',
        '<div class="modal-dialog modal-dialog-centered" >',
        '<div class="modal-content">',
        '<div class="modal-header">',
        '<h5 class="modal-title">Actualizar Nota</h5>',
        '</div>',
        '<form id="updateMark">',
        '<div class="modal-body">',
        '<label for="name" class="form-label">Nombre</label>',
    '<input size="16" id="name" type="text" class="form-control" value=' + e.target.getAttribute("studentMail") + ' readonly>',

        '<label for="newMark" class="form-label">Nota</label>',
    '<input size="16" type="text" class="form-control" id="newMark" value=""  required>',
        '</div>',
        '<div class="modal-footer">',
        '<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>',
        '<button type="submit" class="btn btn-primary" onclick="updateMark(this.form)">Revisar</button>',
        '</div>',
        '</form>',
        '</div>',
        '</div>',
        '</div>']).join('');
}


//Subir Acta  a la base de datos
    document.getElementById('readPDF').addEventListener('click', async function (e) {
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.pdf)$/;
        if (regex.test($("#marksPDF").val().toLowerCase())) {
           
                var file = document.getElementById('marksPDF').files[0];
                const formData = new FormData();
                formData.append("file", file);
                
                const response = await fetch('http://localhost:8080/teachers/'+localStorage.getItem("usuario")+'/actas', {
                    method: 'POST', 
                    headers: {
                      'Authorization': 'Bearer '+ localStorage.getItem("access_token")
                    },
                    body: formData
                  });
                  
                 if(response.status===200){
                     alert("Acta registrada");
                 } else if(response.status===401){
                     location.href="login.html";
                 }else{
                     alert("Algo ha fallado");
                 }

        }

    });

    //Generar Acta en formato PDF
  
 document.getElementById('generaPDF').addEventListener('click', async function (e) {
        var myHeaders = new Headers();
        myHeaders.append("Authorization", "Bearer " + localStorage.getItem("access_token"));
    
        var requestOptions = {
            method: 'GET',
            headers: myHeaders
        };
    
 fetch(url, requestOptions)
.then(res => res.json())
.then(data => {
    var doc = new jsPDF();
    var finalY = 20;


    //Comienzo edición fichero PDF
    doc.setFontSize(20);
    doc.text('Universidad Politécnica de Madrid',20,finalY);
    finalY = finalY + 20;


    doc.setFontSize(12);
    doc.text('Asignatura:'+ localStorage.getItem("asignatura"),20,finalY);
    finalY = finalY + 10;
    //Pintamos al tribunal
    var professorsBody = [];
    data.miembrosTribunal.forEach(function (miembro, i) {
        professorsBody.push([miembro.name,miembro.mail])
    });
    doc.text('Tribunal',20,finalY);
    finalY = finalY + 5;
    doc.autoTable({
        startY: finalY,
        head: [['Miembro', 'Correo']],
        body: professorsBody,
        theme : 'striped',
        headStyles:{
            fillColor: [24,103,183],
            halign: 'center'
        },
        bodyStyles: {
            halign: 'center'
        },
      });
    finalY = finalY + 20*professorsBody.length;
    //Pintamos las calificaciones
    var studentsBody = [];
    data.students.forEach(function (student, i) {
        studentsBody.push([student.studentName,student.mailStudent,student.nota])
    });
    doc.text('Calificaciones',20,finalY);
    finalY = finalY + 5;

    doc.autoTable({
        startY: finalY,
        head: [['Alumno', 'Correo', 'Nota']],
        body: studentsBody,
        showHead: 'everyPage',
        theme : 'striped',
        headStyles:{
            fillColor: [24,103,183],
            halign: 'center'
        },
        bodyStyles: {
            halign: 'center'
        },
      });
    
    doc.save('Acta.pdf');

});

    });
getSubjects(url);

uploadMarks();
