package br.ufes.inf.labes.mysystem.subsystem.persistence;

import br.ufes.inf.labes.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.labes.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.labes.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import jakarta.ejb.Local;

@Local
public interface MyObjectDAO extends BaseDAO<MyObject> {
  public MyObject retrieveByMyNumber(int myNumber)
      throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
