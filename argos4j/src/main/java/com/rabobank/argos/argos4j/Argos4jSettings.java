package com.rabobank.argos.argos4j;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class Argos4jSettings implements Serializable {

    public static final String DEFAULT_EXCLUDE_PATTERNS = "**.{git,link}**";

    @Builder.Default
    private final String excludePatterns = DEFAULT_EXCLUDE_PATTERNS;

    @Builder.Default
    private final boolean followSymlinkDirs = true;

    @Builder.Default
    private final boolean normalizeLineEndings = true;

    private final String supplyChainName;
    private final String stepName;
    private final SigningKey signingKey;
    private final String argosServerBaseUrl;

}
