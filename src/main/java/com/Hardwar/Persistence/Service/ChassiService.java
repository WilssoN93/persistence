package com.Hardwar.Persistence.Service;

import com.Hardwar.Persistence.Entitys.Chassi;
import com.Hardwar.Persistence.Repository.ChassiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChassiService {

    @Autowired
    ChassiRepository repo;

    public List<Chassi> getAllChassis(){
        return repo.findAll();
    }
    public List<Chassi> getAllByDomain(String domainName){
        return repo.findAllByDomainName(domainName);
    }

    public List<Chassi> getAllByPrice(int amount){
        return repo.findAllByPriceIsLessThanEqual(amount);
    }
    public List<Chassi> saveAll(List<Chassi> chassis) {
        List<Chassi> allChassis = getAllChassis();

        for (int i = 0; i < chassis.size(); i++) {
            Chassi targetChassi = chassis.get(i);
            for (Chassi persistedChassi : allChassis) {
                if (targetChassi.getUrl() != null && persistedChassi.getUrl() != null) {
                    if (targetChassi.getUrl().equals(persistedChassi.getUrl())) {
                        targetChassi.setId(persistedChassi.getId());
                    }
                    if (targetChassi.getArticleNumber().equals(persistedChassi.getArticleNumber())) {
                        if (targetChassi.getFormFactor() == null && persistedChassi.getFormFactor() != null) {
                            targetChassi.setFormFactor(persistedChassi.getFormFactor());
                        }
                        if (targetChassi.getMaxGpuWidth() == null && persistedChassi.getMaxGpuWidth() != null) {
                            targetChassi.setMaxGpuWidth(persistedChassi.getMaxGpuWidth());
                        }
                        if (targetChassi.getMaxHeightCpuCooler() == null && persistedChassi.getMaxHeightCpuCooler() != null) {
                            targetChassi.setMaxHeightCpuCooler(persistedChassi.getMaxHeightCpuCooler());
                        }

                    }
                }
            }
            repo.save(targetChassi);
        }
        return chassis;
    }
}
