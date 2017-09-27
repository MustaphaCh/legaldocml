package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.akn.attribute.ShowReq;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerManifestationURI;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSrc;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type srcType defines the empty content model and the list of attributes for manifestation-level
 * references to external resources.
 *
 * <pre>
 *   <xsd:complexType name="srcType">
 * 	   <xsd:attributeGroup ref="core"/>
 * 	   <xsd:attributeGroup ref="idopt"/>
 * 	   <xsd:attributeGroup ref="src"/>
 * 	   <xsd:attributeGroup ref="show"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class SrcType extends AbstractId implements Core, IdOpt, ShowReq, Src {

    protected static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(AknAttributes.SRC, biConsumerManifestationURI(AknAttributes.SRC, getFieldOffset(SrcType.class, "src")))
                .put(AknAttributes.ALT, biConsumerString(getFieldOffset(SrcType.class, "alt")))
                .put(AknAttributes.SHOW_AS, biConsumerString(getFieldOffset(SrcType.class, "showAs")))
                .put(AknAttributes.SHORT_FORM, biConsumerString(getFieldOffset(SrcType.class, "shortForm")))
                .build();
    }

    private ManifestationURI src;
    private String alt;
    private String showAs;
    private String shortForm;

    /**
     * {@inheritDoc}
     */
    @Override
    public ManifestationURI getSrc() {
        return this.src;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSrc(ManifestationURI src) {
        this.src = src;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAlt() {
        return this.alt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShowAs() {
        return this.showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortForm() {
        return this.shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShortForm(String shortForm) {
        this.shortForm = shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        IdOpt.super.write(writer);
        Core.super.write(writer);
        writeSrc(writer, this);
        writeShow(writer, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
    }

    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}