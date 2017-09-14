package io.legaldocml.module.xsi;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.module.Module;
import io.legaldocml.module.xsi.attribute.SchemaLocation;
import io.legaldocml.util.ToStringBuilder;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XsiModule implements Module {

    public static final String NAMESPACE_SCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";

    public static final String NAMESPACE_PREFIX_XSI = "xmlns:xsi";

    private static final long ADDRESS_NAMESPACE_SCHEMA_INSTANCE = Buffers.address(NAMESPACE_SCHEMA_INSTANCE);
    private static final long ADDRESS_NAMESPACE_PREFIX_XSI = Buffers.address(NAMESPACE_PREFIX_XSI);

    private static final CharArray NAMESPACE_XSI = CharArrays.constant(NAMESPACE_SCHEMA_INSTANCE);

    private static final ImmutableMap<String, Supplier<Attribute>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, Supplier<Attribute>>builder()
                .put(SchemaLocation.ATTRIBUTE, SchemaLocationImpl::new)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray namespace() {
        return NAMESPACE_XSI;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(ADDRESS_NAMESPACE_PREFIX_XSI, 9, ADDRESS_NAMESPACE_SCHEMA_INSTANCE, 41);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<Attribute> attributes(String name) {
        return ATTRIBUTES.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AknObject> getAknClass(String localName) {
        throw new IllegalStateException("No AknClass for XSI");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("prefix", NAMESPACE_PREFIX_XSI)
                .append("namespace", NAMESPACE_SCHEMA_INSTANCE)
                .toString();
    }

}