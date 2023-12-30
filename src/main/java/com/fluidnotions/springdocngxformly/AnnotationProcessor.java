package com.fluidnotions.springdocngxformly;

import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyField;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyFieldExpressions;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyFieldGroup;
import com.fluidnotions.springdocngxformly.annoations.SpringdocNgxFormlyFieldProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Slf4j

@RequiredArgsConstructor
public class AnnotationProcessor  implements ApplicationListener<ApplicationReadyEvent> {


    private final OpenApiJsonService openApiJsonService;

    private Map<String, SchemaInfo> schemaInfo = new HashMap<>();
    private Map<String, String> schemaFormlyFormDefinitions = new ConcurrentHashMap<>();

    private void processAnnotations(String fullyQualifiedName) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(fullyQualifiedName);
        var fieldInfo = Arrays.stream(clazz.getDeclaredFields())
                .map(this::processField)
                .collect(Collectors.toList());
        schemaInfo.put(clazz.getSimpleName(), new SchemaInfo(fullyQualifiedName, fieldInfo));
        log.debug("fieldInfo: {}", fieldInfo);
    }

    private FieldInfo processField(Field field) {
        var fieldAnnotations = new FieldAnnotations(
                field.getAnnotation(SpringdocNgxFormlyField.class),
                field.getAnnotation(SpringdocNgxFormlyFieldExpressions.class),
                field.getAnnotation(SpringdocNgxFormlyFieldGroup.class),
                field.getAnnotation(SpringdocNgxFormlyFieldProps.class)
        );
        return new FieldInfo(field.getName(), field.getType().getSimpleName(), fieldAnnotations);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var openApiObjectNode = openApiJsonService.getJsonObjectNode();
        log.debug("openApiObjectNode: {}", openApiObjectNode);
        openApiObjectNode.path("components").path("schemas").fieldNames().forEachRemaining(fullyQualifiedName -> {
            try {
                processAnnotations(fullyQualifiedName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        buildSchemaFormlyFormDefinitions();
    }

    private void buildSchemaFormlyFormDefinitions() {
        schemaInfo.forEach((simpleClassName, schemaInfo) -> {

        });
    }


    public String getSchemaFormlyFormDefinitions(String simpleClassName) {
        return schemaFormlyFormDefinitions.get(simpleClassName);
    }


    record SchemaInfo(String fullyQualifiedName, List<FieldInfo> fields) {
    }
    record FieldInfo(String fieldName, String fieldType, FieldAnnotations annotations) {
    }

    record FieldAnnotations(SpringdocNgxFormlyField field, SpringdocNgxFormlyFieldExpressions expressions,
                            SpringdocNgxFormlyFieldGroup group, SpringdocNgxFormlyFieldProps props) {
    }
}


