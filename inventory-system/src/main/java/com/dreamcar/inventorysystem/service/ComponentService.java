package com.dreamcar.inventorysystem.service;

import com.dreamcar.inventorysystem.exceptions.EntityNotFoundException;
import com.dreamcar.inventorysystem.model.Component;
import com.dreamcar.inventorysystem.repository.ComponentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ComponentService {
    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }


    public List<Component> getAll() {
        return componentRepository.findAll();
    }

    public Component getComponent(Integer id) {
        Optional<Component> optionalComponent = componentRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Component.class.getSimpleName(), id
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        return optionalComponent.get();
    }

    public int getDiffBetweenExistingAndRequiredComponents(Component component, int quantity) {
        return component.getQuantity() - quantity;
    }

    @Transactional
    public Component updateFeignComponent(Component feignComponent) {
        Component component = componentRepository.findComponentByName(feignComponent.getName());
        component.setQuantity(component.getQuantity() + feignComponent.getQuantity());
        return componentRepository.save(component);
    }

    @Transactional
    public Component update(Component component) {
        return componentRepository.save(component);
    }
}
