package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attributes startQuote and endQuote are used in quotedText, quotedStructure, embeddedText and embeddedStructure to
 * specify the start and quote character delimiting the quoted or embedded part. If the characters are the same, one can
 * use only startQuote. Attribute inlineQuote is used for marking the character showing continuation of a quote e.g. at
 * the beginning of each page or at the beginning of each line of the quote
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="quote">
 * 	    <xsd:attribute name="startQuote" type="xsd:string"/>
 *      <xsd:attribute name="endQuote" type="xsd:string"/>
 *      <xsd:attribute name="inlineQuote" type="xsd:string"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Quote extends AknObject {

    /**
     * Attribute name "startQuote".
     */
    String ATTRIBUTE_STARTQUOTE = "startQuote";
    /**
     * Attribute name "endQuote".
     */
    String ATTRIBUTE_ENDQUOTE = "endQuote";
    /**
     * Attribute name "inlineQuote".
     */
    String ATTRIBUTE_INLINEQUOTE = "inlineQuote";


    String getStartQuote();

    void setStartQuote(String startQuote);

    String getEndQuote();

    void setEndQuote(String endQuote);

    String getInlineQuote();

    void setInlineQuote(String inlineQuote);
}