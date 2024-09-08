package exercise;

// BEGIN
public class InputTag implements TagInterface {
    private final String value;
    private final String type;

    @Override
    public String render() {
        return String.format("<input type=\"%s\" value=\"%s\">", type, value);
    }

    InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
// END
