package io.legaldocml.business.builder;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.element.HierarchyBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.channels.Channels;

import static io.legaldocml.akn.AknElements.LIST;

@ExtendWith(LoggerInstancePostProcessor.class)
public class HierarchyBuilderTest {

    @Test
    public void testNum() throws IOException {

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Bill> businessBuilder = provider.newBuilder(AknElements.BILL);

        //HierarchyStrategy strategy = new HierarchyStrategyBuilder().article().paragraph().build();

        Article article = new Article();
        businessBuilder.getAkomaNtoso().getDocumentType().getBody().add(article);

        HierarchyBuilder<Article> builder = new HierarchyBuilder<>(businessBuilder, article);
        builder.eId("1");
        builder.num().text("Art. 1.");
        builder.heading()
                .i()
                .text("(Modifiche al sistema elettorale della")
                .<InlineTypeBuilder<I>>eol()
                .text("Camera dei deputati)");

        HierarchyBuilder<Paragraph> paragraphBuilder = builder.next();
        paragraphBuilder.num().text("1.");
        paragraphBuilder.eId("1");

        HierarchyBuilder<List> listBuilder = paragraphBuilder.newChild(LIST);
        listBuilder.eId("1");
        listBuilder.intro()
                .eid("1")
                .p().text("Al testo unico delle leggi recanti norme per la elezione della Camera dei deputati, di cui al decreto del Presidente della Repubblica 30 marzo 1957, n. 361, sono apportate le seguenti modificazioni:");

        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(System.out), businessBuilder.getAkomaNtoso());
    }

}
