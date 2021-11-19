import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "")
public interface AInstrument {
    Optional<String> getId();
    Map<String, String> alternateIds();
}
