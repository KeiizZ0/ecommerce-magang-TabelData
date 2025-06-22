package com.example.ecommerce.service;

import com.example.ecommerce.entity.Store;
import com.example.ecommerce.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public List<Store> getStoresByOwnerId(Long ownerId) {
        return storeRepository.findByOwnerId(ownerId);
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, Store storeDetails) {
        Store store = getStoreById(id);
        if (store != null) {
            store.setName(storeDetails.getName());
            store.setDescription(storeDetails.getDescription());
            store.setLocation(storeDetails.getLocation());
            store.setActive(storeDetails.getActive());
            store.setRating(storeDetails.getRating());
            return storeRepository.save(store);
        }
        return null;
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}