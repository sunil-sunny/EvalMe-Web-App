package com.group18.asdc.database;

public enum SQLQueries {
    USER_AUTH_BY_EMAIL_PASSWORD("select * from User where EMAIL = ? and PASSWORD = ?"),
    USER_AUTH_BY_EMAIL("select EMAIL, PASSWORD, ENABLED from User where EMAIL = ?");

    private final String sqlQuery;

    private SQLQueries(String sqlQuery){
        this.sqlQuery = sqlQuery;
    }

    @Override
    public String toString(){
        return sqlQuery;
    }
    
}