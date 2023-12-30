package com.fluidnotions.springdocngxformly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyField;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyFieldExpressions;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyFieldGroup;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyFieldProps;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Getter
@RequiredArgsConstructor
public class AnnotationProcessor {


    private final OpenApiJsonService openApiJsonService;

    private List<FieldInfo> fieldInfo;

    @PostConstruct()
    public void init() {
       var openApiObjectNode = openApiJsonService.getJsonObjectNode();
       log.info("openApiObjectNode: {}", openApiObjectNode);
    }

    public void processAnnotations(String fullyQualifiedName) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(fullyQualifiedName);
        fieldInfo = Arrays.stream(clazz.getDeclaredFields()).map(this::processField).collect(Collectors.toList());
        log.info("fieldInfo: {}", fieldInfo);
    }

    private FieldInfo processField(Field field) {
        var fieldAnnotations = new FieldAnnotations(field.getAnnotation(SpringdocNgxFormlyField.class), field.getAnnotation(SpringdocNgxFormlyFieldExpressions.class), field.getAnnotation(SpringdocNgxFormlyFieldGroup.class), field.getAnnotation(SpringdocNgxFormlyFieldProps.class));
        return new FieldInfo(field.getName(), field.getType().getSimpleName(), fieldAnnotations);
    }


    record FieldInfo(String fieldName, String fieldType, FieldAnnotations annotations) {
    }

    record FieldAnnotations(SpringdocNgxFormlyField field, SpringdocNgxFormlyFieldExpressions expressions,
                            SpringdocNgxFormlyFieldGroup group, SpringdocNgxFormlyFieldProps props) {
    }
}


