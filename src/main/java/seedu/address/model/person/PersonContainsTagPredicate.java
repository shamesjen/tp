package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class PersonContainsTagPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private boolean isAll;

    /**
     * Constructor for the PersonContainsTagPredicate.
     * @param keywords
     * @param isAll
     */
    public PersonContainsTagPredicate(List<String> keywords, boolean isAll) {
        this.keywords = keywords;
        this.isAll = isAll;
    }

    /**
     * Tests if the person has any or all of the keywords in their tags.
     * @param person
     * @return
     */
    @Override
    public boolean test(Person person) {
        if (isAll) {
            return allTest(person);
        } else {
            return anyTest(person);
        }
    }

    /**
     * Returns true if the person has any of the keywords in their tags.
     * @param person
     * @return
     */
    public boolean anyTest(Person person) {
        Set<Tag> tags = person.getTags();
        return keywords.stream()
                .anyMatch(keyword ->
                    tags.stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword))
                );
    }

    /**
     * Returns true if the person has all of the keywords in their tags.
     * @param person
     * @return
     */
    public boolean allTest(Person person) {
        Set<Tag> tags = person.getTags();
        return keywords.stream()
                .allMatch(keyword ->
                    tags.stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword))
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonContainsTagPredicate)) {
            return false;
        }

        PersonContainsTagPredicate otherTagContainsKeywordsPredicate = (PersonContainsTagPredicate) other;
        return keywords.equals(otherTagContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        // Assuming ToStringBuilder works similarly for lists
        return new seedu.address.commons.util.ToStringBuilder(this)
                .add("keywords", keywords)
                .toString();
    }
}
