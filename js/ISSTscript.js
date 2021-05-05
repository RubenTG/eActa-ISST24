function uploadMarks() {
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
    if (regex.test($("#marksCSV").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var file = document.getElementById('marksCSV').files[0];
            console.log(file);
            var reader = new FileReader();
            console.log(reader);
            reader.readAsText(file);
            reader.onload = function (e) {
                Papa.parse(reader.result,{
                    header : true,
                    skipEmptyLines : true,
                    complete : function(result) {
                        console.log(result);
                        var marksTable = $("#marksTable").DataTable({
                            data : result.data,
                            columns: [
                                { data: 'Nombre'},
                                { data: 'Nota'},
                                { data: 'Definitiva',
                                render: function ( data, type, row, meta ) {
                                    var modal = updateMarkModal(row.Nombre,row.Nota);
                                    if(data === 'true'){
                                        return modal + '<button type="button" class="btn btn-primary" disabled data-toggle="modal" data-target="#updateModal'+row.Nombre+'"">Revisar</button>';

                                    }else{
                                     return modal + '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateModal'+row.Nombre+'"">Revisar</button>';
                                   }
                                }
                                }
                            ]
                        });

                    } 
                }

                );
            }
            reader.onerror = function() {
                console.log(reader.error);
              };
        } else {
            alert("This browser does not support HTML5.");
        }
    } else {
        alert("Please upload a valid CSV file.");
    }
}


function updateMarkModal(name,previousMark) {
    return (['<div class="modal fade" id="updateModal'+name+'" tabindex="-1" aria-labelledby="updateModal'+name+'" aria-hidden="true" >',
        '<div class="modal-dialog modal-dialog-centered" >',
          '<div class="modal-content">',
            '<div class="modal-header">',
              '<h5 class="modal-title">Actualizar Nota</h5>',
            '</div>',
            '<form id="updateMark">',
            '<div class="modal-body">',
                    '<label for="name" class="form-label">Nombre</label>',
                    '<input size="16" id="name" type="text" class="form-control" placeholder='+name+' readonly>',

                    '<label for="newMark" class="form-label">Nota</label>',
                    '<input size="16" type="text" class="form-control" id="newMark" placeholder='+previousMark+' required>',
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