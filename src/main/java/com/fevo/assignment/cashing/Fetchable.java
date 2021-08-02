package com.fevo.assignment.cashing;

import java.util.Optional;

/**
 * @param P - object to fetch
 * @param K - key for which to fetch P
 */
public interface Fetchable<P, K>{
	
	Optional<P> fetch(K k);
}
