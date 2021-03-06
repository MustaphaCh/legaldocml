package io.legaldocml.business.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessPartBuilder;
import io.legaldocml.business.builder.DefaultHierachyStrategy;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultBusinessServiceLoader extends BusinessProvider {


    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return "default";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AknIdentifier newAknIdentifier(String work, String expression, String manifestation) {
        return new DefaultAknIdentifier(work, expression, manifestation, "/");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AknIdentifier extractAknIdentifier(String work, String expression, String manifestation) {
        return new DefaultAknIdentifier(work, expression.substring(work.length() + 1), manifestation.substring(expression.length() + 1), "/");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AknIdentifier newAknIdentifierTransient() {
        return new DefaultAknIdentifier("0", "0", "0", "/");
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends BusinessBuilder<T>, T extends DocumentType> E newBuilder(String name) {
        return (E) new DefaultBusinessBuilder(this, name, DefaultHierachyStrategy.COMPLETE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends BusinessPartBuilder<Z>, Z extends AknObject> E newPartBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, AknObject parent, String name) {
        return null;
    }

}