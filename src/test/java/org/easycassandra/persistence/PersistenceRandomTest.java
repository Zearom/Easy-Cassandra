package org.easycassandra.persistence;

import org.easycassandra.bean.model.Person;
import org.easycassandra.bean.model.Sex;
import org.junit.Assert;
import org.junit.Test;

public class PersistenceRandomTest {

	@Test
	public void sizeTest(){
    	EasyCassandraManager.closeClients();
    	EasyCassandraManager.getPersistence("javabahia", "localhost", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "node2", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "node3", 9160);
    	Persistence persistence=EasyCassandraManager.getPersistenceRandom("javabahia");
    	
    	Assert.assertTrue(persistence.size()==3);
    }
	@Test
	public void sizeTwoTest(){
    	EasyCassandraManager.closeClients();
    	EasyCassandraManager.getPersistence("javabahia", "localhost", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "node2", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "node3", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "localhost", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "node2", 9160);
    	EasyCassandraManager.getPersistence("javabahia", "node3", 9160);
    	PersistenceRandom persistence=(PersistenceRandom)EasyCassandraManager.getPersistenceRandom("javabahia");
    	
    	Assert.assertTrue(persistence.size()==3);
    }
	
	@Test
	public void insertTest(){
		Persistence persistence=EasyCassandraManager.getPersistenceRandom("javabahia");
		for(long index=100;index<10000l;index++){
			Person person=getPerson();
			person.setId(index);
			person.setName(person.getName().concat(String.valueOf(index)));
			if(!persistence.insert(person)){
				Assert.assertTrue("Some error in insertion",false);
			}
			if(index%1000==0){
				System.out.println("in index number "+index);
			}
			
		}
		Assert.assertTrue("All insertion is ok",true);
	}
	
	  private Person getPerson() {
	        Person person = new Person();
	        person.setYear(10);
	        person.setName("Name Person ");
	        person.setSex(Sex.MALE);
	        return person;
	    }
}
