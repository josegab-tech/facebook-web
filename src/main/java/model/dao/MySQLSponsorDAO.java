package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.Sponsor;
import model.User;

public class MySQLSponsorDAO implements SponsorDAO {

	@Override
	public boolean save(Sponsor sponsor) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO sponsors VALUES "
				+ " (DEFAULT, ?, ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, sponsor.getName());
		db.setString(2, String.valueOf(sponsor.getInvestment()));
		db.setInt(3, sponsor.getUser().getId());
		  
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Sponsor sponsor) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE sponsors "
						 + " SET name = ? , "
						 + " investment = ? , "
						 + " user_id = ? "
						 + " WHERE id = ?;";
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, sponsor.getName());
		db.setString(2, String.valueOf(sponsor.getInvestment()));
		db.setInt(3, sponsor.getUser().getId());
		db.setInt(4, sponsor.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Sponsor sponsor) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM sponsors "
		                 + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, sponsor.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Sponsor> listAll() throws ModelException {
	
		DBHandler db = new DBHandler();
		
		List<Sponsor> sponsors = new ArrayList<Sponsor>();
			
		// Declara um instrução SQL
		String sqlQuery = " SELECT u.id AS user_id, s.* "
				        + " FROM users u "
				        + " INNER JOIN sponsors s "
				        + " ON u.id = s.user_id ";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Sponsor s = createSponsor(db);
			
			sponsors.add(s);
		}
		
		return sponsors;
	}

	@Override
	public Sponsor findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
				
		String sql = "SELECT * FROM sponsors WHERE id = ?;";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Sponsor s = null;
		while (db.next()) {
			s = createSponsor(db);
			break;
		}
		
		return s;
	}
	
	private Sponsor createSponsor(DBHandler db) throws ModelException {
		Sponsor s = new Sponsor(db.getInt("id"));
		s.setName(db.getString("name"));
		
		// Lê como String do banco e converte para double
		String investmentStr = db.getString("investment");
		if (investmentStr != null && !investmentStr.isBlank()) {
			s.setInvestment(Double.parseDouble(investmentStr));
		}
		
		UserDAO userDAO = DAOFactory.createDAO(UserDAO.class); 
		
		User user = userDAO.findById(db.getInt("user_id"));
		s.setUser(user);
		
		return s;
	}
}