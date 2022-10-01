package com.project.sena.amorestore.repositories;
import com.project.sena.amorestore.models.Sale;
import com.project.sena.amorestore.models.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}

