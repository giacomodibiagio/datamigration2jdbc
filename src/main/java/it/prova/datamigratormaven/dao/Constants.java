package it.prova.datamigratormaven.dao;

public interface Constants {

	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String CONNECTION_URL_VECCHIO = "jdbc:mysql://localhost:3306/schemaold?user=root&password=root&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	public static final String CONNECTION_URL_NUOVO = "jdbc:mysql://localhost:3306/schemanew?user=root&password=root&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";


}
