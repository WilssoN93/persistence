package com.Hardwar.Persistence.Service;

import com.Hardwar.Persistence.Entitys.MotherBoard;
import com.Hardwar.Persistence.Repository.MotherBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotherBoardService {

    @Autowired
    MotherBoardRepository repository;

    public List<MotherBoard> getAllMotherBoards(){
        return repository.findAll();
    }
    public List<MotherBoard> getAllByDomain(String domainName){
        return repository.findAllByDomainName(domainName);
    }

    public List<MotherBoard> getAllMotherBoardsByPrice(int amount){
        return repository.findAllByPriceIsLessThanEqual(amount);
    }

    public List<MotherBoard> getAllMotherBoardsByPriceAndSocket(int amount,String socket){
        return repository.findAllByPriceIsLessThanEqualAndAndSocket(amount,socket);
    }

    public List<MotherBoard> saveAllMotherBoards(List<MotherBoard> motherBoards){
        List<MotherBoard> allMotherBoards = getAllMotherBoards();

        for (int i = 0; i < motherBoards.size(); i++) {
            MotherBoard targetMotherBoard = motherBoards.get(i);
            for (MotherBoard persistedMotherBoard : allMotherBoards) {
                if (targetMotherBoard.getUrl() != null && persistedMotherBoard.getUrl() != null) {
                    if (targetMotherBoard.getUrl().equals(persistedMotherBoard.getUrl())) {
                        targetMotherBoard.setId(persistedMotherBoard.getId());
                    }
                    if (targetMotherBoard.getArticleNumber().equals(persistedMotherBoard.getArticleNumber())) {
                        if (targetMotherBoard.getFormFactor() == null && persistedMotherBoard.getFormFactor() != null) {
                            targetMotherBoard.setFormFactor(persistedMotherBoard.getFormFactor());
                        }
                        if (targetMotherBoard.getSocket() == null && persistedMotherBoard.getSocket() != null) {
                            targetMotherBoard.setSocket(persistedMotherBoard.getSocket());
                        }
                        if (targetMotherBoard.getSpeeds() == 0 && persistedMotherBoard.getSpeeds() != 0) {
                            targetMotherBoard.setSpeeds(persistedMotherBoard.getSpeeds());
                        }
                        if (targetMotherBoard.getSupportedRam() == null && persistedMotherBoard.getSupportedRam() != null) {
                            targetMotherBoard.setSupportedRam(persistedMotherBoard.getSupportedRam());
                        }

                    }
                }
            }
            repository.save(targetMotherBoard);
        }
        return motherBoards;
    }
}
