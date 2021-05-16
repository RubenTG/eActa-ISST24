function updateMark(form) {
  var name = form.name.value;
  var nota = form.newMark.value;
  var mail = form.mailStudent.value;


  var revision = {
    'Name': name,
    'nota': nota,
    'mailStudent': mail,
    //TODO: Poner de donde se saca el subjectCode de la asignatura
    //'subjectCode' : localStorage.getItem(subjectCode)
  }

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