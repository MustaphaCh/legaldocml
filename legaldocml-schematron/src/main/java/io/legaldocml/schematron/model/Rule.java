package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Uri;
import static io.legaldocml.schematron.model.SchAttributes.CONTEXT;
import static io.legaldocml.schematron.model.SchReadException.Type.INVALID_STATE;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rule extends AbstractLinkableRich implements PatternElement {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Rule.class);

    private static final ImmutableMap<String, AttributeGetterSetter<SchObject>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<RuleElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<SchObject>>builder()
                .putAll(AbstractLinkableRich.ATTRIBUTES)
                .put(CONTEXT, attributeGetterSetter4Uri(CONTEXT, getFieldOffset(Rule.class, "context")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<RuleElement>>builder()
                .put(SchElements.ASSERT, Assert::new)
                .put(SchElements.REPORT, Report::new)
                .build();
    }

    private String context;

    private final ExternalizableList<RuleElement> elements = new ExternalizableList<>(new RuleElement[8]);

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qName = reader.getQName();
        SchAttributes.read(reader, this);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[{}] with context [{}]",qName, this.context);
        }

        SchElements.read(reader, elements, ELEMS, qName);

        if (!SchElements.RULE.equals(qName.getLocalName())) {
            throw new SchReadException(INVALID_STATE, reader, reader.getQName(), SchElements.RULE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<SchObject>> attributes() {
        return ATTRIBUTES;
    }
}
