package br.ufes.inf.labes.mysystem.subsystem.service;

import br.ufes.inf.labes.jbutler.ejb.application.CrudService;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import jakarta.ejb.Local;

@Local
public interface ManageMyObjectsService extends CrudService<MyObject> {

}
