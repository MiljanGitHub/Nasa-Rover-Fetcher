package com.fevo.assignment.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import com.fevo.assignment.service.RoverService;

public enum RoverType {
	
	/*
	 * Better option with method references and functional style rather then using switch or if-else.
	 * Also, the developer who is about to work on this code will get compile-error if the new enum is added without method reference.
	 * I hope we agree that doing: NEW_ROVER(null) is not very intuitive.  
	 */
	
	CURIOSITY(RoverService::fetchCuriosityData),
	OPPORTUNITY(RoverService::fetchOpportunityData),
	SPIRIT(RoverService::fetchSpiritData);
	
	
	
	private static final Map<String, RoverType> BY_LABEL = new HashMap<String, RoverType>();
    
	public final BiFunction<RoverService, Map<String, String[]>, Response> roverDataFetcher;
	
	private RoverType(BiFunction<RoverService, Map<String, String[]>, Response> roverDataFetcher) {
		this.roverDataFetcher = roverDataFetcher;
	}
	
    static {
    	Stream.of(RoverType.values()).forEach(roverType -> BY_LABEL.put(roverType.toString().toLowerCase(), roverType));
    }

    public static RoverType valueOfLabel(String label) {
        return BY_LABEL.get(label.toLowerCase());
    }
}
