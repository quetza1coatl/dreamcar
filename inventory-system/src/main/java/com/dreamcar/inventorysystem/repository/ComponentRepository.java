package com.dreamcar.inventorysystem.repository;

import com.dreamcar.inventorysystem.model.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComponentRepository extends CrudRepository<Component, Integer> {

    @Override
    List<Component> findAll();

    Component findComponentByName(String name);
}
