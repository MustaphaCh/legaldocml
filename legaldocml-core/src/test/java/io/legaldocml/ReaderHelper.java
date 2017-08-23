package io.legaldocml;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.impl.XmlChannelReader;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class ReaderHelper {

    private ReaderHelper() {
    }

    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

    static {
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
    }

    public static <T extends DocumentType> AkomaNtoso<T> read(Path path) throws IOException {

        AkomaNtoso<T> akomaNtoso;

        XmlChannelReader reader = new XmlChannelReader();
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            reader.setBuffer(out);
            reader.nextStartOrEndElement();

            akomaNtoso = XmlReaderHelper.createAkomaNtoso(reader);
            akomaNtoso.read(reader);

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        return akomaNtoso;
    }


    public static Document load(String classpathResource) {
        return load(ReaderHelper.class.getResourceAsStream(classpathResource));
    }

    public static Document load(InputStream stream) {
        try {
            DocumentBuilder db = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();

            return db.parse(stream);
        } catch (Exception cause) {
            throw new RuntimeException(cause);
        }


    }


}