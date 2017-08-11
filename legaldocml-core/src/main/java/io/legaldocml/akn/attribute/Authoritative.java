package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies whether the translation is authoritative or not.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="authoritative">
 * 	   <xsd:attribute name="authoritative" type="xsd:boolean" />
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Authoritative extends AknObject {

    /**
     * Attribute "authoritative".
     */
    String ATTRIBUTE = "authoritative";

    Boolean getAuthoritative();

    void setAuthoritative(Boolean authoritative);
}