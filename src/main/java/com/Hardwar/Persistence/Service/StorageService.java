package com.Hardwar.Persistence.Service;

import com.Hardwar.Persistence.Entitys.Storage;
import com.Hardwar.Persistence.Repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    StorageRepository repo;

    public List<Storage> getAllStorage() {
        return repo.findAll();
    }

    public List<Storage> getAllByDomain(String domainName) {
        return repo.findAllByDomainName(domainName);
    }

    public List<Storage> saveAllStorage(List<Storage> storages) {
        List<Storage> allStorage = getAllStorage();

        for (int i = 0; i < storages.size(); i++) {
            Storage targetStorage = storages.get(i);
            for (Storage persistedStorage : allStorage) {
                if (targetStorage.getUrl() != null && persistedStorage.getUrl() != null) {
                    if (targetStorage.getUrl().equals(persistedStorage.getUrl())) {
                        targetStorage.setId(persistedStorage.getId());
                    }
                    if (targetStorage.getArticleNumber().equals(persistedStorage.getArticleNumber())) {
                        if (targetStorage.getReadSpeed() == null && persistedStorage.getReadSpeed() != null) {
                            targetStorage.setReadSpeed(persistedStorage.getReadSpeed());
                        }
                        if (targetStorage.getWriteSpeed() == null && persistedStorage.getWriteSpeed() != null) {
                            targetStorage.setWriteSpeed(persistedStorage.getWriteSpeed());
                        }
                        if (targetStorage.getSize() == null && persistedStorage.getSize() != null) {
                            targetStorage.setSize(persistedStorage.getSize());
                        }
                        if (targetStorage.getType() == null && persistedStorage.getType() != null) {
                            targetStorage.setType(persistedStorage.getType());
                        }
                    }
                }
            }
            repo.save(targetStorage);
        }
        return storages;
    }
}
