package dev.simmons.data;

import dev.simmons.entities.Client;

public interface ClientDAO {
    Client createClient(Client client);

    Client getClient(int id);
    Client getClient(String username);

    Client updateClient(Client newClient);

    boolean deleteClient(Client client);
    boolean deleteClient(int id);
}
