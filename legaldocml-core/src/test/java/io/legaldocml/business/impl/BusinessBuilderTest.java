package io.legaldocml.business.impl;

import static io.legaldocml.akn.AknElements.DEBATE;
import static io.legaldocml.io.XmlProvider.writerFactory;

import java.io.IOException;
import java.nio.channels.Channels;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.iso.Iso639;
import io.legaldocml.test.LoggerInstancePostProcessor;

@ExtendWith(LoggerInstancePostProcessor.class)
public class BusinessBuilderTest {

    @Test
    public void testDebate() throws IOException {

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(DEBATE);

        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifierTransient());
        builder.getMetaBuilder().addLanguage(Iso639.ENGLISH);

        List<LegalDocMlException> exceptions = writerFactory(3).writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

        exceptions.forEach(System.out::println);
    }
}
