package br.ufes.inf.labes.mysystem.subsystem.controller;

import java.time.LocalDate;
import java.util.Random;

import org.primefaces.PrimeFaces;

import br.ufes.inf.labes.jbutler.ejb.application.CrudService;
import br.ufes.inf.labes.jbutler.ejb.controller.CrudController;
import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import br.ufes.inf.labes.mysystem.subsystem.service.ManageMyObjectsService;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ManageMyObjectsController extends CrudController<MyObject> {
    @EJB
    private ManageMyObjectsService manageMyObjectsService;

    @Override
    protected CrudService<MyObject> getCrudService() {
        return manageMyObjectsService;
    }

    public void createRandomObjects() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(1000);
            MyObject obj = new MyObject();
            obj.setMyBoolean(num % 2 == 0);
            obj.setMyDate(LocalDate.now().plusDays(num));
            obj.setMyNumber(num);
            obj.setMyString("Random Object #" + num);

            manageMyObjectsService.create(obj);

            // If using regular listing, add the new object to the list.
            if (entities != null)
                entities.add(obj);
        }

        // If using the lazy listing, reload the count.
        if (entities == null)
            reloadLazyCount();

        addGlobalI18nMessage("msgsSubsystem", FacesMessage.SEVERITY_INFO,
                "manageMyObjects.text.createRandomObjects.info.summary",
                "manageMyObjects.text.createRandomObjects.info.detail");
        PrimeFaces.current().ajax().update("form:dt-entities");
    }
}
