package com.epam.mentoring.service;

import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.repository.InventoryRepository;
import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public InventoryDAO createInventory(InventoryDAO inventory) {
        return inventoryRepository.save(inventory);
    }

    public InventoryDAO getInventoryById(long id) {
        InventoryDAO inventory = inventoryRepository.findById(id);

        if (inventory == null) {
            throw new NoSuchEntryException("Inventory doesn't exist with the requested id=" + id);
        }

        return inventory;
    }

    public List<InventoryDAO> getInventories() {
        return inventoryRepository.findAll();
    }

    @Transactional
    public InventoryDAO updateInventory(InventoryDAO inventory) {
        InventoryDAO storedInventory = getInventoryById(inventory.getId());

        if (Objects.nonNull(inventory.getBook())) {
            storedInventory.setBook(inventory.getBook());
        }

        if (inventory.getAvailableCopiesNumber() >= 0) {
            storedInventory.setAvailableCopiesNumber(inventory.getAvailableCopiesNumber());
        }

        if (!CollectionUtils.isEmpty(inventory.getRequestedForBorrow())) {
            storedInventory.setRequestedForBorrow(inventory.getRequestedForBorrow());
        }

        if (Objects.nonNull(inventory.getReturnDate())) {
            storedInventory.setReturnDate(inventory.getReturnDate());
        }

        if (Objects.nonNull(inventory.getUsersBorrowed())) {
            storedInventory.setUsersBorrowed(inventory.getUsersBorrowed());
        }

        return storedInventory;
    }

    @Transactional
    public void deleteInventory(long id) {
        try {
            inventoryRepository.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntryException("Inventory doesn't exist with the requested id=" + id);
        }
    }

}
