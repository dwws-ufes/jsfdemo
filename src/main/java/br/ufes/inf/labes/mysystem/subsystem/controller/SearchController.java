package br.ufes.inf.labes.mysystem.subsystem.controller;

import java.io.Serializable;
import java.util.List;
import br.ufes.inf.labes.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import br.ufes.inf.labes.mysystem.subsystem.persistence.MyObjectDAO;
import br.ufes.inf.labes.mysystem.subsystem.service.ManageMyObjectsService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class SearchController implements Serializable {
  @EJB
  private ManageMyObjectsService manageMyObjectsService;

  private long objectId;

  private MyObject result;

  private PersistentObjectConverterFromId<MyObject> myObjectConverter;

  @Inject
  void initConverter(MyObjectDAO myObjectDAO) {
    myObjectConverter = new PersistentObjectConverterFromId<>(myObjectDAO);
  } 

  public PersistentObjectConverterFromId<MyObject> getMyObjectConverter() {
    return myObjectConverter;
  }

  public long getObjectId() {
    return objectId;
  }

  public void setObjectId(long objectId) {
    this.objectId = objectId;
  }

  public MyObject getResult() {
    return result;
  }

  public void setResult(MyObject result) {
    this.result = result;
  }

  public List<MyObject> getExistingMyObjects() {
    return manageMyObjectsService.list();
  }

  public String search() {
    result = manageMyObjectsService.retrieve(objectId);
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.put("searchController", this);
    return "/subsystem/search/result.xhtml?faces-redirect=true";
  }

  public String view() {
    if (result != null) objectId = result.getId();
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.put("searchController", this);
    return "/subsystem/search/result.xhtml?faces-redirect=true";
  }
}
