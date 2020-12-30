package net.galuhpradipta.bbwapi.service;

import net.galuhpradipta.bbwapi.entity.Client;
import net.galuhpradipta.bbwapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void seedClient() {
        Client client = clientRepository.findByClientId("001");
        if (client == null) {
            Client newClient = new Client();
            newClient.setClientId("001");
            newClient.setClientKey("1d7c9dedc7d3e187e5e908df1780c6c3899");
            clientRepository.save(newClient);
        }
    }
}
