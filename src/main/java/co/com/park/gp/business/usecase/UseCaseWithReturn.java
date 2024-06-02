package co.com.park.gp.business.usecase;

public interface UseCaseWithReturn<T,R> {
	
	R execute (T data);

}
