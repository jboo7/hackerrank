import instrument.InstrumentAttributes;
import instrument.InstrumentEconomics;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@ToString
public class Data {
    public static void main(String[] args) {
        var i = InstrumentEconomics.newBuilder()
                                   .addAttribute(InstrumentAttributes.INT_ATTRIBUTE, 1)
                                   .addAttribute(InstrumentAttributes.LONG_ATTRIBUTE, 1L)
                                   .addAttribute(InstrumentAttributes.STRING_ATTRIBUTE, "name1")
                                   .addAttribute(InstrumentAttributes.STRING_LIST, List.of("1", "2"))
                                   .addAttribute(InstrumentAttributes.STRING_OF_STRING_MAP, Map.of("1", "2"))
                                   .build();

        LOG.info("{}", i);
        LOG.info("{}", i.getRequiredAttribute(InstrumentAttributes.STRING_LIST));
    }
}
