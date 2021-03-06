package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.element.Table;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.group.BlockElementsBuilder;
import io.legaldocml.business.builder.support.BlockListSupport;
import io.legaldocml.business.builder.support.ForeignSupport;
import io.legaldocml.business.builder.support.PSupport;
import io.legaldocml.business.builder.support.TocSupport;
import io.legaldocml.business.util.EidFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class BlocksBuilder<T extends BlockElementsContainer<BlockElements>> extends AbstractBusinessPartBuilder<T> implements BlockElementsBuilder<T,BlockElements>,
        PSupport<T,BlockElements>, ForeignSupport<T,BlockElements>, BlockListSupport<T,BlockElements>, TocSupport<T,BlockElements>  {

    private final Id parent;
    private final T container;

    public BlocksBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, Id parent, T container) {
        super(businessBuilder, container);
        this.parent = parent;
        this.container = container;
    }

    public BlocksBuilder<T> eid(String number) {
        EidFactory.makeAndFill(this.parent, this.container, number);
        return this;
    }

    public TableBuilder table() {
        Table table = new Table();
        this.container.add(table);
        return new TableBuilder(businessBuilder(), table);
    }

}
