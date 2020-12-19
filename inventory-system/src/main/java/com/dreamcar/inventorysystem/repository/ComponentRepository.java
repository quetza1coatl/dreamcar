package com.dreamcar.inventorysystem.repository;

import com.dreamcar.inventorysystem.model.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "components")
interface ComponentRepository extends CrudRepository<Component, Integer> {

    @Override
    List<Component> findAll();
}
