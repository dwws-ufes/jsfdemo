package br.ufes.inf.labes.mysystem.subsystem.service;

import br.ufes.inf.labes.jbutler.ejb.application.CrudException;
import br.ufes.inf.labes.jbutler.ejb.application.CrudServiceImpl;
import br.ufes.inf.labes.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.labes.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.labes.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import br.ufes.inf.labes.mysystem.subsystem.persistence.MyObjectDAO;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
@PermitAll
public class ManageMyObjectsServiceImpl extends CrudServiceImpl<MyObject>
        implements ManageMyObjectsService {
    @EJB
    private MyObjectDAO myObjectDAO;

    @Override
    public BaseDAO<MyObject> getDAO() {
        return myObjectDAO;
    }

    @Override
    public void validateCreate(MyObject entity) throws CrudException {
        // Rule: cannot create a new myObject with the same myNumber.
        try {
            myObjectDAO.retrieveByMyNumber(entity.getMyNumber());
        } catch (PersistentObjectNotFoundException e) {
            // This is the OK result.
            return;
        } catch (MultiplePersistentObjectsFoundException e) {
            // This shouldn't happen!
            // This should be logged in a real system. Here, will just proceed to report validation
            // error.
        }

        // A MyObject already exists with the given myNumber. Throw a CRUD exception.
        CrudException crudException =
                addFieldValidationError(null, "Could not create new MyObject: " + entity,
                        "myNumberField", "manageMyObjects.error.existingMyNumber");
        throw crudException;
    }
}
