package instrument;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PACKAGE;

public abstract class InstrumentAttributes {
    public static final IntegerInstrumentAttribute INT_ATTRIBUTE = new IntegerInstrumentAttribute("INTEGER");
    public static final LongInstrumentAttribute LONG_ATTRIBUTE = new LongInstrumentAttribute("LONG");
    public static final StringInstrumentAttribute STRING_ATTRIBUTE = new StringInstrumentAttribute("STRING");
    public static final ListInstrumentAttribute<String> STRING_LIST = new ListInstrumentAttribute<>("STRING_LIST", String.class);
    public static final MapInstrumentAttribute<String, String> STRING_OF_STRING_MAP = new MapInstrumentAttribute<>("STRING_OF_STRING_MAP", String.class,
                                                                                                                   String.class);

    @RequiredArgsConstructor(access = PACKAGE)
    @EqualsAndHashCode
    public static class ValueInstrumentAttribute<T> {
        private final String name;
        private final Class<T> type;

        @Override
        public final String toString() {
            return String.format("%s[%s]", name, type.getSimpleName());
        }

        final T cast(Object value) {
            if (value == null || type.isInstance(value)) {
                return type.cast(value);
            }
            throw new IllegalArgumentException();
        }
    }

    public static class StringInstrumentAttribute extends ValueInstrumentAttribute<String> {
        StringInstrumentAttribute(String name) {
            super(name, String.class);
        }
    }

    public static class IntegerInstrumentAttribute extends ValueInstrumentAttribute<Integer> {
        IntegerInstrumentAttribute(String name) {
            super(name, Integer.class);
        }
    }

    public static class LongInstrumentAttribute extends ValueInstrumentAttribute<Long> {
        LongInstrumentAttribute(String name) {
            super(name, Long.class);
        }
    }

    @RequiredArgsConstructor(access = PACKAGE)
    @EqualsAndHashCode
    public static final class ListInstrumentAttribute<T> {
        private final String name;
        private final Class<T> type;

        @Override
        public String toString() {
            return String.format("%s[List<%s>]", name, type.getSimpleName());
        }

        @SuppressWarnings("unchecked")
        List<T> cast(Object value) {
            if (value == null || value instanceof List) {
                return (List<T>) value;
            }
            throw new IllegalArgumentException();
        }
    }

    @RequiredArgsConstructor(access = PACKAGE)
    @EqualsAndHashCode
    public static final class MapInstrumentAttribute<K, V> {
        private final String name;
        private final Class<K> keyType;
        private final Class<V> valueType;

        @Override
        public String toString() {
            return String.format("%s[Map<%s,%s>]", name, keyType.getSimpleName(), valueType.getSimpleName());
        }

        @SuppressWarnings("unchecked")
        Map<K, V> cast(Object value) {
            if (value == null || value instanceof Map) {
                return (Map<K, V>) value;
            }
            throw new IllegalArgumentException();
        }
    }
}
