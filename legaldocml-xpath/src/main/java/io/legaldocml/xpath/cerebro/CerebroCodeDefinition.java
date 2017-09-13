package io.legaldocml.xpath.cerebro;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;

public final class CerebroCodeDefinition {

    private final ImmutableMap<Class<? extends AknObject>, CerebroCodeLink> links;

    public CerebroCodeDefinition(CerebroCodeLink ... links) {
        ImmutableMap.Builder builder =  ImmutableMap.<Class<? extends AknObject>, CerebroCodeLink>builder();

       /* if (parent != null) {
            builder.putAll(parent.links);
        }*/

        for (CerebroCodeLink link : links) {
            builder.put(link.getReturnClass(), link);
        }
        this.links = builder.build();
    }

    public CerebroCodeLink getCodeLink(Class<? extends AknObject> child) {
        return this.links.get(child);
    }
}
