package instrument;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EqualsAndHashCode
@ToString
public class InstrumentEconomics {
    private final Map<String, String> alternateIds;
    private final Map<Object, Object> instrumentAttributes;

    private InstrumentEconomics(Builder builder) {
        this.alternateIds = Map.copyOf(builder.alternateIds);
        this.instrumentAttributes = Map.copyOf(builder.instrumentAttributes);
    }

    public <T> Optional<T> getAttribute(InstrumentAttributes.ValueInstrumentAttribute<T> instrumentAttribute) {
        return Optional.ofNullable(instrumentAttribute.cast(instrumentAttributes.get(instrumentAttribute)));
    }

    public <T> Optional<List<T>> getAttribute(InstrumentAttributes.ListInstrumentAttribute<T> instrumentAttribute) {
        return Optional.ofNullable(instrumentAttribute.cast(instrumentAttributes.get(instrumentAttribute)));
    }

    public <K, V> Optional<Map<K, V>> getAttribute(InstrumentAttributes.MapInstrumentAttribute<K, V> instrumentAttribute) {
        return Optional.ofNullable(instrumentAttribute.cast(instrumentAttributes.get(instrumentAttribute)));
    }

    public <T> T getRequiredAttribute(InstrumentAttributes.ValueInstrumentAttribute<T> instrumentAttribute) {
        return getAttribute(instrumentAttribute).orElseThrow();
    }

    public <T> List<T> getRequiredAttribute(InstrumentAttributes.ListInstrumentAttribute<T> instrumentAttribute) {
        return getAttribute(instrumentAttribute).orElseThrow();
    }

    public <K, V> Map<K, V> getRequiredAttribute(InstrumentAttributes.MapInstrumentAttribute<K, V> instrumentAttribute) {
        return getAttribute(instrumentAttribute).orElseThrow();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private final Map<String, String> alternateIds = new HashMap<>();
        private final Map<Object, Object> instrumentAttributes = new HashMap<>();

        public <T> Builder addAttribute(InstrumentAttributes.ValueInstrumentAttribute<T> instrumentAttribute, T value) {
            this.instrumentAttributes.put(instrumentAttribute, value);
            return this;
        }

        public <T> Builder addAttribute(InstrumentAttributes.ListInstrumentAttribute<T> instrumentAttribute, List<? extends T> value) {
            this.instrumentAttributes.put(instrumentAttribute, value);
            return this;
        }

        public <K, V> Builder addAttribute(InstrumentAttributes.MapInstrumentAttribute<K, V> instrumentAttribute, Map<? extends K, ? extends V> value) {
            this.instrumentAttributes.put(instrumentAttribute, value);
            return this;
        }

        public InstrumentEconomics build() {
            return new InstrumentEconomics(this);
        }
    }
}
