const usuarios = [
	{
		"id": 1,
		"name": "profesor1",
		"asignaturas": [{ "name": "CORE", "curso": "3º", "plan": "GIST" },
		{ "name": "PROG", "curso": "1º", "plan": "GIST" },
		{ "name": "CDPS", "curso": "4º", "plan": "GIST" },
		{ "name": "APSV", "curso": "1º", "plan": "MUIT" },
		{ "name": "RSTC", "curso": "2º", "plan": "GIST" },
		{"name": "ADSW", "curso": "2º", "plan": "GIST" }],
		"profile": {
			"isAdmin": true,
			"username": "profesor",
			"password": "profesor",
			"profileId": null,
			"profileName": null,
			"photo": {
				"filename": "spg072.jpg",
				"mime": "image/jpeg",
				"url": "https://es.pngtree.com/freepng/vector-users-icon_4144740.html"
			}
		}
	},
	{
		"id": 2,
		"name": "alumno1",
		"asignaturas": [{ "name": "CORE", "curso": "3º", "plan": "GIST", "calificación": "6" },
		{ "name": "PROG", "curso": "1º", "plan": "GIST", "calificación": "7" },
		{ "name": "CDPS", "curso": "4º", "plan": "GIST", "calificación": "8" },
		{ "name": "APSV", "curso": "1º", "plan": "MUIT", "calificación": "9" }],
		"profile": {
			"isAdmin": false,
			"username": "alumno",
			"password": "alumno",
			"profileId": null,
			"profileName": null,
			"photo": {
				"filename": "spg072.jpg",
				"mime": "image/jpeg",
				"url": "https://es.pngtree.com/freepng/vector-users-icon_4144740.html"
			}
		}



	}];

export default usuarios;