package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final TagInterface inputTag;
    private final String text;

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", text, inputTag.render());
    }

    public LabelTag(String text, TagInterface inputTag) {
        this.text = text;
        this.inputTag = inputTag;
    }
}
// END
