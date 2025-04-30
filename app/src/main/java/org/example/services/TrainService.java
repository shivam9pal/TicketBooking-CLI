package org.example.services;

import org.example.entities.Train;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrainService {

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train ->validTrain(train,source,destination)).collect(Collectors.toList());
    }

    private boolean validTarin(Train train,String source , String destination){
        List<String> stationOrder=train.getStations();

        int sourceIndex= stationOrder.indexOf(source.toLowerCase());
        int destinationIndex= stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex<destinationIndex;
       }
    }

}

