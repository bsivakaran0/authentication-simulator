package com.paymoni.simulator.service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Service;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JweJwsService {

    private final CommonJsonService commonJsonService;

    public String toJwe(Object payload, String acquirerId, String requesterKey) {
        try {
            String plainJson = commonJsonService.toJson(payload);
            log.info("Plain response to Requestor {}", plainJson);
            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .keyID(acquirerId)
                    .build();
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .claim("body", plainJson)
                    .build();
            SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);

            RSAPrivateKey signingKey = CryptoConfig.getPrivateKeyMap().get(acquirerId);
            if (signingKey == null) {
                throw new IllegalArgumentException("No private key configured for alias: " + acquirerId);
            }
            JWSSigner signer = new RSASSASigner(signingKey);
            signedJWT.sign(signer);

            String jwsCompact = signedJWT.serialize();

            JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                    .contentType("JWT")
                    .build();
            JWEObject jweObject = new JWEObject(jweHeader, new Payload(jwsCompact));
            RSAPublicKey pub = CryptoConfig.getPublicKeyMap().get(requesterKey);
            if (pub == null) {
                throw new IllegalArgumentException("No public key configured for alias: " + requesterKey);
            }
            JWEEncrypter encrypter = new RSAEncrypter(pub);
            jweObject.encrypt(encrypter);
            return jweObject.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create JWE", e);
        }
    }

    public <T> T fromJwe(String jwe, Class<T> clazz, String acquirerId, String requesterKey) {
        try {
            JWEObject jweObject = JWEObject.parse(jwe);

            RSAPrivateKey priv = CryptoConfig.getPrivateKeyMap().get(acquirerId);
            if (priv == null) {
                throw new IllegalArgumentException("No private key configured for alias: " + acquirerId);
            }
            JWEDecrypter decrypter = new RSADecrypter(priv);
            jweObject.decrypt(decrypter);
            String jwsCompact = jweObject.getPayload().toString();

            SignedJWT signedJWT = SignedJWT.parse(jwsCompact);
            String keyId = signedJWT.getHeader().getKeyID();
            if (keyId == null || keyId.isBlank()) {
            	keyId = requesterKey;
            }
            RSAPublicKey verifyKey = CryptoConfig.getPublicKeyMap().get(keyId);
            if (verifyKey == null) {
                throw new IllegalArgumentException("No public key configured for alias: " + keyId);
            }
            JWSVerifier verifier = new RSASSAVerifier(verifyKey);
            if (!signedJWT.verify(verifier)) {
                throw new JOSEException("Invalid JWS signature");
            }
            String json = (String) signedJWT.getJWTClaimsSet().getClaim("body");
            return commonJsonService.fromJson(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JWE", e);
        }
    }
}