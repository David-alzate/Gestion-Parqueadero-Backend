package co.com.park.gp.business.facade;

public interface FacadeWhitReturn<T , K> {
	
	K execute (T dto);

}