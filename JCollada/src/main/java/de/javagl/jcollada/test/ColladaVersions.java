package de.javagl.jcollada.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Methods to read the version string from a COLLADA document
 */
public class ColladaVersions
{
    /**
     * Try to read the value of the <code>"version"</code> attribute of
     * the <code>"COLLADA"</code> tag that is expected in the XML file
     * with the given name.  
     * Returns <code>null</code> if no version can be detected. 
     * 
     * @param fileName The file name 
     * @return The version string
     * @throws IOException If an IO error occurs
     */
    public static String read(String fileName)
        throws IOException
    {
        try (InputStream inputStream = new FileInputStream(fileName))
        {
            return read(inputStream);
        }
    }
    
    /**
     * Try to read the value of the <code>"version"</code> attribute of
     * the <code>"COLLADA"</code> tag that is expected in the XML data
     * provided by the given input stream. The caller is responsible
     * for closing the given stream. 
     * Returns <code>null</code> if no version can be detected. 
     * 
     * @param inputStream The input stream. 
     * @return The version string
     * @throws IOException If an IO error occurs
     */
    public static String read(InputStream inputStream)
        throws IOException
    {
        try
        {
            return readImpl(inputStream);
        }
        catch (XMLStreamException e)
        {
            throw new IOException(e);
        }
    }
    

    /**
     * Implementation of {@link #read(InputStream)} 
     * 
     * @param inputStream The input stream. 
     * @return The version string
     * @throws XMLStreamException If an XML parsing error occurs
     */
    private static String readImpl(InputStream inputStream)
        throws XMLStreamException
    {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(inputStream);

        while (parser.hasNext())
        {
            switch (parser.getEventType())
            {
                case XMLStreamConstants.START_ELEMENT:

                    String localName = parser.getLocalName();
                    if (localName.equalsIgnoreCase("COLLADA"))
                    {
                        for (int i = 0; i < parser.getAttributeCount(); i++)
                        {
                            String attributeLocalName =
                                parser.getAttributeLocalName(i);
                            if (attributeLocalName.equalsIgnoreCase("version"))
                            {
                                String attributeValue =
                                    parser.getAttributeValue(i);
                                return attributeValue;
                            }
                        }
                    }

                default:
                    break;
            }
            parser.next();
        }
        return null;

    }

    /**
     * Private constructor to prevent instantiation
     */
    private ColladaVersions()
    {
        // Private constructor to prevent instantiation
    }
}
