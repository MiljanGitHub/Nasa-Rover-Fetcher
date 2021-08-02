package com.fevo.assignment.cashing;

/**
 * @param P - object to persist
 */
public interface Persistable<P>{
	
	void persist(P p);
}
