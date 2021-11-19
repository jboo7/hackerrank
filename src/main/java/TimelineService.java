import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TimelineService {
    public Long calculateWorkMinute(Timeline t) {
        return t.getWorkMinutes() / t.getRatio();
    }


    public List<String> getTimeLineMinutes(List<Timeline> timelines) {
/*
TODO: For each timelines, call longMinute = calculateWorkMinute(Timeline t) to get minutes (Try to use some java 8 features).
Catch exception of calculateWorkMinute method. If there is any error, don't build the following string and skip that particular timeline.
Build the string: "ID " + id + " = " + longMinute + " Min". Ex: "ID 1012 = 70 Min", and add the string to the return list.
return the list of string.
*/

        return timelines.stream()
                .map(this::convertTimeline)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
    }

    private String convertTimeline(Timeline timeline) {
        try {
            return String.format("ID %d = %d Min", timeline.getId(), calculateWorkMinute(timeline));
        } catch (Exception e) {
            return "";
        }
    }


    public List<Timeline> getValidRatio(List<Timeline> timelines) {
        //return the list Timelines whose ratio is > 0
        return timelines.stream()
                        .filter(t -> t.getRatio() != null && t.getRatio() > 0)
                        .collect(Collectors.toUnmodifiableList());
    }

}