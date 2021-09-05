package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.SectionDAO;
import org.temkarus0070.MvcApp.models.Section;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "/sections")
@CrossOrigin(origins = "http://localhost:4200")
public class SectionController {

    private SectionDAO sectionDAO;

    @Autowired
    public void setSectionDAO(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @GetMapping()

    public List<Section> get(){
        return sectionDAO.findAll();
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping()
    public void add(Section section){
        sectionDAO.save(section);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping()
    public void delete(Section section){
        sectionDAO.delete(section);
    }

    public Optional<Section> get(int id){
        return sectionDAO.findById(id);
    }

}
