package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByDomainNameAndParsedFalse(String domainName);
    List<Product> findAllByDomainName(String domainName);
    List<Product> findAllByTypeOfHardWareIsNullAndDomainName(String domainName);
}
