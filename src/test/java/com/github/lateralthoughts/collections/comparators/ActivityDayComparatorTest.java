package com.github.lateralthoughts.collections.comparators;

import com.github.lateralthoughts.domain.Activity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ActivityDayComparatorTest {

    private ActivityDayComparator comparator;

    @Before
    public void setup() {
        comparator = new ActivityDayComparator();
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_error_if_first_activity_is_null() {
        comparator.compare(null, new Activity());
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_error_if_second_activity_is_null() {
        comparator.compare(new Activity(), null);
    }

    @Test
    public void should_return_0_for_same_date_activities() {
        assertThat(comparator.compare(
            activity(new Date(1000)),
            activity(new Date(1000)))
        ).isEqualTo(0);
    }

    @Test
    public void should_return_positive_integer_when_first_activity_occurs_last() {
        assertThat(comparator.compare(
            activity(new Date(1001)),
            activity(new Date(1000)))
        ).isPositive();
    }

    @Test
    public void should_return_negative_integer_when_first_activity_occurs_first() {
        assertThat(comparator.compare(
            activity(new Date(1000)),
            activity(new Date(1001)))
        ).isNegative();
    }

    private Activity activity(Date date) {
        Activity activity = new Activity();
        activity.setDay(date);
        return activity;
    }
}
