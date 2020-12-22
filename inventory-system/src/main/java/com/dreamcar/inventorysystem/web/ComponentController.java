package com.dreamcar.inventorysystem.web;

import com.dreamcar.inventorysystem.feignclient.RequestDto;
import com.dreamcar.inventorysystem.feignclient.RequestServiceFeignClient;
import com.dreamcar.inventorysystem.model.Component;
import com.dreamcar.inventorysystem.service.ComponentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = ComponentController.REST_URL)
public class ComponentController {
    static final String REST_URL = "/components";
    private final ComponentService componentService;
    private final RequestServiceFeignClient feignClient;

    public ComponentController(ComponentService componentService, RequestServiceFeignClient feignClient) {
        this.componentService = componentService;
        this.feignClient = feignClient;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Component> getAllComponents() {
        return componentService.getAll();
    }

    /* Yes, a gross violation of the REST principles.
     For a secondary service created for 'one request' without UI, I had to do so ^^ */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAndUpdateComponentsAndCreateRequest(@PathVariable("id") Integer id, @RequestParam int quantity) {
        String response;
        Component component = componentService.getComponent(id);
        int diff = componentService.getDiffBetweenExistingAndRequiredComponents(component, quantity);
        if(diff >= 0){
            component.setQuantity(diff);
            componentService.update(component);
            response = String.format("{\"received\":{\"name\":\"%s\",\"quantity\": %d}}", component.getName(), quantity);
            return response;
        }else{
            int allAvailableComponents = component.getQuantity();
            component.setQuantity(0);
            Integer quantityToOrder = Math.abs(diff);
            RequestDto request = new RequestDto(component.getName(), quantityToOrder);
            String resourceUrl = feignClient.createRequest(request);
            componentService.update(component);
            response = String.format(
                    "{\"received\":{\"name\":\"%s\",\"quantity\":%d},\"ordered\":{\"name\":\"%s\"," +
                    "\"quantity\":%d},\"order_resource\":\"%s\",\"message\":\"Please, confirm request at the Auction" +
                    " Platform to continue purchasing.\"}",
                    component.getName(), allAvailableComponents, component.getName(), quantityToOrder, resourceUrl);
            return response;
        }
    }
}
