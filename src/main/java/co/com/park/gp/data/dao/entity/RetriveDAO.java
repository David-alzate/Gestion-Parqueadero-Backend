package co.com.park.gp.data.dao.entity;

import java.util.List;

public interface RetriveDAO<E> {

	List<E> consultar(E data);

}
