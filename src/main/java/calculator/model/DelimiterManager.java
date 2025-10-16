package calculator.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DelimiterManager {

    private final Set<String> delimiters;

    public DelimiterManager() {
        delimiters = new HashSet<>();
        delimiters.add(",");
        delimiters.add(":");
    }

    public String getRegex() {
        return delimiters.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }

    public void addCustomRegex(String regex) {
        delimiters.add(regex);
    }
}
