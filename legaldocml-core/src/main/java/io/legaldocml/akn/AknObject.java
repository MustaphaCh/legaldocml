package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.visitor.Visitable;
import io.legaldocml.diff.DiffContext;
import io.legaldocml.diff.Diffs;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknObject extends Externalizable, Visitable {

    /**
     * Name of this object (the name of the xml element)
     */
    String name();

    /**
     * To read attributes.
     */
    default ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ImmutableMap.of();
    }

    /**
     * default method for diff.
     */
    default void compare(AknObject object, DiffContext context) {
        context.push(this);
        Diffs.compare(this,object, context);
        context.pop(this);
    }

    default void nestedCompare(AknObject object, DiffContext context) {
    }

}
