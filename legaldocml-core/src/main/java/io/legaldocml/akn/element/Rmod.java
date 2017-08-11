package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Range;
import io.legaldocml.akn.attribute.RangeReq;
import io.legaldocml.akn.attribute.UpTo;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRange;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element rmod is an inline element containing the specification of a range of modifications on another document.
 * <p/>
 * <pre>
 *   <xsd:element name="rmod">
 *     <xsd:complexType mixed="true">
 *       <xsd:complexContent>
 *         <xsd:extension base="modType">
 *           <xsd:attributeGroup ref="range"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rmod extends ModType implements RangeReq, ANinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "rmod";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(ModType.ATTRIBUTES)
                .put(UpTo.ATTRIBUTE, biConsumerEidRef(getFieldOffset(Rmod.class, "uoTo")))
                .put(Range.ATTRIBUTE, biConsumerEidRef(getFieldOffset(Rmod.class, "from")))
                .build();

    }

    private EidRef from;
    private EidRef upTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getFrom() {
        return this.from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrom(EidRef from) {
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getUpTo() {
        return this.upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpTo(EidRef upTo) {
        this.upTo = upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 4);
        writeRange(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}