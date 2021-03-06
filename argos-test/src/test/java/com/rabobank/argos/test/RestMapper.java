/*
 * Copyright (C) 2019 - 2020 Rabobank Nederland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rabobank.argos.test;


import com.rabobank.argos.argos4j.rest.api.model.RestKeyPair;
import com.rabobank.argos.argos4j.rest.api.model.RestLayoutMetaBlock;
import com.rabobank.argos.test.rest.api.model.TestKeyPair;
import com.rabobank.argos.test.rest.api.model.TestLayoutMetaBlock;
import org.mapstruct.Mapper;

@Mapper
public interface RestMapper {

    RestLayoutMetaBlock mapTestLayout(TestLayoutMetaBlock layout);

    TestLayoutMetaBlock mapRestLayout(RestLayoutMetaBlock layout);

    RestKeyPair mapTestKeyPair(TestKeyPair keyPair);

    TestKeyPair mapRestKeyPair(RestKeyPair keyPair);

}
