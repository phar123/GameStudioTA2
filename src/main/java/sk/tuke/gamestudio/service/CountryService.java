package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getCountries();
    public void addCountry(Country country);

}
