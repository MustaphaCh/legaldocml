package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.hierElements;

/**
 * The complex type hierarchy is used by most or all the hierarchical elements of act-like documents.
 * <p/>
 * <pre>
 *   <xsd:complexType name="hierarchy">
 *     <xsd:complexContent>
 * 	     <xsd:extension base="basehierarchy">
 * 		   <xsd:choice>
 * 		     <xsd:sequence>
 * 			   <xsd:element ref="intro" minOccurs="0" maxOccurs="1"/>
 * 			     <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 				   <xsd:element ref="componentRef"/>
 * 				   <xsd:group ref="hierElements"/>
 * 				   <xsd:element ref="crossHeading"/>
 * 				 </xsd:choice>
 * 			     <xsd:element ref="wrapUp" minOccurs="0" maxOccurs="1"/>
 * 			   </xsd:sequence>
 * 		     <xsd:element ref="content"/>
 * 		   </xsd:choice>
 * 		   <xsd:attributeGroup ref="corereq"/>
 * 	     </xsd:extension>
 * 	   </xsd:complexContent>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Hierarchy extends BaseHierarchyCoreReq {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Hierarchy.class);

    private static final ImmutableMap<String, Supplier<HierarchyElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<HierarchyElement>>builder()
                .putAll(convertSuper(hierElements()))
                .put(ComponentRef.ELEMENT, ComponentRef::new)
                .put(CrossHeading.ELEMENT, CrossHeading::new)
                .build();
    }

    private Content content;
    // or
    private Intro intro;
    private AknList<HierarchyElement> elements;

    @Deprecated
    private Wrap wrap;

    private WrapUp wrapUp;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        if (this.content != null) {
            this.content.write(writer);
        } else {
            if (this.intro != null) {
                this.intro.write(writer);
            }
            if (this.elements != null && this.elements.size() > 0) {
                this.elements.write(writer);
            }

            if (writer.getVersion() == 2) {
                if (this.wrap != null) {
                    this.wrap.write(writer);
                }
            } else {
                if (this.wrapUp != null) {
                    this.wrapUp.write(writer);
                }
            }


        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        QName qName = reader.getQName();
        super.read(reader);

//        if (LOGGER.isTraceEnabled()) {
//            LOGGER.trace("Hierarchy -> [{}] -> [{}]" , getClass().getSimpleName(), this);
//        }

        if (reader.getQName().equalsLocalName(Content.ELEMENT)) {
            this.content = new Content();
            this.content.read(reader);
            reader.nextStartOrEndElement();
            return;
        }

        if (reader.getQName().equalsLocalName(Intro.ELEMENT)) {
            this.intro = new Intro();
            this.intro.read(reader);
            reader.nextStartOrEndElement();
        }

        this.elements = new AknList<>(new HierarchyElement[4]);
        XmlReaderHelper.read(reader, this.elements, ELEMS, qName, Wrap.ELEMENT);


        if (reader.getContext().getAkoXmlModule().getVersion() == 2 && reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getQName().equalsLocalName(Wrap.ELEMENT)) {
            this.wrap = new Wrap();
            this.wrap.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getContext().getAkoXmlModule().getVersion() == 3 && reader.getEventType() == XMLStreamConstants.START_ELEMENT && reader.getQName().equalsLocalName(WrapUp.ELEMENT)) {
            this.wrapUp = new WrapUp();
            this.wrapUp.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    public final Content getContent() {
        return this.content;
    }

    public final void setContent(Content content) {
        this.content = content;
    }

    public final void setIntro(Intro intro) {
        this.intro = intro;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {

//        private Content content;
//        // or
//        private Intro intro;
//        private AknList<HierarchyElement> elements;
//
//        @Deprecated
//        private Wrap wrap;
//
//        private WrapUp wrapUp;

//        if (content != null) {
//            if (visitor.visitEnter(this.content)) {
//                super.accept(visitor);
//                visitor.visitLeave(this.content);
//            }
//        }


    }
}