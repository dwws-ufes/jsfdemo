package br.ufes.inf.labes.mysystem.subsystem.persistence;

import br.ufes.inf.labes.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.labes.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.labes.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject_;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Stateless
public class MyObjectJPADAO extends BaseJPADAO<MyObject> implements MyObjectDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public MyObject retrieveByMyNumber(int myNumber)
            throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
        // Constructs the query over the Academic class.
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyObject> cq = cb.createQuery(MyObject.class);
        Root<MyObject> root = cq.from(MyObject.class);

        // Filters the query with the email.
        cq.where(cb.equal(root.get(MyObject_.myNumber), myNumber));
        MyObject result = executeSingleResultQuery(cq, myNumber);
        return result;
    }
}
