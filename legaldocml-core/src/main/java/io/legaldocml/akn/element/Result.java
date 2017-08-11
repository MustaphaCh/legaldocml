package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.ResultType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeResultType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element result is a metadata element specifying the overall result of the judgment.
 * <p/>
 * <pre>
 *   <xsd:element name="result" type="anyOtherType">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attributeGroup ref="resultType"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Result extends AnyOtherType implements io.legaldocml.akn.attribute.ResultType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "restriction";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(io.legaldocml.akn.attribute.ResultType.ATTRIBUTE, biConsumerEnum(getFieldOffset(Result.class, "refersTo"), ResultType.class))
                .build();
    }

    private ResultType type;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(ResultType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        writeResultType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }

}