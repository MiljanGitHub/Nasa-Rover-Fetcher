package com.fevo.assignment.cashing;

public class Cash {
	
	
	private Persistable persistable;
	
	private Fetchable fetchable;

	public Cash(Persistable persistable, Fetchable fetchable) {
		super();
		this.persistable = persistable;
		this.fetchable = fetchable;
	}

	public Persistable getPersistable() {
		return persistable;
	}

	public void setPersistable(Persistable persistable) {
		this.persistable = persistable;
	}

	public Fetchable getFetchable() {
		return fetchable;
	}

	public void setFetchable(Fetchable fetchable) {
		this.fetchable = fetchable;
	}

}
