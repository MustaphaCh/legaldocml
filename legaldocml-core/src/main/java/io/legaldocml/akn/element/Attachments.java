package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;

import static io.legaldocml.akn.AknElements.ATTACHMENTS;

/**
 *
 * <pre>
 *  <xsd:element name="attachments">
 *  	<xsd:complexType>
 *      	<xsd:sequence>
 *          	<xsd:element ref="componentRef" minOccurs="1" maxOccurs="unbounded"/>
 *          <xsd:sequence>
 *      	<xsd:attributeGroup ref="coreopt"/>
 * 		<xsd:complexType>
 * 	<xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Attachments extends AknObject {

    /**
     * {@inheritDoc}
     */
    @Override
    default String name() {
        return ATTACHMENTS;
    }

}