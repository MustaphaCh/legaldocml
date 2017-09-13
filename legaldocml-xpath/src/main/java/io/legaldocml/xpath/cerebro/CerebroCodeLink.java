package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AknObject;

public interface CerebroCodeLink<E extends AknObject> {

    Class<E> getReturnClass();

    void apply(CerebroBuilder builder);
}
