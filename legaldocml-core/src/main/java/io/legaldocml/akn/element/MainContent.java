package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.COMPONENT_REF;

/**
 * The complex type maincontent is used by container elements that can contain basically any other Akoma Ntoso
 * structure.
 *
 * <pre>
 *   <xsd:complexType name="maincontent">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:element ref="componentRef"/>
 * 		 <xsd:group ref="hierElements"/>
 * 		 <xsd:group ref="blockElements"/>
 * 		 <xsd:group ref="containerElements"/>
 * 	   </xsd:choice>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MainContent extends AbstractCore implements CoreOpt {

    private static final ImmutableMap<String, Supplier<MainContentElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<MainContentElement>>builder()
                .putAll(Groups.convertSuper(Groups.hierElements()))
                .putAll(Groups.convertSuper(Groups.containerElements()))
                .putAll(Groups.convertSuper(Groups.blockElements()))
                .put(COMPONENT_REF, ComponentRef::new)
                .build();
    }

    // Mandatory
    private final AknList<MainContentElement> elements = new AknList<>(new MainContentElement[4]);

    public final void add(MainContentElement mainContentElement) {
        this.elements.add(mainContentElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreOpt.super.write(writer);
        this.elements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.elements.accept(visitor);
    }

}