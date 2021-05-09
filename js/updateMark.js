function updateMark(form) {
  var name = form.name.value;
  var mark = form.newMark.value;


  var revision = {
    'Name': name,
    'mark': mark,
    //TODO: Poner de donde se saca el subjectCode de la asignatura
    'subjectCode' : localStorage.getItem(subjectCode)
  }

  //Solo actualiza la tabla, no manda los datos
  var newData = [name, mark];
  var marksTable = $("#marksTable").DataTable();
  marksTable.row(name).data(newData).draw();


  var postJSON = JSON.stringify(revision);
  console.log(postJSON);

  //TO-DO
  //FALTA PONER LA RUTA BIEN
  /*
  let response = await fetch('/subject/subjectcode/revision', {
    method: 'PUT',
    body: postJSON
  });
  
    let data = await response.json();
    console.log(data);
  
    //var marksTable = $('#marksTable').DataTable();
    //marksTable.draw();
  */
}