//IMPORTAMOS LA LIBRERÍA "pdfmake"
import pdfMake from 'pdfmake/build/pdfmake.js';
import pdfFonts from 'pdfmake/build/vfs_fonts.js';

pdfMake.vfs = pdfFonts.pdfMake.vfs;

const TRIBUNAL = [
    {"Nombre":"John", "primerApellido":"Doe", "segundoApellido":"Doe" }, 
    {"Nombre":"Anna", "primerApellido":"Smith", "segundoApellido":"Doe"},
    {"Nombre":"Peter", "primerApellidotName":"Jones", "segundoApellido":"Doe"}
];
const Alumnos = [
    {"Nombre":"John", "primerApellido":"Doe", "segundoApellido":"Doe", "Nota":"Doe" }, 
    {"Nombre":"Anna", "primerApellido":"Smi", "segundoApellido":"Doe", "Nota":"Doe" },
    {"Nombre":"Pete", "primerApellido":"Jon", "segundoApellido":"Doe", "Nota":"Doe" }
];
const document = { content: [{text: 'Tribunal', fontStyle: 15, lineHeight: 2}] }
TRIBUNAL.forEach(miembro => {
    document.content.push({
        columns: [
            { text: 'Nombre', width: 60 },
            { text: ':', width: 10 },
            { text:miembro.Nombre, width: 50 },
            { text: 'primerApellido', width: 60 },
            {text: ':', width: 10 }, 
            { text:miembro.primerApellido, width: 50 },
            { text: 'segundoApellido', width: 60 },
            {text: ':', width: 10 }, 
            { text: miembro.segundoApellido, width: 50}
        ],
        lineHeight: 2
    });
});
Alumnos.forEach(alumno => {
    document.content.push({
        columns: [
            { text: 'Nombre', width: 60 },
            { text: ':', width: 10 },
            { text: alumno.Nombre, width: 50 },
            { text: 'primerApellido', width: 60 },
            { text: ':', width: 10 }, 
            { text: alumno.primerApellido, width: 50},
            { text: 'segundoApellido', width: 60 },
            { text: ':', width: 10 }, 
            { text: alumno.segundoApellido, width: 50},
            { text: 'Nota', width: 60 },
            { text: ':', width: 10 }, 
            { text: alumno.Nota, width: 50}
        ],
        lineHeight: 2
    });
});
pdfMake.createPdf(document).download();