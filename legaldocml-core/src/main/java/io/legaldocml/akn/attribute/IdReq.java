package io.legaldocml.akn.attribute;

import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeIdReq;

/**
 * These attributes identify the element in an absolute manner. In elements using this attribute definition the eId
 * attribute is required whenever the Akoma Ntoso naming Convention is used. The wId is used to mark the identifier that
 * the structure used to have in the original version, and is only needed when a renumbering occurred.
 *
 * ```xml
 * <xsd:attributeGroup name="idreq">
 *   <xsd:attribute name="eId" type="noWhiteSpace"/>
 *   <xsd:attribute name="wId" type="noWhiteSpace"/>
 *   <xsd:attribute name="GUID" type="noWhiteSpace"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface IdReq extends Id {

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        writeIdReq(writer, this);
    }
}

