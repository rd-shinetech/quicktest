package co.shinetech.dao.impl;

import java.util.ArrayList;
import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.Group;
import co.shinetech.dto.Domain;

public class GroupDAOImp implements DAOProvider<Group>{

	@Override
	public void create(Group d) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_GROUP, d);
		
	}

	@Override
	public void update(Group d) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_GROUP, d);
		
	}

	@Override
	public Group retrieveByID(long ID) throws PersistenceException {
		Domain d = SerializerDB.selectById(SerializerDB.TABLE_GROUP, ID);
		return (Group) d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> retrieveAll() throws PersistenceException {
		ArrayList<? extends Domain> r = SerializerDB.selectAll(SerializerDB.TABLE_GROUP);
		return (ArrayList<Group>) r;
	}

	@Override
	public void delete(long ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_GROUP, ID);
		
	}

	@Override
	public int count() throws PersistenceException {
		int c = SerializerDB.count(SerializerDB.TABLE_GROUP);
		return (int) c;
	}

	@Override
	public long nextId() throws PersistenceException {
		return SerializerDB.nextId(SerializerDB.TABLE_GROUP);
	}
}
