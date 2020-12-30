package net.galuhpradipta.bbwapi.repository;

import net.galuhpradipta.bbwapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
