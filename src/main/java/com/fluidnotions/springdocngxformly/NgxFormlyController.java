package com.fluidnotions.springdocngxformly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ngx-formly/")
@RequiredArgsConstructor
public class NgxFormlyController {

    private final AnnotationProcessor annotationProcessor;
    private final ObjectMapper objectMapper;

    @GetMapping("/{schemaName}")
    public ResponseEntity<ObjectNode> getFormlyJson(@PathVariable(required = false) String schemaName) {
        var openApi = annotationProcessor.getOpenApi();
        var response = objectMapper.createObjectNode();
        response.put("schemaName", schemaName);
        response.put("schemas", openApi.getComponents().getSchemas().toString());
        if(schemaName != null && openApi.getComponents().getSchemas().get(schemaName) != null){
            response.put("schema", openApi.getComponents().getSchemas().get(schemaName).toString());
        }
        return ResponseEntity.ok(response);
    }
}
