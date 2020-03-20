package com.Hardwar.Persistence.Service;

import com.Hardwar.Persistence.Entitys.RandomAccessMemory;
import com.Hardwar.Persistence.Repository.RandomAccessMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomAccessMemoryService {

    @Autowired
    RandomAccessMemoryRepository repository;

    public List<RandomAccessMemory> getAllRAM(){
        return repository.findAll();
    }

    public List<RandomAccessMemory> getAllByDomain(String domainName){
        return repository.findAllByDomainName(domainName);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<RandomAccessMemory> getAllByPrice(int amount){
        return repository.findAllByPriceIsLessThanEqual(amount);
    }

    public List<RandomAccessMemory> saveAllRAM(List<RandomAccessMemory> listOfRAM){
        List<RandomAccessMemory> allRAM = getAllRAM();

        for (int i = 0; i < listOfRAM.size(); i++) {
            RandomAccessMemory targetRAM = listOfRAM.get(i);
            for (RandomAccessMemory persistedRAM : allRAM) {
                if (targetRAM.getUrl() != null && persistedRAM.getUrl() != null) {
                    if (targetRAM.getUrl().equals(persistedRAM.getUrl())) {
                        targetRAM.setId(persistedRAM.getId());
                    }
                    if (targetRAM.getArticleNumber().equals(persistedRAM.getArticleNumber())) {
                        if (targetRAM.getCapacity() == 0 && persistedRAM.getCapacity() != 0) {
                            targetRAM.setCapacity(persistedRAM.getCapacity());
                        }
                        if (targetRAM.getDdr() == null && persistedRAM.getDdr() != null) {
                            targetRAM.setDdr(persistedRAM.getDdr());
                        }
                        if (targetRAM.getSpeeds() == 0 && persistedRAM.getSpeeds() != 0) {
                            targetRAM.setSpeeds(persistedRAM.getSpeeds());
                        }

                    }
                }
            }
            repository.save(targetRAM);
        }
        return listOfRAM;
    }
}
