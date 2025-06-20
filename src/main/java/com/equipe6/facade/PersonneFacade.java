package com.equipe6.facade;

import com.equipe6.dao.PersonneDAO;
import com.equipe6.dto.PersonneDTO;
import com.equipe6.model.Personne;

public class PersonneFacade {

    private final PersonneDAO personneDAO;

    public PersonneFacade(PersonneDAO personneDAO) {
        this.personneDAO = personneDAO;
    }

    public PersonneDTO getPersonneById(String id) {
        Personne personne = personneDAO.findById(id);
        return (personne != null) ? getPersonneDTO(personne) : null;
    }

    public PersonneDTO getPersonneByName(String name) {
        Personne personne = personneDAO.findByName(name);
        return (personne != null) ? getPersonneDTO(personne) : null;
    }


    public PersonneDTO getPersonneDTO(Personne personne) {
        return new PersonneDTO(
                personne.getNom(),
                personne.getDateNaissance().toString(),
                personne.getLieuNaissance(),
                personne.getPhoto(),
                personne.getBiographie()
        );
    }
}
