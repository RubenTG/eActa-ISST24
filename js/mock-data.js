const usuarios = [
	{
		"id": 1,
		"name": "profesor1",
		"asignaturas": [{ "name": "CORE", "curso": "3º", "plan": "GIST" },
		{ "name": "PROG", "curso": "1º", "plan": "GIST" },
		{ "name": "ISST", "curso": "4º", "plan": "GIST" },
		{ "name": "APSV", "curso": "1º", "plan": "MUIT" }],
		"profile": {
			"isAdmin": true,
			"isSecretaria": false,
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
			"isSecretaria": false,
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



	},
	{
		"id": 3,
		"name": "secretaria1",
		"asignaturas": [],
		"profile": {
			"isAdmin": true,
			"isSecretaria": true,
			"username": "secretaria",
			"password": "secretaria",
			"profileId": null,
			"profileName": null,
			"photo": {
				"filename": "spg072.jpg",
				"mime": "image/jpeg",
				"url": "https://es.pngtree.com/freepng/vector-users-icon_4144740.html"
			}
		}
	}
	];

export default usuarios;