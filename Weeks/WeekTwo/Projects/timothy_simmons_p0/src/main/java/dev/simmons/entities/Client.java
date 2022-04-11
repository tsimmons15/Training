package dev.simmons.entities;

import dev.simmons.utilities.hashing.HashUtil;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

public class Client {
    private int clientId;
    private String clientUsername;
    private String clientName;
    private String clientPassword;
    private byte[] clientSalt;

    public Client() {
        clientSalt = new byte[16];
        Random rand = new Random();
        rand.nextBytes(clientSalt);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public void hashClientPassword(String clientPassword) {
        this.clientPassword = HashUtil.hashSaltedString(clientPassword, this.clientSalt);
    }

    public byte[] getClientSalt() {
        return clientSalt;
    }

    public void setClientSalt(byte[] clientSalt) {
        this.clientSalt = clientSalt;
    }

    public String getDiagnostics() {
        return "[Client](id=" + clientId + ", name=" + this.getClientName() + ", username=" + this.getClientUsername() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client)) {
            return false;
        }

        Client other = (Client)o;
        return this.clientId == other.getClientId();
    }
}
