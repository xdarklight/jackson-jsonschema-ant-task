# Ant task to generate JSON Schemas using Jackson

Easily generate JSON Schemas from your Java classes with Apache Ant using the [Jackson](http://wiki.fasterxml.com/JacksonHome) library and extension modules.

## Usage

### Preconditions

There are a few preconditions for using this Ant task:
1. you need ```jackson-jsonschema-ant-task``` in your classpath (obvious)
2. you need your Jackson annotated Java classes in your classpath
3. you need the Jackson's ```jackson-module-jsonSchema``` and it's dependencies in your classpath

### Example

```xml
<path id="project.classpath">
	<pathelement path="${java.class.path}/"/>
	<pathelement location="lib/" /> <!-- should contain jackson-module-jsonSchema and it's dependencies -->
	<pathelement path="classes/" /> <!-- path to your classes directory where you have the class for which you want to generate the JSON schema -->
	<pathelement path="/path/to/jackson-jsonschema-ant-task-VERSION.jar" />
</path>

<taskdef resource="eu/blumenstingl/jackson/jsonschema/jsonschematasks.properties" classpathref="project.classpath">
</taskdef>

<target name="generateJsonSchema">
  <jsonschema classname="fqdn.for.JsonJavaClass" destfile="doc/jsonSchema/test.jsonschema" />
</target>
```

## Parameters
### Task attributes

Attribute   | Description | Required
----------- | ----------- | ------------------
classname   | The Java class for which the JSON Schema will be generated (including all referenced classes) | yes
destfile    | The target file to which the JSON Schema will be written | yes

## License

The Ant task is offered under the Apache 2.0 license, see ```LICENSE```

## Building a new release

```bash
mvn release:prepare
```
