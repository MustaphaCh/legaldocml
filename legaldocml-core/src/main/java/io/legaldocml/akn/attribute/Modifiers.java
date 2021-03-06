package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * These attributes are used in the analysis to allow manifestation editors to specify whether the analysis is complete
 * and/or ignored in the text.
 *
 * ```xml
 * <xsd:attributeGroup name="modifiers">
 *   <xsd:attribute name="exclusion" type="xsd:boolean" />
 *   <xsd:attribute name="incomplete" type="xsd:boolean" />
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Modifiers extends AknObject {

    Boolean getExclusion();

    void setExclusion(Boolean exclusion);

    Boolean getIncomplete();

    void setIncomplete(Boolean incomplete);

}