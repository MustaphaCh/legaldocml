package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.DEBATE_SECTION;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * This element is a generic structural container for all other parts of a debates that are not explicitly supported
 * with a named element.
 * <p>
 * <pre>
 *   <xsd:element name="debateSection">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="althierarchy">
 * 		     <xsd:attributeGroup ref="name"/>
 * 		   </xsd:extension>
 * 		 </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DebateSection extends AltHierarchy implements Name, SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEBATE_SECTION = Buffers.address(DEBATE_SECTION);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(AltHierarchy.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(DebateSection.class, "name")))
                .build();
    }

    private String name;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEBATE_SECTION, 13);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEBATE_SECTION, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEBATE_SECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
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