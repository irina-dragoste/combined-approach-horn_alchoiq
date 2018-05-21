package qa.combined_approach.dataStore;

import uk.ac.ox.cs.JRDFox.store.DataStore;

public class DataStoreConfiguration {
	private int rdfoxDataStoreThreads = 1;
	private DataStore.StoreType rdfoxDataStoreType = DataStore.StoreType.ParallelComplexWW;

	/**
	 * Number of threads used by RDFox data store for reasoning. Default value is 1.
	 * 
	 * @return
	 */
	public int getRdfoxDataStoreThreads() {
		return rdfoxDataStoreThreads;
	}

	/**
	 * Sets the number of threads used by RDFox data store for reasoning. Default value is 1.
	 * 
	 * @param rdfoxDataStoreThreads
	 */
	public void setRdfoxDataStoreThreads(int rdfoxDataStoreThreads) {
		this.rdfoxDataStoreThreads = rdfoxDataStoreThreads;
	}

	// TODO copy from rdfsore javadoc
	/**
	 * Gets the RDFox data store type. Default value is ParallelSimpleNN
	 * 
	 * @return
	 */
	public DataStore.StoreType getRdfoxDataStoreType() {
		return rdfoxDataStoreType;
	}

	/**
	 * Sets the RDFox data store type. Default value is ParallelSimpleNN
	 * 
	 * @param rdfoxDataStoreType
	 */
	public void setRdfoxDataStoreType(DataStore.StoreType rdfoxDataStoreType) {
		this.rdfoxDataStoreType = rdfoxDataStoreType;
	}

}
