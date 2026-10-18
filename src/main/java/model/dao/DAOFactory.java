package model.dao;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
	
	private static Map<Class, Object> listDAOsInterfaces = new HashMap<Class, Object>();
	
	
    private DAOFactory() {
    	// Para impedir instanciação
    }
	
	// Para o DAOFactory funcionar para suas classes de domínio, adicione na 
	// lista suas interfaces e classes DAO na listDAOsInterfaces
	static {
		listDAOsInterfaces.put(PostDAO.class, new MySQLPostDAO());
		listDAOsInterfaces.put(UserDAO.class, new MySQLUserDAO());
	}
	
	@SuppressWarnings("unchecked")
	public static <DAOInterface> DAOInterface createDAO(Class<?> entity){
		return (DAOInterface) listDAOsInterfaces.get(entity);
	} 
}
