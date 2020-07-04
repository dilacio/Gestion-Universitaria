package dao.queries;

public enum queriesCurso {

	INSERT_INTO_CURSOS("INSERT INTO CURSOS (ID_MATERIA, ANIO ,ID_DOCENTE, CUATRIMESTRE, FECHA_INICIO, FECHA_FIN, BAJA) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)"),

	INSERT_INTO_CURSOS_POR_ALUMNOS("INSERT INTO CURSOSXALUMNOS (ID_CURSO, ID_ALUMNO, ESTADO, BAJA,PARCIAL1,PARCIAL2,RECUPERATORIO1,RECUPERATORIO2) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),

	GET_ULTIMO_CURSO("SELECT MAX(ID_CURSO) AS  ID_CURSO FROM CURSOS "),

	LISTAR_CURSOS("SELECT C.ID_CURSO, C.ID_MATERIA,M.DESCRIPCION, C.ANIO, C.ID_DOCENTE, P.NOMBRE, P.APELLIDO, CUATRIMESTRE, APROBADOS, DESAPROBADOS \n"
					+ "FROM CURSOS AS C \n" 
					+ "INNER JOIN MATERIA AS M ON C.ID_MATERIA = M.ID_MATERIA \n"
					+ "INNER JOIN PERSONAS AS P ON P.LEGAJO = C.ID_DOCENTE \n" 
					+ "WHERE BAJA <> 1"),

	BORRAR_CURSO("UPDATE CURSOS SET BAJA = 1  WHERE ID_CURSO = ?"),

	BORRAR_ALUMNOS_DE_CURSO("UPDATE CURSOSXALUMNOS SET BAJA = 1 WHERE ID_CURSO = ?"),

	UPDATE_CURSO("UPDATE CURSOS SET ID_MATERIA = ?, CUATRIMESTRE = ?, ANIO = ?, ID_DOCENTE = ?  WHERE ID_CURSO =?"),

	LISTAR_CURSOS_POR_ID("SELECT C.ID_CURSO, C.ID_MATERIA, M.DESCRIPCION, C.ANIO, C.ID_DOCENTE,P.NOMBRE, P.APELLIDO, CUATRIMESTRE, APROBADOS, DESAPROBADOS "
					+ "FROM CURSOS AS C " 
					+ "INNER JOIN MATERIA AS M ON C.ID_MATERIA = M.ID_MATERIA "
					+ "INNER JOIN PERSONAS AS P ON P.LEGAJO = C.ID_DOCENTE " 
					+ "WHERE BAJA <> 1 AND C.ID_CURSO =  ?"),

	GET_CURSO_POR_PROFESOR("SELECT C.ID_CURSO, C.ANIO, C.CUATRIMESTRE, M.DESCRIPCION, P.APELLIDO "
					+ "FROM PERSONAS AS P "
					+ "INNER JOIN CURSOS AS C ON C.ID_DOCENTE = P.LEGAJO "
					+ "INNER JOIN MATERIA AS M ON M.ID_MATERIA = C.ID_MATERIA "
					+ "WHERE P.ID_TIPO = 1 AND C.BAJA = 0 AND P.LEGAJO = ?"), 
	
	
	
	UPDATE_NOTAS("UPDATE CURSOSXALUMNOS AS C SET C.PARCIAL1 = ?, C.PARCIAL2= ?, C.RECUPERATORIO1= ?, C.RECUPERATORIO2 = ?, C.ESTADO= ? WHERE C.ID_CURSO = ? AND C.ID_ALUMNO = ?"), 
	
	EXISTE_REGISTRO("SELECT C.BAJA FROM CURSOSXALUMNOS AS C WHERE C.ID_ALUMNO = ? AND C.ID_CURSO = ?"),
	
	VOLVER_A_INSCRIBIR("UPDATE CURSOSXALUMNOS SET BAJA = 0  WHERE ID_CURSO = ? AND ID_ALUMNO = ?"),
	
	BORRAR_ALUMNO_DE_CURSO("UPDATE CURSOSXALUMNOS SET BAJA = 1 WHERE ID_CURSO = ? AND ID_ALUMNO = ?"), 
	
	GET_NOTAS("SELECT C.ID_CURSO, C.PARCIAL1 , C.PARCIAL2, C.RECUPERATORIO1, C.RECUPERATORIO2, C.ESTADO, P.LEGAJO "
					+ "FROM CURSOSXALUMNOS AS C "
					+ "INNER JOIN PERSONAS AS P ON P.LEGAJO = C.ID_ALUMNO"), 
	
	APROBAR_ALUMNO("UPDATE CURSOSXALUMNOS AS C SET C.APROBO = 1 WHERE C.ID_CURSO = ? AND C.ID_ALUMNO = ?"), 
	
	GET_PORCENTAJE_APROBADOS("SELECT DISTINCT C.ID_CURSO, (("
					+ "(SELECT COUNT(D.ID_ALUMNO) FROM CURSOSXALUMNOS AS D  WHERE D.APROBO=1 AND D.ID_CURSO = C.ID_CURSO)"
					+ "/(SELECT COUNT(R.ID_ALUMNO) FROM CURSOSXALUMNOS AS R WHERE R.ID_CURSO = C.ID_CURSO))*100) AS APROBADOS "
					+ "FROM CURSOSXALUMNOS AS C;"), 
	
	UPDATE_APROBADOS("UPDATE CURSOS AS C SET C.APROBADOS = ? WHERE C.ID_CURSO = ?"), 
	
	UPDATE_DESAPROBADOS("UPDATE CURSOS AS C SET C.DESAPROBADOS = ? WHERE C.ID_CURSO= ?"), 
	
	GET_INFORME("SELECT COUNT(CU.ID_ALUMNO) AS CANTIDAD, C.APROBADOS, C.DESAPROBADOS, C.ANIO, C.CUATRIMESTRE, P.APELLIDO, M.DESCRIPCION "
			+ "FROM CURSOS AS C "
			+ "INNER JOIN MATERIA AS M ON M.ID_MATERIA = C.ID_MATERIA "
			+ "INNER JOIN PERSONAS AS P ON P.LEGAJO = C.ID_DOCENTE "
			+ "INNER JOIN CURSOSXALUMNOS AS CU ON C.ID_CURSO = CU.ID_CURSO "
			+ "WHERE C.FECHA_INICIO  >= ?\n" 
			+ "AND C.FECHA_FIN <= ?  GROUP BY C.ID_CURSO"),

	;

	private String query;

	private queriesCurso(String valor) {
		this.setQuery(valor);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}