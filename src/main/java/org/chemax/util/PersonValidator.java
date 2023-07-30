package org.chemax.util;

import org.chemax.models.Person;
import org.chemax.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person)o;

        if (personDetailsService.findPerson(person).isPresent()) {
            errors.rejectValue("username", "",
                    "Пользователь с таким именем пользователя уже существует");
        }
    }
}
