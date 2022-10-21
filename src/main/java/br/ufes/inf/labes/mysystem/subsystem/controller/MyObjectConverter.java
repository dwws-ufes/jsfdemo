package br.ufes.inf.labes.mysystem.subsystem.controller;

import br.ufes.inf.labes.mysystem.subsystem.domain.MyObject;
import br.ufes.inf.labes.mysystem.subsystem.service.ManageMyObjectsService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("myObjectConverter")
@Dependent
public class MyObjectConverter implements Converter<MyObject> {
  @EJB
  private ManageMyObjectsService manageMyObjectsService;

  @Override
  public MyObject getAsObject(FacesContext context, UIComponent component, String value) {
		if ((value != null) && (value.trim().length() > 0)) {
			try {
				Long id = Long.valueOf(value);
				return manageMyObjectsService.retrieve(id);
			} catch (NumberFormatException e) {
				System.out.println("Value is not a number (Long): " + value);
				return null;
			}
		}

    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, MyObject value) {
    return (value == null) ? "" : "" + value.getId();
  }
}
