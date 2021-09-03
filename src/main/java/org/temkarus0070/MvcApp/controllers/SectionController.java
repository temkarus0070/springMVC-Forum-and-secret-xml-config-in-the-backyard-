package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.temkarus0070.MvcApp.dao.SectionDAO;
import org.temkarus0070.MvcApp.models.Section;

import java.util.List;
import java.util.Optional;

@RestController
public class SectionController {

    private SectionDAO sectionDAO;

    @Autowired
    public void setSectionDAO(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @GetMapping( path = "section/")
    public List<Section> get(){
        return sectionDAO.findAll();
    }

    @PostMapping(path = "section/")
    public void add(Section section){
        sectionDAO.save(section);
    }

    @DeleteMapping(path = "section/")
    public void delete(Section section){
        sectionDAO.delete(section);
    }

    public Optional<Section> get(int id){
        return sectionDAO.findById(id);
    }

}
