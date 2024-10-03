package me.cj;

public class DatabaseInfo {

    public static String URL = "jdbc:mysql://localhost:3306/";
    public static String TABLE = "medical_db";
    public static String USER = "root";
    public static String PASSWORD = "";

    public static String getMedicalUrl() {
        return URL + TABLE;
    }

}
