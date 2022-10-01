package com.project.sena.amorestore.repositories;
import com.project.sena.amorestore.models.Product;
import com.project.sena.amorestore.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

}

