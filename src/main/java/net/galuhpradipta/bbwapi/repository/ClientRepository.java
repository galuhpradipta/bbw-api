package net.galuhpradipta.bbwapi.repository;

import net.galuhpradipta.bbwapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {


    Client findByClientId(String cliendId);
}
