# CHANGELOG

## 2.0.2

- Use shared Maven workflows at `v1.1.0`: wait for the SonarCloud quality gate and analyse with the
  uetlib-only Sonar token.
- Use shared Maven build workflow at `v1.0.0` to enforce formatting checks in CI.

## 2.0.1

- Migrate configuration properties and references to `ch.uetlib.*`.
- Use the renamed `uetlib-geo-spring-boot` integration artifact.

- Rename uetlib configuration keys and property references to `ch.uetlib.*`, including Spring bindings, `@Value`,
  conditions, scheduling expressions and configuration files. Migrate external deployment configuration as well.

- Move `uetlib-spring-boot-geo` into the geo repository as `uetlib-geo-spring-boot`, with packages under
  `ch.uetlib.geo.spring.boot`; update consumers and migration mappings.

- Move Java packages and class references from `ch.ubique.uetlib` to `ch.uetlib`.

### Breaking changes

- Move Java packages from `ch.ubique.backend` to `ch.uetlib`, including class-name resources. The combined OpenRewrite
  migration also renames known Spring configuration keys to `ch.uetlib.*`.
- Remove the inherited Ubique Artifactory repository and deployment destination; private consumers must configure their
  own registry.
- Add Apache-2.0 licensing for original code, preserve third-party licenses, and add public project metadata and
  contribution/security guidance.

- Move Maven coordinates from `ch.ubique:uetlib-*` to `ch.uetlib:uetlib-*`.
- Align uetlib parent, BOM and library references with the migration releases.

### Migration

After the migration releases and `uetlib-rewrite` are published, run this from the consuming project, then review the
diff and run its tests:

```sh
mvn -U org.openrewrite.maven:rewrite-maven-plugin:6.46.1:run \
  -Drewrite.recipeArtifactCoordinates=ch.uetlib:uetlib-rewrite:RELEASE \
  -Drewrite.activeRecipes=ch.uetlib.MigrateToPublicUetlib
```

The recipe updates Maven coordinates/versions, Java references and known class-name resources. Review application-owned
packages under the old namespace, Kotlin, reflection patterns, native-access module flags and persisted class names
separately. Use `:dryRun` instead of `:run` to preview a patch.

## 1.0.2

- latest uetlib parent
- version updates

## 1.0.1

- updated actions to not run tests twice for sonar
