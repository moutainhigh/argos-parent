package com.rabobank.argos.domain.layout.rule;

/*-
 * #%L
 * Argos Supply Chain Notary
 * %%
 * Copyright (C) 2019 Rabobank Nederland
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.rabobank.argos.domain.layout.exceptions.RuleVerificationError;
import com.rabobank.argos.domain.link.Artifact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AllowRuleTest {


    public static final String PATHARTIFACTJAVA = "/path/artifact.java";
    public static final String HASH = "hash";
    private AllowRule allowRule;
    private Set<Artifact> artifacts;

    @BeforeEach
    void setUp() {
        allowRule = AllowRule
                .builder()
                .pattern(PATHARTIFACTJAVA)
                .build();
        artifacts = new HashSet<>();
        artifacts.add(Artifact.builder().hash(HASH).uri(PATHARTIFACTJAVA).build());
    }

    @Test
    void verifyWithCorrectPatternWillReturnResult() throws RuleVerificationError {
        assertThat(allowRule.verify(artifacts, null, null).size(), is(1));
    }
}
