package io.legaldocml.akn.attribute;

import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeIdOpt;

/**
 * These attributes identify the element in an absolute manner. In elements using this attribute definition the eId
 * attribute is optional. The wId is used to mark the identifier that the structure used to have in the original
 * version, and is only needed when a renumbering occurred.
 *
 * ```xml
 * <xsd:attributeGroup name="idopt">
 *   <xsd:attribute name="eId" type="noWhiteSpace"/>
 *   <xsd:attribute name="wId" type="noWhiteSpace"/>
 *   <xsd:attribute name="GUID" type="noWhiteSpace"/>
 * </xsd:attributeGroup>
 * ```
 */
public interface IdOpt extends Id {

    @Override
    default void write(XmlWriter writer) throws IOException {
        writeIdOpt(writer, this);
    }
}



