package com.nan.lint;

import com.android.resources.ResourceType;
import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import org.jetbrains.uast.UElement;

import java.util.Arrays;
import java.util.List;

public class LogUtilDetector extends Detector {

    public static final Issue ISSUE = Issue.create(
            "LogUtilUse",
            "避免使用Log/System.out.println",
            "使用LogUtil，LogUtil对系统的Log类进行了展示格式、逻辑等封装",
            Category.SECURITY, 5, Severity.WARNING,
            new Implementation(LogUtilDetector.class, Scope.JAVA_FILE_SCOPE));

    @Override
    public List<String> getApplicableMethodNames() {
        //用于返回我们需要查找的方法名称，因为打印日志会调用系统类Log对应的方法，我们通过简析方法名来达到替换的目的。系统找到对应的方法则会回调visitMethod
        return Arrays.asList("d", "e", "i", "v", "w");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression node, PsiMethod method) {
        JavaEvaluator evaluator = context.getEvaluator();
        //对方法的宿主类进行了检验，只有是android.util.Log下的方法才是我们最重要找的目标
        if (evaluator.isMemberInClass(method, "android.util.Log")) {
            String message = "请使用LogUtil";
            context.report(ISSUE, node, context.getLocation(node), message);
        }
    }

}
