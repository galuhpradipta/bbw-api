package net.galuhpradipta.bbwapi;

import lombok.extern.slf4j.Slf4j;
import net.galuhpradipta.bbwapi.entity.Client;
import net.galuhpradipta.bbwapi.entity.VirtualAccount;
import net.galuhpradipta.bbwapi.repository.ClientRepository;
import net.galuhpradipta.bbwapi.repository.VirtualAccountRepository;
import net.galuhpradipta.bbwapi.service.ClientService;
import net.galuhpradipta.bbwapi.util.SignatureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
public class BbwApiApplication {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private VirtualAccountRepository virtualAccountRepository;

	public static void main(String[] args) {
		SpringApplication.run(BbwApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() throws Exception {

		Client client = clientRepository.findByClientId("001");
		if (client == null) {
			Client newClient = new Client();
			newClient.setClientId("001");
			newClient.setClientKey("1d7c9dedc7d3e187e5e908df1780c6c3899");
			clientRepository.save(newClient);
		}

		VirtualAccount virtualAccount = virtualAccountRepository.findByAccountNumber("7771234567890");
		if (virtualAccount == null) {
			VirtualAccount newVirtualAccount = new VirtualAccount();
			newVirtualAccount.setClient(client);
			newVirtualAccount.setAccountName("FebriHaryadi");
			newVirtualAccount.setAccountNumber("7771234567890");
			virtualAccountRepository.save(newVirtualAccount);
		}


	}

}
