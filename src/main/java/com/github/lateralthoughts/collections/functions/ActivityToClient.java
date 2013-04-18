package com.github.lateralthoughts.collections.functions;

import com.github.lateralthoughts.domain.Activity;
import com.github.lateralthoughts.domain.Company;
import com.google.common.base.Function;

public class ActivityToClient implements Function<Activity, Company> {
    @Override
    public Company apply(Activity activity) {
        return activity.getClient();
    }
}
