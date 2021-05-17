

async function validar() {
    var mail_alumno = document.getElementById("email_alumno").value;
if( mail_alumno !== null && mail_alumno.includes("@alumnos.upm.es")){
  

    const alumno = await getSubjects(mail_alumno);
    var name_student = alumno.studentName;
    var tr_str;
    for (let i = 0; i < alumno.subjects.length; i++) {

        var name_asignatura = alumno.subjects[i].name;
        var nota = alumno.subjects[i].nota;

        tr_str += "<tr>" +
            "<td align='center'>" + mail_alumno + "</td>" +
            "<td align='center'>" + name_student + "</td>" +
            "<td align='center'>" + name_asignatura + "</td>" +
            "<td align='center'>" + nota + "</td>" +
            "</tr>";
     

    }
    $("#marksTable tbody").html(tr_str);

}else{ alert("correo mal escrito")}
    
}





async function getSubjects(alumno) {
    let url_alumno = 'http://localhost:8080/students/' + alumno + '/subjects';
    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + localStorage.getItem("access_token"));

    var requestOptions = {
        method: 'GET',
        headers: myHeaders
    };

    return await fetch(url_alumno, requestOptions)
        .then(res => {
            if (res.status === 401) { localStorage.clear(); location.href = "login.html"; }
            if(res.status!== 200){alert("No se ha encontrado el expediente del alumno")}
            return res.json();
        })
}



/*
 $(document).ready(function(){

    $.ajax({

        url: '',

        type: 'get', (pasandole como parametro el alumno)

        dataType: 'JSON',

        success: function(response){

            for(var i=0; i<len; i++){
                var email = response[i].email;
                var alumno = response[i].alumno;
                var asignaturas = response[i].asignaturas;
                var tr_str = "<tr>" +
                "<td align='center'>" + email + "</td>" +
                "<td align='center'>" + name + "</td>" +
                "<td align='center'>" + asignatura + "</td>" +
                "</tr>";
                $("#userTable tbody").append(tr_str);


            }
        }
    });
});

*/
