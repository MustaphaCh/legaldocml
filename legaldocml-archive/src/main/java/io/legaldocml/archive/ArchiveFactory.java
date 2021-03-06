package io.legaldocml.archive;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.business.BusinessProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ArchiveFactory {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveFactory.class);

    /**
     * To hold all factory to manager Archive.
     */
    private static final ImmutableMap<String, ArchiveFactory> FACTORIES;

    static {
        ServiceLoader<ArchiveFactory> serviceLoader = ServiceLoader.load(ArchiveFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<ArchiveFactory> iterator = serviceLoader.iterator();
        ImmutableMap.Builder<String, ArchiveFactory> builder = ImmutableMap.builder();
        while (iterator.hasNext()) {
            ArchiveFactory factory = iterator.next();
            LOGGER.info("ArchiveFactory -> load factory [{}]", factory);
            builder.put(factory.type(), factory);
        }
        FACTORIES = builder.build();
    }

    protected abstract String type();

    protected abstract Archive writeOnly(BusinessProvider provider, Path path);

    protected abstract Archive readOnly(Path path);

    protected abstract Archive readWrite(BusinessProvider provider, Path source, Path target);

    public static Archive writeOnly(String type, BusinessProvider provider, Path path) {
        return getArchiveFactory(type).writeOnly(provider, path);
    }

    public static Archive readOnly(String type, Path path) {
        return getArchiveFactory(type).readOnly(path);

    }

    public static Archive readWrite(String type, BusinessProvider provider, Path source, Path target) {
        return getArchiveFactory(type).readWrite(provider, source, target);
    }

    private static ArchiveFactory getArchiveFactory(String type) {
        ArchiveFactory factory = FACTORIES.get(type);

        if (factory == null) {
            throw new ArchiveException(ArchiveException.Type.READ_OPEN, "No ServiceLoader found for ArchiveFactory for type [" + type + "]");
        }

        return factory;
    }

}