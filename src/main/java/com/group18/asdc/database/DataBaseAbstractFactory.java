package com.group18.asdc.database;

import java.sql.Connection;

public interface DataBaseAbstractFactory {

	public IDatabaseConfiguration getDatabaseConfiguration();

	public SQLMethods getSqlMethods(Connection connection);
}
