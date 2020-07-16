package com.group18.asdc.database;

import java.sql.Connection;

public interface DataBaseAbstractFactory {

	public IDatabaseConfiguration getDatabaseConfiguration();

	public ISQLMethods getSQLMethods(Connection connection);
}
