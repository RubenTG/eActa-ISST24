$(document).ready(function(){

  $('#publishTable').DataTable({
      "ajax" : {
        //la url llevará como parámetro el codigo de la asignatura
        url : "../ISST-revision.json",
        dataSrc : "revision"
      },
      "columns": [
          { data: 'revisionDateStart',
            render : function(data, type, row, meta){
              var date = new Date(data);
              var day = date.getDate();
              var month = date.getMonth();
              var year = date.getFullYear();
            return '<p>'+ day + '/'+ month + '/'+ year +'</p>'
          }
        },
          { data: 'revisionDateFinish',
            render : function(data, type, row, meta){
              var date = new Date(data);
              var day = date.getDate();
              var month = date.getMonth();
              var year = date.getFullYear();
              return '<p>'+ day + '/'+ month + '/'+ year +'</p>'
            }
          },
          { data: 'null',
          "render": function ( data, type, row, meta ) {
              return '<button type="button" class="btn btn-primary" onClick="searchRevisionStudents()">Ver</button>';
            }
      }

      ]
  });
  //fetchRevision();
});


/*
async function fetchRevision() {
  let response = await fetch('http://localhost:8080/reclamation/all');
  let data = await response.json();
  console.log(data);
  console.log(t);
  var t = $('#publishTable').DataTable();
  t.rows.add(data).draw();
};
*/

async function newRevision(form){
var revision = {
  'revisionDateStart' : form.revisionDateStart.value,
  'revisionDateFinish' : form.revisionDateFinish.value,
}

var postJSON = JSON.stringify(revision);
console.log(postJSON);

let response = await fetch('/subject/subjectcode/revision', {
  method: 'POST',
  body: postJSON
});

  let data = await response.json();
  console.log(data);

  var publishTable = $('#publishTable').DataTable();
  publishTable.draw();
};

function searchRevisionStudents() {
  var marksTable = $('#marksTable').DataTable()

  marksTable
      .columns(3)
      .search('PENDIENTE')
      .draw()

}