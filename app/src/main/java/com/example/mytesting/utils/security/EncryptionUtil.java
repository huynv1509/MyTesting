package com.example.mytesting.utils.security;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

public class EncryptionUtil {
    private static  EncryptionUtil encryptUtilsInstance;
    private KeyStore mKeyStore;
    static EncryptionUtil getInstance() {
        synchronized (EncryptionUtil.class) {
            if (null == encryptUtilsInstance) {
                encryptUtilsInstance = new EncryptionUtil();
            }
        }
        return encryptUtilsInstance;
    }

    private void initKeyStore(Context context, String alias){
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            mKeyStore.load(null);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        createNewKeys(context, alias);
    }

    private void  createNewKeys(Context context, String alias){
        if(!"".equals(alias)) {
            try {
                if (!mKeyStore.containsAlias(alias)) {
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    end.add(Calendar.YEAR, 1);
                    KeyPairGeneratorSpec spec = null;
                    spec = new KeyPairGeneratorSpec.Builder(context)
                            .setAlias(alias)
                            .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(start.getTime())
                            .setEndDate(end.getTime())
                            .build();
                    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    generator.initialize(spec);
                    generator.generateKeyPair();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String encryptString(Context context, String value, String alias) {
        if(!"".equals(alias) && !"".equals(value)) {
            initKeyStore(context, alias);
            String encryptStr="";
            byte [] arr = null;
            try {
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry(alias, null);
                if(value.isEmpty()) {
                    return encryptStr;
                }

                Cipher inCipher;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    inCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
                }else {
                    inCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                }
                inCipher.init(Cipher.ENCRYPT_MODE, privateKeyEntry.getCertificate().getPublicKey());

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                CipherOutputStream cipherOutputStream = new CipherOutputStream(
                        outputStream, inCipher);
                cipherOutputStream.write(value.getBytes(StandardCharsets.UTF_8));
                cipherOutputStream.close();

                arr = outputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Base64.encodeToString(arr, Base64.DEFAULT);
        }
        return "";
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String decryptString(Context context, String value, String alias) {
        if(!"".equals(alias) && !"".equals(value)) {
            initKeyStore(context, alias);
            String decryptStr="";
            try {
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) mKeyStore.getEntry(alias, null);
                Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
                CipherInputStream cipherInputStream = new CipherInputStream(
                        new ByteArrayInputStream(Base64.decode(value, Base64.DEFAULT)), output);
                ArrayList<Byte> values = new ArrayList<>();
                int nextByte;
                while ((nextByte = cipherInputStream.read()) != -1) {
                    values.add((byte)nextByte);
                }

                byte[] bytes = new byte[values.size()];
                for(int i = 0; i < bytes.length; i++) {
                    bytes[i] = values.get(i);
                }

                decryptStr = new String(bytes, 0, bytes.length, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  decryptStr;
        }
        return "";
    }
}
