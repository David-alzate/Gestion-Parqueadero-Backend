package co.com.park.gp.business.assembler.entity;

import java.util.List;

import co.com.park.gp.business.assembler.Assembler;

public interface AssemblerEntity<D, K> extends Assembler<D, K> {

	K toEntity(D domain);

	List<K> toEntityCollection(List<D> domainCollection);

}	