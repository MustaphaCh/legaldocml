package io.legaldocml.io;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;

import java.nio.MappedByteBuffer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XmlReaderFactory {

    <T extends DocumentType> AkomaNtoso<T> read(MappedByteBuffer buffer);

}