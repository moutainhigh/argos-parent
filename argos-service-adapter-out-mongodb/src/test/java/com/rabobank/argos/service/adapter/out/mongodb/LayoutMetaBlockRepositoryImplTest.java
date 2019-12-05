package com.rabobank.argos.service.adapter.out.mongodb;

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

import com.mongodb.client.result.UpdateResult;
import com.rabobank.argos.domain.layout.LayoutMetaBlock;
import com.rabobank.argos.service.adapter.out.mongodb.layout.LayoutMetaBlockRepositoryImpl;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LayoutMetaBlockRepositoryImplTest {

    private static final String COLLECTION_NAME = "layoutMetaBlocks";
    private static final String SUPPLY_CHAIN_ID = "supplyChainId";
    private static final String LAYOUT_METABLOCK_ID = "layoutMetablockId";

    @Mock
    private MongoTemplate template;

    @Mock
    private LayoutMetaBlock layoutMetaBlock;

    private LayoutMetaBlockRepositoryImpl repository;

    @Mock
    private IndexOperations indexOperations;

    @Captor
    private ArgumentCaptor<Query> queryArgumentCaptor;

    @Captor
    private ArgumentCaptor<Update> updateArgumentCaptor;


    @Mock
    private MongoConverter converter;

    @Mock
    private Document layoutMetaBlockDocument;

    @Mock
    private UpdateResult updateResult;

    @BeforeEach
    void setUp() {
        repository = new LayoutMetaBlockRepositoryImpl(template);
    }

    @Test
    void postConstruct() {
        when(template.indexOps(COLLECTION_NAME)).thenReturn(indexOperations);
        repository.postConstruct();
        verify(template, times(2)).indexOps(COLLECTION_NAME);
    }

    @Test
    void save() {
        repository.save(layoutMetaBlock);
        verify(template).save(layoutMetaBlock, COLLECTION_NAME);
    }

    @Test
    void findBySupplyChainAndId() {
        when(template.findOne(any(Query.class), eq(LayoutMetaBlock.class), eq(COLLECTION_NAME))).thenReturn(layoutMetaBlock);
        assertThat(repository.findBySupplyChainAndId(SUPPLY_CHAIN_ID, LAYOUT_METABLOCK_ID), is(Optional.of(layoutMetaBlock)));
        verify(template).findOne(queryArgumentCaptor.capture(), eq(LayoutMetaBlock.class), eq(COLLECTION_NAME));
        assertThat(queryArgumentCaptor.getValue().toString(), is("Query: { \"supplyChainId\" : \"supplyChainId\", \"layoutMetaBlockId\" : \"layoutMetablockId\"}, Fields: {}, Sort: {}"));
    }

    @Test
    void findBySupplyChainId() {
        when(template.find(any(Query.class), eq(LayoutMetaBlock.class), eq(COLLECTION_NAME))).thenReturn(Collections.singletonList(layoutMetaBlock));
        assertThat(repository.findBySupplyChainId(SUPPLY_CHAIN_ID), contains(layoutMetaBlock));
        verify(template).find(queryArgumentCaptor.capture(), eq(LayoutMetaBlock.class), eq(COLLECTION_NAME));
        assertThat(queryArgumentCaptor.getValue().toString(), is("Query: { \"supplyChainId\" : \"supplyChainId\"}, Fields: {}, Sort: {}"));

    }

    @Test
    void update() {
        when(template.getConverter()).thenReturn(converter);
        when(converter.convertToMongoType(layoutMetaBlock)).thenReturn(layoutMetaBlockDocument);
        when(updateResult.getMatchedCount()).thenReturn(1L);
        when(template.updateFirst(any(Query.class), any(Update.class), eq(LayoutMetaBlock.class), eq(COLLECTION_NAME))).thenReturn(updateResult);
        assertThat(repository.update(SUPPLY_CHAIN_ID, LAYOUT_METABLOCK_ID, layoutMetaBlock), is(true));

        verify(template).updateFirst(queryArgumentCaptor.capture(), updateArgumentCaptor.capture(), eq(LayoutMetaBlock.class), eq(COLLECTION_NAME));
        assertThat(queryArgumentCaptor.getValue().toString(), is("Query: { \"supplyChainId\" : \"supplyChainId\", \"layoutMetaBlockId\" : \"layoutMetablockId\"}, Fields: {}, Sort: {}"));
        assertThat(updateArgumentCaptor.getValue().toString(), is("{}"));
    }
}
