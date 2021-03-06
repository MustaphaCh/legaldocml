package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_VERSION_NUMBER;

/**
 * The element FRBRversionNumber is the metadata property containing the value of the version number if appropriate to
 * identify the specific expression here contained. It allows an arbitrary string.
 *
 * <pre>
 *   <xsd:element name="FRBRversionNumber" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRversionNumber extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_VERSION_NUMBER = Buffers.address(FRBR_VERSION_NUMBER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_VERSION_NUMBER, 17);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_VERSION_NUMBER, 17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_VERSION_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}