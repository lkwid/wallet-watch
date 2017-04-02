package lkwid.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lkwid.entity.Operation;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long>{
	@Query("SELECT o FROM Operation o INNER JOIN o.account a WHERE a.id = o.account")
	Collection<Operation> findAllByUser();
}
