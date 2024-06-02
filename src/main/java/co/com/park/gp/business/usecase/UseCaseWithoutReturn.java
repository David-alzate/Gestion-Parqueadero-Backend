package co.com.park.gp.business.usecase;

public interface UseCaseWithoutReturn<T> {
	
	void execute(T data);

}
