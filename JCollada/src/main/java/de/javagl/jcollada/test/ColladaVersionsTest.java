package de.javagl.jcollada.test;

import java.io.IOException;

public class ColladaVersionsTest
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Version is " + 
            ColladaVersions.read("data/emptyColladaDocument_1.3.1.dae"));
        System.out.println("Version is " + 
            ColladaVersions.read("data/emptyColladaDocument_1.4.1.dae"));
        System.out.println("Version is " + 
            ColladaVersions.read("data/emptyColladaDocument_1.5.0.dae"));
    }

}
