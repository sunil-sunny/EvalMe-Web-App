package com.group18.asdc;

import com.group18.asdc.dao.GroupFormationDao;
import com.group18.asdc.dao.GroupFormationDaoImpl;
import com.group18.asdc.service.GroupFormationService;
import com.group18.asdc.service.GroupFormationServiceImpl;

public class GroupFormationConfig {

	public static GroupFormationConfig singletonInstance = null;
	private GroupFormationService theGroupFormationService;
	private GroupFormationDao theGroupFormationDao;
	
	public GroupFormationConfig() {
		this.setTheGroupFormationService(new GroupFormationServiceImpl());
		this.setTheGroupFormationDao(new GroupFormationDaoImpl());
	}
	
	public static GroupFormationConfig getSingletonInstance() {
		if(null==singletonInstance) {
			singletonInstance = new GroupFormationConfig();
		}
		return singletonInstance;
	}

	public GroupFormationService getTheGroupFormationService() {
		return theGroupFormationService;
	}

	public void setTheGroupFormationService(GroupFormationService theGroupFormationService) {
		this.theGroupFormationService = theGroupFormationService;
	}

	public GroupFormationDao getTheGroupFormationDao() {
		return theGroupFormationDao;
	}

	public void setTheGroupFormationDao(GroupFormationDao theGroupFormationDao) {
		this.theGroupFormationDao = theGroupFormationDao;
	}
}
