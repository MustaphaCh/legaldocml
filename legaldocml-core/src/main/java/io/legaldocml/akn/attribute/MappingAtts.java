package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.WidRef;

/**
 * The mappingAtts attributes represent the reference to the permanent wId (attribute original) of a structure, and to
 * the eId (attribute current) such structure had during the time interval included between an initial temporalGroup
 * (attribute start) and a final temporalGroup (attribute end).
 *
 * ```xml
 * <xsd:attributeGroup name="mappingAtts">
 *   <xsd:attribute name="original" type="wIdRef"/>
 *   <xsd:attribute name="current" type="eIdRef"/>
 *   <xsd:attribute name="start" type="eventRefRef"/>
 *   <xsd:attribute name="end" type="eventRefRef"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MappingAtts extends Role, Agent {

    WidRef getOriginal();

    void setOriginal(WidRef original);

    EidRef getCurrent();

    void setCurrent(EidRef current);

    EventRefRef getStart();

    void setStart(EventRefRef start);

    EventRefRef getEnd();

    void setEnd(EventRefRef end);
}
