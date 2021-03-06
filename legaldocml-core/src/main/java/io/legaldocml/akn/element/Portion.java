package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PORTION;

/**
 * Element portion is used for describing the structure and content of an independent portion of a document.
 *
 * <pre>
 *   <xsd:element name="portion" type="portionStructure"/>
 *     <xsd:unique name="eId-portion">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-portion">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   </element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Portion extends PortionStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PORTION = Buffers.address(PORTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PORTION, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_PORTION, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PORTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}