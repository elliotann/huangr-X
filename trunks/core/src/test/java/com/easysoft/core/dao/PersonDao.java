package com.easysoft.core.dao;

import com.easysoft.core.model.Person;
import org.mangocube.corenut.pf.core.DataAccessObject;
import org.mangocube.corenut.pf.core.annotation.DaoConfig;
import org.mangocube.corenut.pf.core.annotation.Insert;
import org.mangocube.corenut.pf.core.annotation.Parameter;
import org.mangocube.corenut.pf.core.annotation.Select;



/**
 * author:elliotann
 * modify time��2012-5-13
 * desc:description of the Class
 */
@DaoConfig(resource = "com/easysoft/core/dao/PersonDao.xml")
public interface PersonDao extends DataAccessObject{
	@Insert
	public void savePerson(@Parameter(name = "person") Person person);
	@Select
	public Person retrievePersonById(int id);
	
	@Select
	public Person retrievePersonById();
}
