package org.temkarus0070.MvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.temkarus0070.MvcApp.dao.Repositories.CommentRepository;
import org.temkarus0070.MvcApp.dao.Repositories.SectionRepository;
import org.temkarus0070.MvcApp.models.Section;

import java.util.List;

@RestController()
@RequestMapping(path = "/sections")
@CrossOrigin(origins = "http://localhost:4200")
public class SectionController {


    private SectionRepository sectionDAO;

    @Autowired
    public void setSectionDAO(SectionRepository sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @GetMapping()
    public List<Section> get(){
        return sectionDAO.findAll();
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/new")
    public void add(@RequestBody Section section){
        sectionDAO.save(section);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping()
    public void delete(Section section){
        sectionDAO.delete(section);
    }


}
