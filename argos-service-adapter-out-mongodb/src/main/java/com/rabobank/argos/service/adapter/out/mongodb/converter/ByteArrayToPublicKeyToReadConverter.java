package com.rabobank.argos.service.adapter.out.mongodb.converter;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import static com.rabobank.argos.domain.model.RSAPublicKeyFactory.instance;

public class ByteArrayToPublicKeyToReadConverter implements Converter<Binary, PublicKey> {

    @Override
    public PublicKey convert(Binary bytes) {
        try {
            return instance(bytes.getData());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
