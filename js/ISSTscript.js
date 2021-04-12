function uploadMarks() {
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
    if (regex.test($("#marksCSV").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var reader = new FileReader();
            reader.onload = function (e) {
                var table = $("<table class='table table-bordered' id='dataTable' width='100%' cellspacing='0'/>");
                var rows = e.target.result.split("\n");
                console.log(rows)
                for (var i = 0; i < rows.length; i++) {
                    if(i===0){
                        var row = $("<tr class='table-primary' />");
                        var cells = rows[i].split(";");
                        if (cells.length > 1) {
                            for (var j = 0; j < cells.length; j++) {
                                var cell = $("<th />");
                                cell.html(cells[j]);
                                row.append(cell);
                            }
                            table.append(row);
                        }
                    }else{
                        var row = $("<tr />");
                        var cells = rows[i].split(";");
                        if (cells.length > 1) {
                            for (var j = 0; j < cells.length; j++) {
                                var cell = $("<td />");
                                cell.html(cells[j]);
                                row.append(cell);
                            }
                            table.append(row);
                        }
                    }

                }
                $("#marksTable").html('')
                $("#marksTable").append(table);
            }
            reader.readAsText($("#marksCSV")[0].files[0]);
        } else {
            alert("This browser does not support HTML5.");
        }
    } else {
        alert("Please upload a valid CSV file.");
    }
}