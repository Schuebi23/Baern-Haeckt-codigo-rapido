package codigorapido.coshopcore.common.converter;

import java.util.Collection;
import java.util.List;

/**
 * @param <F> Type to convert from
 * @param <T> Type to convert to
 */
public interface Converter<F, T> {

    /**
     * @param source Mandatory. Object to convert from. This method does not mutate it.
     * @return New instance of the type to convert to. Never null.
     */
    T convert(F source);

    default List<T> convert(Collection<F> source) {
        return source.stream()
            .map(this::convert)
            .toList();
    }
}
