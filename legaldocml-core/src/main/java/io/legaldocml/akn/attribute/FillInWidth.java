package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute width of the element fillIn specifies the width of the fillIn element.
 *
 * ```xml
 * <xsd:attributeGroup name="fillInWidth">
 *   <xsd:attribute name="width" type="xsd:string"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface FillInWidth extends AknObject {

    String getWidth();

    void setWidth(String width);

}
