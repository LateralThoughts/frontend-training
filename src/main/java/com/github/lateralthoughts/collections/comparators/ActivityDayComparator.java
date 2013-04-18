package com.github.lateralthoughts.collections.comparators;

import com.github.lateralthoughts.domain.Activity;

import java.util.Comparator;

import static com.google.common.base.Preconditions.checkNotNull;

public class ActivityDayComparator implements Comparator<Activity> {
    @Override
    public int compare(Activity activity, Activity other) {
        checkNotNull(activity, other);
        return activity.getDay().compareTo(other.getDay());
    }
}
