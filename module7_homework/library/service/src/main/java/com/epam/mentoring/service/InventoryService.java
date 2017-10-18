package com.epam.mentoring.service;

import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public InventoryDAO createInventory(InventoryDAO inventory) {
        return inventoryRepository.save(inventory);
    }

    public InventoryDAO getInventoryById(long id) {
        return inventoryRepository.findById(id);
    }

    public List<InventoryDAO> getInventories() {
        return inventoryRepository.findAll();
    }

}
