package dev.simmons.entities;

public class Client {
    private int clientId;
    private String clientUsername;
    private String clientName;
    private String clientPassword;
    private byte[] clientSalt;

    public Client() {

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

    public byte[] getClientSalt() {
        return clientSalt;
    }

    public void setClientSalt(byte[] clientSalt) {
        this.clientSalt = clientSalt;
    }
}
