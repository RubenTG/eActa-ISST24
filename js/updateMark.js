function updateMark(form){
    var revision = {
        'Name' : form.name.value,
        'mark' : form.newMark.value,
        'subject' : se
      }
      
      var postJSON = JSON.stringify(revision);

      //TO-DO
      //FALTA PONER LA RUTA BIEN
      let response = await fetch('/subject/subjectcode/revision', {
        method: 'PUT',
        body: postJSON
      });
      
        let data = await response.json();
        console.log(data);
      
        var marksTable = $('#marksTable').DataTable();
        marksTable.draw();

}