package com.dreamcar.inventorysystem.repository;

import com.dreamcar.inventorysystem.model.Component;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ComponentRepositoryTest {
    @Autowired
    ComponentRepository repository;

    @Test
    void checkThatIdIsCreated(){
        Component component = new Component("Wheel", 42);
        Component saved = repository.save(component);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void getAll() {
        List<Component> components = repository.findAll();
        assertThat(components.size()).isEqualTo(3);
    }

}