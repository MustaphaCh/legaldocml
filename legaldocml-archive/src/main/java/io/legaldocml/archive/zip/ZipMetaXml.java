package io.legaldocml.archive.zip;

import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.MetaResource;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.MediaType;
import io.legaldocml.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;

import static io.legaldocml.archive.ArchiveException.Type.READ_META;
import static io.legaldocml.archive.ArchiveException.Type.META_WRITE;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ZipMetaXml {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipMeta.class);

    private static final XMLOutputFactory XML_OUTPUT_FACTORY = XMLOutputFactory.newFactory();

    private static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newFactory();


    static void write(FileChannel fileChannel,BusinessProvider provider, ZipMeta meta) {
        try {
            XMLStreamWriter writer = XML_OUTPUT_FACTORY.createXMLStreamWriter(Channels.newOutputStream(fileChannel), StandardCharsets.UTF_8.name());
            writer.writeStartDocument(StandardCharsets.UTF_8.name(), "1.0");
            writer.writeStartElement("legaldocml-archive");
            writer.writeAttribute("version", "1.0");
            writer.writeAttribute("provider", provider.name());
            meta.stream().forEach(resource -> write(writer, resource));
            writer.writeEndElement();
            writer.writeEndDocument();
        } catch (XMLStreamException cause) {
            throw new ArchiveException(META_WRITE, "Failed to write descriptor for ZipArchiveWriteOnly", cause);
        }


    }

    static ZipMeta valueOf(ReadableByteChannel rbc) {

        ZipMeta zipMeta = new ZipMeta();
        try {
            XMLStreamReader reader = XML_INPUT_FACTORY.createXMLStreamReader(Channels.newInputStream(rbc));

            // goto descriptor tag.
            reader.nextTag();

            if ("legaldocml-archive".equals(reader.getLocalName())) {

                String version = reader.getAttributeValue(null, "version");
                String provider = reader.getAttributeValue(null,"provider");

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("version=[{}], provider=[{}]", version, provider);
                }

                if (Strings.isEmpty(provider)) {
                    throw new ArchiveException(READ_META, "Missing attribute provider");
                }

                BusinessProvider businessProvider = BusinessProvider.businessProvider(provider);
                reader.nextTag();

                String work, expression, manifestation, name, type;

                while (reader.isStartElement()) {
                    if (!"resource".equals(reader.getLocalName())) {
                        throw new ArchiveException(READ_META, "Invalid descriptor -> expected <resource>, found <" + reader.getLocalName() + ">");
                    }
                    work = readTag(reader, "work");
                    expression = readTag(reader, "expression");
                    manifestation = readTag(reader, "manifestation");
                    name = readTag(reader, "name");
                    type = readTag(reader, "type");

                    AknIdentifier identifier = businessProvider.newAknIdentifier(work, expression, manifestation);
                    ZipMetaResource resource = new ZipMetaResource(identifier, MediaType.valueOf(type), name);

                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("found ZipMetaResource [{}]", resource);
                    }

                    zipMeta.getResources().put(identifier, resource);


                    reader.nextTag();

                    // goto next item ?
                    reader.nextTag();
                }


            }
        } catch (XMLStreamException cause) {
            throw new ArchiveException(READ_META,"Failed to read meta descriptor", cause);
        }

        return zipMeta;

    }

    private static void write(XMLStreamWriter writer, MetaResource resource) {
        try {
            writer.writeStartElement("resource");
            write(writer, "work", resource.getAknIdentifier().work());
            write(writer, "expression", resource.getAknIdentifier().expressionPart());
            write(writer, "manifestation", resource.getAknIdentifier().manifestationPart());
            write(writer, "name", resource.getName());
            write(writer, "type", resource.getMediaType().toString());
            writer.writeEndElement();
        } catch (XMLStreamException cause) {
            throw new ArchiveException(ArchiveException.Type.META_WRITE, "Failed to write MetaResource in Meta", cause);
        }

    }

    private static void write(XMLStreamWriter writer, String key, String value) throws XMLStreamException {
        writer.writeStartElement(key);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }

    private static String readTag(XMLStreamReader reader, String name) throws XMLStreamException {
        String value;
        reader.nextTag();
        if (!name.equals(reader.getLocalName())) {
            throw new ArchiveException(ArchiveException.Type.READ_META, "Invalid descriptor -> expected <" + name + ">, found <" + reader.getLocalName() + ">");
        } else {
            reader.next();
            value = reader.getText();
            reader.nextTag();
        }
        return value;
    }
}
