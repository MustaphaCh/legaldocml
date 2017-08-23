package io.legaldocml.akn.type;

import io.legaldocml.unsafe.UnsafeString;

import java.util.function.Function;

/**
 * These values are references to existing agents (i.e., TLCPerson or TLCOrganization) only, i.e., given an existing eId
 * of an agent of the form XYZ, the value is necessarily #XYZ.
 * <p>
 * <pre>
 *   &lt;xsd:simpleType name="agentRef"&gt;
 *     &lt;xsd:restriction base="xsd:anyURI"/&gt;
 *   &lt;xsd:simpleType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AgentRef extends AbstractRef {

    @SuppressWarnings("ALL")
    private static final Function<char[], AgentRef> INSTANTIATOR_AGENT_REF = array -> new AgentRef(array);

    private AgentRef(char[] value) {
        super(value);
    }

    public static AgentRef raw(char[] value) {
        return new AgentRef(value);
    }

    public static AgentRef valueOf(char[] value) {
        return valueOf(value, INSTANTIATOR_AGENT_REF);
    }

    public static AgentRef valueOf(String value) {
        return valueOf(UnsafeString.getChars(value));
    }

}
