const secretariajson = [
	{
            "email": "paco@alumnos.upm.es",
            "alumno": "Paco",
            "asignaturas": [{ "name": "CORE", "curso": "3º", "nota": 3},
                { "name": "PROG", "curso": "1º", "nota": 3},
                { "name": "ISST", "curso": "4º", "nota": 3},
                { "name": "APSV", "curso": "1º", "nota": 3}]

        },
        {
            "email": "julian@alumnos.upm.es",
            "alumno": "Julian",
            "asignaturas": [{ "name": "CORE", "curso": "3º", "nota": 3},
                { "name": "PROG", "curso": "1º", "nota": 3},
                { "name": "ISST", "curso": "4º", "nota": 3},
                { "name": "APSV", "curso": "1º", "nota": 3}]

        },
        {
            "email": "roberto@alumnos.upm.es",
            "alumno": "Roberto",
            "asignaturas": [{ "name": "CORE", "curso": "3º", "nota": 3},
                { "name": "PROG", "curso": "1º", "nota": 3},
                { "name": "ISST", "curso": "4º", "nota": 3},
                { "name": "APSV", "curso": "1º", "nota": 3}]

        },
        {
            "email": "rosa@alumnos.upm.es",
            "alumno": "Rosa",
            "asignaturas": [{ "name": "CORE", "curso": "3º", "nota": 3},
                { "name": "PROG", "curso": "1º", "nota": 3},
                { "name": "ISST", "curso": "4º", "nota": 3},
                { "name": "APSV", "curso": "1º", "nota": 3}]

        }
    
];

function validar(){
    
    var alumno = document.getElementById("email_alumno").value;
    var alumnojson = JSON.stringify(alumno);
    var email = null;


        for (let i = 0; i < secretariajson.length; i++) {
            if (secretariajson[i].email === alumno) {
                    email = secretariajson[i].email;
                    var alumno = secretariajson[i].alumno;
                    var asignaturas = secretariajson[i].asignaturas;
                    
                    for (let j = 0; j < asignaturas.length; j++) {

                        var tr_str = "<tr>" +
                        "<td align='center'>" + email + "</td>" +
                        "<td align='center'>" + alumno + "</td>" +
                        "<td align='center'>" + asignaturas[j].name + "</td>" +
                        "<td align='center'>" + asignaturas[j].curso + "</td>" +
                        "<td align='center'>" + asignaturas[j].nota + "</td>" +
                        "</tr>";
                        $("#marksTable tbody").append(tr_str);

                    }
            }
        } 

        if (email === null) alert("correo mal escrito")
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
