package br.ufes.inf.labes.mysystem.subsystem.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("cpfValidator")
public class CPFValidator implements Validator<String> {
  @Override
  public void validate(FacesContext context, UIComponent component, String value)
      throws ValidatorException {
    if (! ValidaCPF.isCPF(value)) {
      FacesMessage msg = new FacesMessage("MyString does not contain a valid CPF");
      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(msg);
    }
  }
}
