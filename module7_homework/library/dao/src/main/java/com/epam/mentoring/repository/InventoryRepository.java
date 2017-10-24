package com.epam.mentoring.repository;

import com.epam.mentoring.domain.InventoryDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryDAO, Long> {

    @Override
    List<InventoryDAO> findAll();

    InventoryDAO findById(long id);

}
