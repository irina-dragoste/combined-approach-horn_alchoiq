package qa.combined_approach;

import java.io.File;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLOntology;

import qa.combined_approach.dataStore.DataStoreInterface;
import qa.combined_approach.dataStore.RDFoxDataStoreWrapper;
import qa.combined_approach.rules.Atom;
import qa.combined_approach.rules.Converter;
import qa.combined_approach.rules.Query;

public class CheckFacts {

	public CheckFacts(final OWLOntology ontology, final File koncludeFactsFile) {
		super();
		this.ontology = ontology;
		this.koncludeFactsFile = koncludeFactsFile;
	}

	private final OWLOntology ontology;
	// private DataStore dataStore;
	private final File koncludeFactsFile;

	public void countIndivInEveryClass() {
		final DataStoreInterface dataStoreKonclude = new RDFoxDataStoreWrapper();
		// TODO you cannot do this, because it is an ontology
		dataStoreKonclude.importFiles(koncludeFactsFile);
		// dataStoreKonclude.reason();

		// check all class assertions we have with the named individuals
		ontology.classesInSignature().forEach(classInSignature -> {
			/* ?x such that class(?x),N(?x) . */
			final Query query = new Query(Util.VAR_X, Converter.ConceptToAtom(classInSignature, Util.VAR_X), new Atom(Util.NAMED_PRED, Util.VAR_X));

			final Set<String> answersKonclude = dataStoreKonclude.answerUnaryQuery(query);
			System.out.println(classInSignature + " konclude: " + answersKonclude.size());
		});
	}

}
