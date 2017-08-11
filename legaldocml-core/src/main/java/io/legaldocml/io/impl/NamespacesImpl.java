package io.legaldocml.io.impl;

import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.Namespaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class NamespacesImpl implements Namespaces {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NamespacesImpl.class);

    /**
     * Max namespaces.
     */
    private static final int MAX = 8;

    private static final CharArray PREFIX_XML = CharArrays.constant("xml");

    private static final CharArray URI_XML = CharArrays.constant("http://www.w3.org/XML/1998/namespace");

    private final CharArray[] uris = new CharArray[MAX];
    private final CharArray[] prefixes = new CharArray[MAX];

    private int count = 1;

    @Override
    public CharArray get(CharArray prefix) {

        if (PREFIX_XML.equals(prefix)) {
            return URI_XML;
        }

        for (int i = 0; i < count; i++) {
            if (prefix.equals(this.prefixes(i))) {
                return uris[i];
            }
        }
        return null;
    }

    @Override
    public CharArray getDefaultNamespaceUri() {
        return this.uris[0];
    }

    @Override
    public void forEach(BiConsumer<CharArray, CharArray> consumer) {

        int i = 0;
        if (this.prefixes[i] == null && this.uris[i] == null) {
            LOGGER.info("no default namespace");
            i++;
        }

        for (; i < count; i++) {
            consumer.accept(this.prefixes[i], this.uris[i]);
        }
    }

    public void reset() {
        count = 1;
    }

    public void setPrefix(CharArray prefix, CharArray uri) {

        if (prefix.length() == 0) {
            // default namespace -> pos 0;
            this.uris[0] = uri;
            this.prefixes[0] = DEFAULT_NS_PREFIX;
        } else {
            this.uris[count] = uri;
            this.prefixes[count++] = prefix;
        }

    }

    public CharArray prefixes(int index) {
        return this.prefixes[index];
    }

    public int namespacesCount(int depth) {
        throw new UnsupportedOperationException();
    }

}