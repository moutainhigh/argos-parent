package com.rabobank.argos.service.domain.layout;

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

import com.rabobank.argos.domain.layout.LayoutMetaBlock;

import java.util.List;
import java.util.Optional;

public interface LayoutMetaBlockRepository {
    void save(LayoutMetaBlock layoutMetaBlock);

    Optional<LayoutMetaBlock> findBySupplyChainAndId(String supplyChainId, String id);

    List<LayoutMetaBlock> findBySupplyChainId(String supplyChainId);

    boolean update(String supplyChainId, String layoutMetaBlockId, LayoutMetaBlock layoutMetaBlock);
}
