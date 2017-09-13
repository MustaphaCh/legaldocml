package io.legaldocml.xpath.cerebro;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Act;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.akn.element.AmendmentList;
import io.legaldocml.akn.element.Attachments;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.Body;
import io.legaldocml.akn.element.Components;
import io.legaldocml.akn.element.Conclusions;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.DebateBody;
import io.legaldocml.akn.element.HierarchicalStructure;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.Preamble;
import io.legaldocml.akn.element.Preface;

@SuppressWarnings("unchecked")
public class Cerebro {

    private static ImmutableMap<Class<? extends AknObject>, CerebroCodeDefinition> DEFINITIONS;


    static {

        ImmutableMap.Builder builder = ImmutableMap.<Class<? extends AknObject>, CerebroCodeDefinition>builder();

        builder.put(AkomaNtoso.class, new CerebroCodeDefinition(
                new CerebroCodeLinkGetter(Bill.class,  "getDocumentType"),
                new CerebroCodeLinkGetter(AmendmentList.class,  "getDocumentType"),
                new CerebroCodeLinkGetter(Amendment.class,  "getDocumentType"),
                new CerebroCodeLinkGetter(Act.class,  "getDocumentType"),
                new CerebroCodeLinkGetter(Debate.class,  "getDocumentType"),
                new CerebroCodeLinkGetter(Portion.class,  "getDocumentType")
                ));



        builder.put(Debate.class, new CerebroCodeDefinition(
                new CerebroCodeLinkGetter(Meta.class, "getMeta"),
                new CerebroCodeLinkGetter(CoverPage.class, "getCoverPage"),
                new CerebroCodeLinkGetter(Preface.class, "getPreface"),
                new CerebroCodeLinkGetter(DebateBody.class, "getDebateBody"),
                new CerebroCodeLinkGetter(Conclusions.class, "getConclusions"),
                new CerebroCodeLinkGetter(Attachments.class, "getAttachments"),
                new CerebroCodeLinkGetter(Components.class, "getComponents")
        ));

      /*  builder.put(AkomaNtoso.class, new CerebroDefinition(


                new CerebroDirectLink<AkomaNtoso<Bill>, Bill>(Bill.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso<AmendmentList>, AmendmentList>(AmendmentList.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso<Amendment>, Amendment>(Amendment.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso<Debate>, Debate>(Debate.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso, Components>(Components.class, AkomaNtosoType::getComponents)
                ));*/

     /*   builder.put(Act.class, hierarchicalStructure(Act.class));
        builder.put(Bill.class, hierarchicalStructure(Bill.class));
        builder.put(Debate.class, new CerebroDefinition(
                new CerebroDirectLink<>(Meta.class, Debate::getMeta),
                new CerebroDirectLink<>(CoverPage.class, Debate::getCoverPage),
                new CerebroDirectLink<>(Preface.class, Debate::getPreface),
                new CerebroDirectLink<>(DebateBody.class, Debate::getDebateBody),
                new CerebroDirectLink<>(Conclusions.class, Debate::getConclusions),
                new CerebroDirectLink<>(Attachments.class, Debate::getAttachments),
                new CerebroDirectLink<>(Components.class, Debate::getComponents)
        ));

        builder.put(Meta.class, new CerebroDefinition(
                new CerebroDirectLink<>(Identification.class, Meta::getIdentification),
                new CerebroDirectLink<>(Publication.class, Meta::getPublication),
                new CerebroListLink<>(Classification.class, Meta::getClassifications),
                new CerebroListLink<>(Lifecycle.class, Meta::getLifecycles),
                new CerebroListLink<>(Workflow.class, Meta::getWorkflows),
                new CerebroListLink<>(Analysis.class, Meta::getAnalysis),
                new CerebroListLink<>(TemporalData.class, Meta::getTemporalData),
                new CerebroListLink<Meta, References>(References.class, Meta::getReferences),
                new CerebroListLink<>(Notes.class, Meta::getNotes),
                new CerebroListLink<>(Proprietary.class, Meta::getProprietaries),
                new CerebroListLink<>(Presentation.class, Meta::getPresentations)
        ));

        builder.put(Workflow.class, new CerebroDefinition(
                new CerebroListLink<>(Step.class, Workflow::getSteps)
        ));

        builder.put(Analysis.class, new CerebroDefinition(
                new CerebroDirectLink<>(ActiveModifications.class, Analysis::getActiveModifications),
                new CerebroDirectLink<>(PassiveModifications.class, Analysis::getPassiveModifications),
                new CerebroDirectLink<>(Restrictions.class, Analysis::getRestrictions),
                new CerebroDirectLink<>(Judicial.class, Analysis::getJudicial),
                new CerebroDirectLink<>(Parliamentary.class, Analysis::getParliamentary),
                new CerebroDirectLink<>(Mappings.class, Analysis::getMappings),
                new CerebroListLink<>(OtherReferences.class, Analysis::getOtherReferences),
                new CerebroListLink<>(OtherAnalysis.class, Analysis::getOtherAnalysis)
        ));


        builder.put(Parliamentary.class, new CerebroDefinition(
                new CerebroListLink<>(ParliamentaryAnalysisElement.class, Parliamentary::getElements),
                new CerebroListFilterLink<>(ParliamentaryAnalysisElement.class, Voting.class, Parliamentary::getElements),
                new CerebroListFilterLink<>(ParliamentaryAnalysisElement.class, QuorumVerification.class, Parliamentary::getElements),
                new CerebroListFilterLink<>(ParliamentaryAnalysisElement.class, Recount.class, Parliamentary::getElements)
        ));*/

        DEFINITIONS = builder.build();



    }

    public static CerebroCodeLink getCodeLink(Class<? extends AknObject> parent, Class<? extends AknObject> child) {

        CerebroCodeDefinition definition = DEFINITIONS.get(parent);

        if (definition == null) {
            throw new CerebroException("No defintion found for [" + parent + "] (child=" + child + ")");
        }

        return definition.getCodeLink(child);

    }


    public static CerebroLink getLink(Class<? extends AknObject> parent, Class<? extends AknObject> child) {

      /*  CerebroDefinition definition = DEFINITIONS.get(parent);

        if (definition == null) {
            throw new CerebroException("No defintion found for [" + parent + "] (child=" + child + ")");
        }

        return definition.getLink(child);*/

      return null;

    }


    private static <T extends HierarchicalStructure> CerebroDefinition<T> hierarchicalStructure(Class<T> clazz) {
        return new CerebroDefinition<>(
                new CerebroDirectLink<T, Meta>(Meta.class, HierarchicalStructure::getMeta),
                new CerebroDirectLink<T, CoverPage>(CoverPage.class, HierarchicalStructure::getCoverPage),
                new CerebroDirectLink<T, Preface>(Preface.class, HierarchicalStructure::getPreface),
                new CerebroDirectLink<T, Preamble>(Preamble.class, HierarchicalStructure::getPreamble),
                new CerebroDirectLink<T, Body>(Body.class, HierarchicalStructure::getBody),
                new CerebroDirectLink<T, Conclusions>(Conclusions.class, HierarchicalStructure::getConclusions),
                new CerebroDirectLink<T, Attachments>(Attachments.class, HierarchicalStructure::getAttachments),
                new CerebroDirectLink<T, Components>(Components.class, HierarchicalStructure::getComponents)
        );
    }
}
