package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;

final class CerebroCodeLinkGetter<E extends AknObject> implements CerebroCodeLink<E> {

    private final Class<E> returnClass;
    private final String getter;

    public CerebroCodeLinkGetter(Class<E> returnClass, String getter) {
        this.returnClass = returnClass;
        this.getter = getter;
    }

    @Override
    public Class<E> getReturnClass() {
        return this.returnClass;
    }

    @Override
    public void apply(CerebroBuilder builder) {
        builder.appendGetter(returnClass, getter);
    }
}