package net.galuhpradipta.bbwapi.repository;

import net.galuhpradipta.bbwapi.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, Integer> {

    VirtualAccount findByAccountNumber(String accountNumber);
}
