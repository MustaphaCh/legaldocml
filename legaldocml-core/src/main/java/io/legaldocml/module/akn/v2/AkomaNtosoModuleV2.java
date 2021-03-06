package io.legaldocml.module.akn.v2;


import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.io.Attribute;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.module.AknModule;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AkomaNtosoModuleV2 implements AknModule {

    private static final String NS_VALUE = "http://www.akomantoso.org/2.0";
    private static final String NS_PREFIX = "xmlns";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    static final CharArray NAMESPACE = CharArrays.immutable(NS_VALUE);

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray namespace() {
        return NAMESPACE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS, 5, NS_VALUE_ADDRESS, 29);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<Attribute> attributes(String name) {
        throw new IllegalStateException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVersion() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AkomaNtosoContext newAkomaNtosoContext() {
        AkomaNtosoContext context = new AkomaNtosoContextV2();
        context.add(this);
        return context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AknObject> getAknClass(String localName) {
        return AknElements.getAknClass(localName);
    }

}
