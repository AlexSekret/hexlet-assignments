package exercise;


// BEGIN

import java.util.Objects;

public class ReversedSequence implements CharSequence {
    private final String reversedString;

    public ReversedSequence(String string) {
        this.reversedString = new StringBuilder(string).reverse().toString();
    }

    @Override
    public int length() {
        return reversedString.length();
    }

    @Override
    public char charAt(int index) {
        return reversedString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedString.substring(start, end);
    }

    @Override
    public String toString() {
        return reversedString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReversedSequence)) {
            return false;
        }
        ReversedSequence that = (ReversedSequence) o;
        return Objects.equals(reversedString, that.reversedString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reversedString);
    }
}

// END
