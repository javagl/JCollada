package de.javagl.jcollada.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.collada._2005._11.colladaschema.COLLADA;
import org.collada._2005._11.colladaschema.FloatArray;
import org.collada._2005._11.colladaschema.Geometry;
import org.collada._2005._11.colladaschema.LibraryGeometries;
import org.collada._2005._11.colladaschema.Mesh;
import org.collada._2005._11.colladaschema.ObjectFactory;
import org.collada._2005._11.colladaschema.Source;

public class ColladaLoadTest
{
    public static void main(String[] args) throws IOException, JAXBException
    {
        String version = 
            ColladaVersions.read("data/duck.dae");
        if (version == null || !version.startsWith("1.4"))
        {
            System.out.println("Expected version 1.4 but found " + version);
            return;
        }
        
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        
        File file = new File("data/duck.dae");
        COLLADA collada = (COLLADA) unmarshaller.unmarshal(file);
        
        List<Object> elements = 
            collada.getLibraryAnimationsAndLibraryAnimationClipsAndLibraryCameras();
        for (Object element : elements)
        {
            System.out.println("Element: " + element);
            
            if (element instanceof LibraryGeometries)
            {
                LibraryGeometries libraryGeometries = 
                    (LibraryGeometries)element;
                List<Geometry> geometries = libraryGeometries.getGeometries();
                Mesh mesh = geometries.get(0).getMesh();
                List<Source> sources = mesh.getSources();
                FloatArray floatArray = sources.get(0).getFloatArray();
                System.out.println("Some geometry data: " + 
                    floatArray.getValues());
            }
        }
        
        
    }
}
