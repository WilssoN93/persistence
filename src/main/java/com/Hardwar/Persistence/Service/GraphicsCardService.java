package com.Hardwar.Persistence.Service;

import com.Hardwar.Persistence.Entitys.GraphicsCard;
import com.Hardwar.Persistence.Repository.GraphicsCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphicsCardService {

    @Autowired
    GraphicsCardRepository repository;

    public List<GraphicsCard> getAllGraphicsCardsByDomainName(String domainName) {
        return repository.findAllByDomainName(domainName);
    }

    public List<GraphicsCard> getAllByPriceAmount(String amount) {
        return repository.findAllByPriceIsLessThanEqual(Integer.parseInt(amount));
    }

    public List<GraphicsCard> getAllGraphicsCards() {
        return repository.findAll();
    }

    public List<GraphicsCard> saveAll(List<GraphicsCard> graphicsCardsList) {
        GraphicsCard graphicsCard = null;
        for (GraphicsCard gpu:graphicsCardsList) {
            if(gpu != null) {
                graphicsCard = repository.findByUrl(gpu.getUrl());
            }
            if (graphicsCard != null){
                gpu.setId(graphicsCard.getId());
            }
            repository.save(gpu);
        }
        return graphicsCardsList;
    }

    public void matchAll() {
        List<GraphicsCard> graphicsCardsList = repository.findAll();
        for (int i = 0; i < graphicsCardsList.size(); i++) {
            GraphicsCard targetGpu = graphicsCardsList.get(i);
            for (int j = 0; j < graphicsCardsList.size(); j++) {

                GraphicsCard graphicsCard = graphicsCardsList.get(j);
                if (targetGpu.getUrl().equals(graphicsCard.getUrl()) || targetGpu.getPrice() == 0) {
                    repository.delete(targetGpu);
                    continue;
                }
                if (targetGpu.getArticleNumber().equals(graphicsCard.getArticleNumber()) || targetGpu.getName().equals(graphicsCard.getName())) {
                    if (targetGpu.getCoreClock() == 0 && graphicsCard.getCoreClock() != 0) {
                        targetGpu.setCoreClock(graphicsCard.getCoreClock());
                        System.out.println(targetGpu.getName() + " CoreClock");
                    }
                    if (targetGpu.getBoostClock() == 0 && graphicsCard.getBoostClock() != 0) {
                        System.out.println(targetGpu.getName() + " BoostClock");
                        targetGpu.setBoostClock(graphicsCard.getBoostClock());
                    }
                    if (targetGpu.getCudaCores() == 0 && graphicsCard.getCudaCores() != 0) {
                        targetGpu.setCudaCores(graphicsCard.getCudaCores());
                        System.out.println(targetGpu.getName() + "cuda cores");
                    }
                    if (targetGpu.getConnection() == null && graphicsCard.getConnection() != null) {
                        targetGpu.setConnection(graphicsCard.getConnection());
                        System.out.println(targetGpu.getName() + " Connection");
                    }
                    if (targetGpu.getCapacity() == 0 && graphicsCard.getCapacity() != 0) {
                        targetGpu.setCudaCores(graphicsCard.getCapacity());
                        System.out.println(targetGpu.getName() + " Capacity");
                    }
                    targetGpu.setId(targetGpu.getId());
                    repository.save(targetGpu);
                }
                System.out.println("End of Loop");
            }
        }

    }
}
