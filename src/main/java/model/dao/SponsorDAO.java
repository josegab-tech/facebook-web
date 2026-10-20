package model.dao;

import java.util.List;
import model.ModelException;
import model.Sponsor;

public interface SponsorDAO{
	boolean save(Sponsor sponsor) throws ModelException;
	boolean update(Sponsor sponsor) throws ModelException;
	boolean delete(Sponsor sponsor) throws ModelException;
	List<Sponsor> listAll() throws ModelException;
	Sponsor findById(int id) throws ModelException;
	
}