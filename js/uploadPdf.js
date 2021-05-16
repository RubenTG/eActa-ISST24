function subir(){

//Leemos el pdf
var pdf = document.getElementById("subirpdf2").files[0];
//Preparamos el envío
var formData = new FormData();
formData.append("pdf",pdf);
fetch('/upload/image', {
	method: "POST",
	body: formData
	});

}
