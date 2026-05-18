package com.paymoni.simulator.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CryptoConfig {

	private final Environment env;
	
    private static final String KEYSTORE_TYPE = "JKS";

	private static Map<String, RSAPublicKey> publicKeyMap;
    private static Map<String, RSAPrivateKey> privateKeyMap;
    
    @PostConstruct
    private void loadKeyStore() throws Exception {
    	String keystorePath = env.getProperty("jwe.key-store-path");
        String keystoreCred = env.getProperty("jwe.key-store-cred");
        String keyCred = env.getProperty("jwe.key-cred");
        
    	KeyStore ks = KeyStore.getInstance(KEYSTORE_TYPE);
        try (InputStream is = new FileInputStream(keystorePath)) {
            ks.load(is, keystoreCred.toCharArray());
        }
        
        publicKeyMap = loadPublicKeyMap(ks);
        privateKeyMap = loadPrivateKeyMap(ks, keyCred);
    }
    
    private Map<String, RSAPublicKey> loadPublicKeyMap(KeyStore ks) throws Exception {
        Map<String, RSAPublicKey> map = new HashMap<>();
        Enumeration<String> aliases = ks.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            Certificate cert = ks.getCertificate(alias);
            if (cert != null && cert.getPublicKey() instanceof RSAPublicKey) {
                map.put(alias, (RSAPublicKey) cert.getPublicKey());
            }
        }
        return map;
    }

    private Map<String, RSAPrivateKey> loadPrivateKeyMap(KeyStore ks, String keyCred) throws Exception {
        Map<String, RSAPrivateKey> map = new HashMap<>();
        Enumeration<String> aliases = ks.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            Key key = ks.getKey(alias, keyCred.toCharArray());
            if (key instanceof RSAPrivateKey) {
                map.put(alias, (RSAPrivateKey) key);
            }
        }
        return map;
    }
    
    public static Map<String, RSAPublicKey> getPublicKeyMap() {
		return publicKeyMap;
	}

	public static Map<String, RSAPrivateKey> getPrivateKeyMap() {
		return privateKeyMap;
	}
}