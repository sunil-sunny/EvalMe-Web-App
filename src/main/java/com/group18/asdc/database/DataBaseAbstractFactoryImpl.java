package com.group18.asdc.database;

import java.sql.Connection;

public class DataBaseAbstractFactoryImpl implements DataBaseAbstractFactory{

	@Override
	public IDatabaseConfiguration getDatabaseConfiguration() {
		return new DefaultDatabaseConfiguration();
	}

	@Override
	public SQLMethods getSqlMethods(Connection connection) {
		return new SQLMethods(connection);
	}

}
