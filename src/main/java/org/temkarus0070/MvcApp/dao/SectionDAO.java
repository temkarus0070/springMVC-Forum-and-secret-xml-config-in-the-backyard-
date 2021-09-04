package org.temkarus0070.MvcApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temkarus0070.MvcApp.models.Section;


@org.springframework.stereotype.Repository
public interface SectionDAO extends JpaRepository<Section,Integer> {

}
