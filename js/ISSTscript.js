function uploadMarks() {
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
    if (regex.test($("#marksCSV").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var reader = new FileReader();
            reader.onload = function (e) {
                Papa.parse(reader.result,{
                    header : true,
                    complete : function(result) {
                        console.log(result);
                        var marksTable = $("#marksTable").DataTable({
                            data : result.data,
                            columns: [
                                { data: 'Nombre'},
                                { data: 'Nota'},
                            ]
                        });
                        
                    } 
                }
                    
                );
            }
        } else {
            alert("This browser does not support HTML5.");
        }
    } else {
        alert("Please upload a valid CSV file.");
    }

}