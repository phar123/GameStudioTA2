package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Occupation;

import java.util.List;

public interface OccupationService {
    public List<Occupation> getOccupations();
    public void addOccupation(Occupation  occupation);


}
