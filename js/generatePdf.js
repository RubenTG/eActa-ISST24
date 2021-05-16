function generarPDF() {

    //Falta cambiar ruta y añadir parámetros de la cabecera
    fetch("./ejemplo.json")
        .then(res => res.json())
        .then(data => {
            var doc = new jsPDF();
            var finalY = 20;


            //Comienzo edición fichero PDF
            doc.setFontSize(20);
            doc.text('Universidad Politécnica de Madrid', 20, finalY);
            finalY = finalY + 20;


            doc.setFontSize(12);
            doc.text('Asignatura: ISST', 20, finalY);
            finalY = finalY + 10;
            //Pintamos al tribunal
            var professorsBody = [];
            data.miembrosTribunal.forEach(function (miembro, i) {
                professorsBody.push([miembro.professorName, miembro.mailProfessor])
            });
            doc.text('Tribunal', 20, finalY);
            finalY = finalY + 5;
            doc.autoTable({
                startY: finalY,
                head: [['Miembro', 'Correo']],
                body: professorsBody,
                theme: 'striped',
                headStyles: {
                    fillColor: [24, 103, 183],
                    halign: 'center'
                },
                bodyStyles: {
                    halign: 'center'
                },
            });
            finalY = finalY + 20 * professorsBody.length;
            //Pintamos las calificaciones
            var studentsBody = [];
            data.students.forEach(function (student, i) {
                studentsBody.push([student.studentName, student.mailStudent, student.nota])
            });
            doc.text('Calificaciones', 20, finalY);
            finalY = finalY + 5;

            doc.autoTable({
                startY: finalY,
                head: [['Alumno', 'Correo', 'Nota']],
                body: studentsBody,
                showHead: 'everyPage',
                theme: 'striped',
                headStyles: {
                    fillColor: [24, 103, 183],
                    halign: 'center'
                },
                bodyStyles: {
                    halign: 'center'
                },
            });
            finalY = finalY + 20 * studentsBody.length;

            var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
            var f = new Date();
            doc.text("Firmado a " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear(),20,finalY);
            finalY = finalY + 5;

            doc.save('Acta.pdf');

        });
}

