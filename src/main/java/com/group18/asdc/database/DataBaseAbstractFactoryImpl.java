package com.group18.asdc.database;

public class DataBaseAbstractFactoryImpl implements DataBaseAbstractFactory{

	@Override
	public IDatabaseConfiguration getDatabaseConfiguration() {
		return new DefaultDatabaseConfiguration();
	}

}
