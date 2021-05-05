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
                                    if(data === 'true'){
                                        return '<button type="button" class="btn btn-primary" disabled onClick="searchRevisionStudents()">Revisar</button>';

                                    }else{
                                     return '<button type="button" class="btn btn-primary" onClick="searchRevisionStudents()">Revisar</button>';
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
