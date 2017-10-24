package com.epam.mentoring.service;

import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.repository.InventoryRepository;
import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public InventoryDAO createInventory(InventoryDAO inventory) {
        return inventoryRepository.save(inventory);
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

    public List<BookDAO> getBooksFromInventory() {
        List<InventoryDAO> inventories = getInventories();

        return inventories.stream().map(InventoryDAO::getBook).collect(Collectors.toList());
    }

    public InventoryDAO getInventoryByBookId(long bookId) {
        return inventoryRepository.findByBook_Id(bookId);
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

        return storedInventory;
    }

}
