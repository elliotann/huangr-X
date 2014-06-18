package com.easysoft.core.manager;


import com.easysoft.core.dao.PersonDao;
import com.easysoft.core.model.Person;

/**
 * author:elliotann
 * modify time��2012-5-13
 * desc:description of the Class
 */

public class PersonManagerImpl implements PersonManager{
	private PersonDao personDao;
	public void savePerson(Person person){
		personDao.savePerson(person);
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	@Override
	public Person reteivePersonById(int id) {
		// TODO Auto-generated method stub
		return personDao.retrievePersonById(id);
	}
	
}
