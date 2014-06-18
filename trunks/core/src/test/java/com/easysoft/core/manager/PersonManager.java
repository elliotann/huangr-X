package com.easysoft.core.manager;

import com.easysoft.core.model.Person;
import org.mangocube.corenut.scm.PublishedService;



/**
 * author:elliotann
 * modify time：2012-5-13
 * desc:description of the Class
 */
@PublishedService
public interface PersonManager {
	public void savePerson(Person person);
	
	public Person reteivePersonById(int id);
}
