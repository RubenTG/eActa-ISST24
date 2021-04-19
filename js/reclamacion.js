$(document).ready(function(){

    $('#publishTable').DataTable({
        columns: [
            { data: 'publishNote'},
            { data: 'startDay'},
            { data: 'endDay'},
            { data: 'null',
            "render": function ( data, type, row, meta ) {
                return '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#alumnosRevisionModal">Ver</button>';
              }
        }

        ]
    });
    fetchReclamation();
});

  async function fetchReclamation() {
    let response = await fetch('http://localhost:8080/reclamation/all');
    let data = await response.json();
    console.log(data);
    console.log(t);
    var t = $('#publishTable').DataTable();
    t.rows.add(data).draw();
  };
  