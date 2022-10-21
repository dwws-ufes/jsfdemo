package br.ufes.inf.labes.mysystem.subsystem.controller;

import br.ufes.inf.labes.jbutler.ejb.controller.JSFController;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import br.ufes.inf.labes.mysystem.subsystem.service.ManageMyObjectsService;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CreateNewObjectController extends JSFController {
  @EJB
  private ManageMyObjectsService manageMyObjectsService;

  private MyObject myObject = new MyObject();

  public MyObject getMyObject() {
    return myObject;
  }

  public void create() {
    manageMyObjectsService.create(myObject);
    addGlobalI18nMessage("msgsSubsystem", FacesMessage.SEVERITY_INFO, "createNewObject.info.createSuccessful", myObject);
    myObject = new MyObject();
  }
}
