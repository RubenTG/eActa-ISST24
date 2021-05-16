$(document).ready(function () {

    $("#marksTable").DataTable({
        ajax: {
            //Descomentar la siguiente línea y añadir la ruta correspondiente
            url: "ejemplo.json",
            dataSrc: 'students'
        },
        //data: './ejemplo.json',
        //dataSrc : 'students',
        columns: [
            { data: 'studentName' },
            { data: 'nota' },
            {
                data: 'null',
                render: function (data, type, row, meta) {
                    var modal = updateMarkModal(row.studentName, row.nota,row.mailStudent);
                    /*if(data === 'true'){
                        return modal + '<button type="button" class="btn btn-primary" disabled data-toggle="modal" data-target="#updateModal'+row.Nombre+'"">Revisar</button>';

                    }else{
                        */
                    return modal + '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateModal' + row.studentName +'">Revisar</button>';
                    //}
                }
            },
            {
                data: 'revisada',
                defaultContent: 'NO REVISADA'
            },
            {
                data: 'mailStudent',
            }
        ],
        columnDefs: [
            { targets: [3, 4], visible: false },
        ]
    });

    subirDatosConFetch();
    //generarPDF();

});

function subirDatosConFetch(){

    var tabla = $("#marksTable").DataTable();
    fetch("./ejemplo2.json")
    .then(res => res.json())
    .then(data =>{
        tabla.rows.add(data.students).draw()
    });
}


function uploadMarks() {
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
    if (regex.test($("#marksCSV").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var file = document.getElementById('marksCSV').files[0];
            var reader = new FileReader();
            reader.readAsText(file);
            reader.onload = function (e) {
                Papa.parse(reader.result, {
                    header: true,
                    skipEmptyLines: true,
                    complete: async function (result) {

                        //Pinta los resultados en la tabla
                        var marksTable = $("#marksTable").DataTable();
                        marksTable.rows.add(result.data).draw();

                        //Preparar el JSON y enviarlo
                        for (let i = 0; i < result.data.length; i++) {
                            result.data[i].subjectName = localStorage.getItem('subjectName');
                            result.data[i].subjectCode = localStorage.getItem('subjectCode');
                            result.data[i].mailTeacher = localStorage.getItem('mailTeacher');
                            result.data[i].revisada = 'NO REVISADA';
                            result.data[i].definitiva = false;
                        }
                        //TODO: Poner ruta del post de las notas
                        let response = await fetch('/subject/subjectcode/revision', {
                            method: 'POST',
                            body: result.data
                        });

                        let data = await response.json();
                        console.log(data);
                        /* La forma adecuada de que esto funcione es que la tabla vuelva a realizar
                           la llamada ajax y a partir de ahí se repinte. Para hacerlo, descomenta las
                           dos proximas líneas

                          var marksTable = $("#marksTable").DataTable();
                          marksTable.draw();
                        */


                        console.log(result.data);
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
}


function updateMarkModal(name, previousMark,mailStudent) {
    return (['<div class="modal fade" id="updateModal' + name + '" tabindex="-1" aria-labelledby="updateModal' + name + '" aria-hidden="true" >',
        '<div class="modal-dialog modal-dialog-centered" >',
        '<div class="modal-content">',
        '<div class="modal-header">',
        '<h5 class="modal-title">Actualizar Nota</h5>',
        '</div>',
        '<form id="updateMark">',
        '<div class="modal-body">',
        '<label for="name" class="form-label">Nombre</label>',
    '<input size="16" id="name" type="text" class="form-control" value=' + name + ' readonly>',

        '<label for="newMark" class="form-label">Nota</label>',
    '<input size="16" type="text" class="form-control" id="newMark" value=' + previousMark + ' required>',
    '<input type="hidden" readonly=""  id="mailStudent" value='+mailStudent+'></input>',
        '</div>',
        '<div class="modal-footer">',
        '<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>',
        '<button type="submit" class="btn btn-primary" onclick="updateMark(this.form)">Guardar</button>',
        '</div>',
        '</form>',
        '</div>',
        '</div>',
        '</div>']).join('');
}