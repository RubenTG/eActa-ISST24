function subir(){
console.log(2);
//Preparamos la subida del pdf y nos aseguramos que es un PDF

var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.pdf)$/;
    if (regex.test($("#subirpdf2").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var reader = new FileReader();
            reader.onload = function (e) {

				//a partir de aquí
				var typedarray = new Uint8Array(this.result);
				PDFJS.getDocument(typedarray).then(function(pdf) {
				// you can now use *pdf* here
				console.log("the pdf has ",pdf.numPages, "page(s).")
				pdf.getPage(pdf.numPages).then(function(page) {
				// you can now use *page* here
				/*var viewport = page.getViewport(2.0);
				var canvas = document.querySelector("canvas")
				canvas.height = viewport.height;
				canvas.width = viewport.width;

				page.render({
					canvasContext: canvas.getContext('2d'),
					viewport: viewport
				});*/
			});

		});
	};

	reader.readAsArrayBuffer($("#subirpdf2")[0].files[0]);
	console.log("funciona");
}else {
	alert("This browser does not support HTML5.");
}
} else {
alert("Please upload a valid PDF file.");
}
}
