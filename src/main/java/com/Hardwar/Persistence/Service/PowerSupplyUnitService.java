package com.Hardwar.Persistence.Service;
import com.Hardwar.Persistence.Entitys.PowerSupplyUnit;
import com.Hardwar.Persistence.Repository.PowerSupplyUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerSupplyUnitService {

    @Autowired
    PowerSupplyUnitRepository repo;

    public List<PowerSupplyUnit> getAllPSUs(){
        return repo.findAll();
    }
    public List<PowerSupplyUnit> getAllByDomainName(String domainName) {
        return repo.findAllByDomainName(domainName);
    }
    public List<PowerSupplyUnit> getAllByPrice(int amount){
        return repo.findAllByPriceIsLessThanEqual(amount);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }

    public List<PowerSupplyUnit> saveAll(List<PowerSupplyUnit> powerSupplyUnits) {
        List<PowerSupplyUnit> allPSUs = getAllPSUs();

        for (int i = 0; i < powerSupplyUnits.size(); i++) {
            PowerSupplyUnit targetPSU = powerSupplyUnits.get(i);
            for (PowerSupplyUnit persistedPSU : allPSUs) {
                if (targetPSU.getUrl() != null && persistedPSU.getUrl() != null) {
                    if (targetPSU.getUrl().equals(persistedPSU.getUrl())) {
                        targetPSU.setId(persistedPSU.getId());
                    }
                    if (targetPSU.getArticleNumber().equals(persistedPSU.getArticleNumber())) {
                        if (targetPSU.getFormFactor() == null && persistedPSU.getFormFactor() != null) {
                            targetPSU.setFormFactor(persistedPSU.getFormFactor());
                        }
                        if (persistedPSU.isModular()) {
                            targetPSU.setModular(true);
                        }

                    }
                }
            }
            repo.save(targetPSU);
        }
        return powerSupplyUnits;
    }
}
