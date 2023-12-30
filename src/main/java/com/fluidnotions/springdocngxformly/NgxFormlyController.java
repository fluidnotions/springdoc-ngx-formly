package com.fluidnotions.springdocngxformly;

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

    private final AnnotationProcessor formly;

    @GetMapping("schema/{simpleRequestBodyClassName}")
    public ResponseEntity<String> getFormlyJson(@PathVariable(required = false) String simpleRequestBodyClassName) {
        try {
            return ResponseEntity.ok(formly.getSchemaFormlyFormDefinitions(simpleRequestBodyClassName));
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
