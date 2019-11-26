Feature: Link

  Background:
    * url karate.properties['server.baseurl']
    * call read('reset.feature')
    * def supplyChain = call read('create-supplychain.feature') { name: 'name'}
    * def linkPath = '/api/supplychain/'+ supplyChain.response.id + '/link'

  Scenario: store link with valid specifications should return a 204
    * call read('create-validlink.feature') {supplyChainId:#(supplyChain.response.id)}

  Scenario: store link with invalid specifications should return a 400 error
    Given path linkPath
    And request read('../testmessages/invalid-link.json')
    And header Content-Type = 'application/json'
    When method POST
    Then status 400
    And match response contains read('../testmessages/invalid-link-response.json')

  Scenario: find link with valid supplychainid should return a 200
    * call read('create-validlink.feature') {supplyChainId:#(supplyChain.response.id)}
    Given path linkPath
    When method GET
    Then status 200
    And match response[*] contains read('../testmessages/valid-link-response.json')

  Scenario: find link with valid supplychainid and optionalHash should return a 200
    * call read('create-validlink.feature') {supplyChainId:#(supplyChain.response.id)}
    Given path linkPath
    And param optionalHash = '74a88c1cb96211a8f648af3509a1207b2d4a15c0202cfaa10abad8cc26300c63'
    When method GET
    Then status 200
    And match response[*] contains read('../testmessages/valid-link-response.json')