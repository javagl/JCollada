# JCollada

COLLADA classes, automatically generated from the schema with JAXB

# NOTE: This is not supposed to be a COLLADA loader!

This is rather an experiment, showing how the COLLADA classes *can* be
automatically generated from the COLLADA schema. The `JColloadaGen`
project contains an appropriate Maven POM that allows generating
the JARs of these classes using the JAXB Maven plugin, with 

    mvn clean install -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=all 

The `JCollada` project contains a basic example, showing how the generated
classes may be used. But in order to load actual COLLADA files, for example,
for a renderer, dedicated loaders should be used. 

    