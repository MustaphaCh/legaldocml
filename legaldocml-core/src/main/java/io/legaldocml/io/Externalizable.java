package io.legaldocml.io;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Externalizable {

    /**
     * The object implements the write method to save its contents by calling the methods of Output.
     *
     * @param writer the stream to write the object to
     *
     */
    void write(XmlWriter writer) throws IOException;

    /**
     * The object implements the read method to restore its contents by calling the methods of Input.
     *
     * @param reader the stream to read data from in order to restore the object
     */
    void read(XmlReader reader);

}
