package com.nan.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义 lint 规则必须提供一个继承自 IssueRegistry 的类
 * 将所有自定义的 Detector 汇总，对外提供
 */
public class MyIssueRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(LogUtilDetector.ISSUE);
    }
}
