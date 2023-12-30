# SpringDoc-Formly Library

## Overview
The SpringDoc-Formly Library, currently in early development, aims to facilitate the creation of Angular forms in projects using Spring Boot. It primarily serves to translate Spring Boot's REST API model schemas into ngx-formly configurations, focusing on custom formly types for specific use cases.

## Key Features
- **Automated Form Generation**: Tailored to convert REST API schemas into ngx-formly JSON.
- **Annotation-Driven**: Leverages custom annotations for additional field metadata.
- **Custom Field Support**: Accommodates unique field types from specific Angular projects.
- **Integration**: Easily integrates with Spring Boot applications using SpringDoc OpenAPI.

## Use Cases
Ideal for:
- Developing Angular forms aligned with Spring Boot API models.
- Streamlining frontend development by automating form configurations.

## Getting Started
1. Add as a dependency in Spring Boot projects using `org.springdoc:springdoc-openapi-starter-webmvc-ui`.
2. Annotate REST API models as needed.
3. Retrieve ngx-formly configurations via an endpoint.
4. Set `springdoc.use-fqn=true` in `application.properties` for proper schema class resolution.

## Caveats
This library is in early development, tailored to specific use cases and custom ngx-formly types. Contributions for broadening its scope and functionality are welcome, but it's currently optimized for specific scenarios.

## Contribution
Contributions are welcome. See `CONTRIBUTING.md` for guidelines.

## License
MIT License - see `LICENSE.md`.

## Acknowledgments
- Thanks to SpringDoc OpenAPI and the ngx-formly team.
