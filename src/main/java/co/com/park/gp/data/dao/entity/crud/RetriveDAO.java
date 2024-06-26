package co.com.park.gp.data.dao.entity.crud;

import java.util.List;

public interface RetriveDAO<E> {

	List<E> consultar(E data);

}
