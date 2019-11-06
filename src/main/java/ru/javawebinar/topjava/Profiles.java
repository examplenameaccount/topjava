package ru.javawebinar.topjava;

public class Profiles {
    public static final String
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    public static final String
            POSTGRES_JDBC_REPOSITORY = "postgresJDBC",
            HSQL_JDBC_REPOSITORY = "hsqldbJDBC";

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_DB;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQL_DB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }

    public static String getActiveJDBCRepository() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_JDBC_REPOSITORY;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQL_JDBC_REPOSITORY;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find JDBC repository implementation");
            }
        }
    }
}
