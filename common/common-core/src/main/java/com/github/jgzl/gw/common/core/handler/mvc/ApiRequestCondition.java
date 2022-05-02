package com.github.jgzl.gw.common.core.handler.mvc;

import com.github.jgzl.gw.common.core.constant.HttpHeaderConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ApiRequestCondition implements RequestCondition<ApiRequestCondition> {

    /**
     * support v1.1.1, v1.1, v1; three levels .
     */
    private static final Pattern VERSION_PREFIX_PATTERN_1 = Pattern.compile("v\\d\\.\\d\\.\\d");
    private static final Pattern VERSION_PREFIX_PATTERN_2 = Pattern.compile("v\\d\\.\\d");
    private static final Pattern VERSION_PREFIX_PATTERN_3 = Pattern.compile("v\\d");
    private static final List<Pattern> VERSION_LIST = Collections.unmodifiableList(
            Arrays.asList(VERSION_PREFIX_PATTERN_1, VERSION_PREFIX_PATTERN_2, VERSION_PREFIX_PATTERN_3)
    );

    private String apiVersion;

    public ApiRequestCondition(String apiVersion) {
        this.apiVersion = apiVersion;
    }
    public String getApiVersion() {
        return apiVersion;
    }

    @Override
    public ApiRequestCondition combine(ApiRequestCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiRequestCondition(other.getApiVersion());
    }

    @Override
    public ApiRequestCondition getMatchingCondition(HttpServletRequest request) {
        String headApiVersion = request.getHeader(HttpHeaderConstants.X_BUSINESS_API_VERSION);
        if (compareVersion(headApiVersion, this.apiVersion) >= 0) {
            log.info("version={}, apiVersion={}", headApiVersion, this.apiVersion);
            return this;
        }
        return null;
    }

    @Override
    public int compareTo(ApiRequestCondition other, HttpServletRequest request) {
        return compareVersion(other.getApiVersion(), this.apiVersion);
    }

    private int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            throw new RuntimeException("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int maxLength = Math.max(versionArray1.length, versionArray2.length);
        int v1count = 0;
        for (int i = 0; i < versionArray1.length; i++) {
            v1count += Long.parseLong(versionArray1[i]) * 10 * (maxLength-1-i);
        }
        int v2count = 0;
        for (int i = 0; i < versionArray2.length; i++) {
            v2count += Long.parseLong(versionArray2[i]) * 10 * (maxLength-1-i);
        }
        return v1count-v2count;
    }
}
