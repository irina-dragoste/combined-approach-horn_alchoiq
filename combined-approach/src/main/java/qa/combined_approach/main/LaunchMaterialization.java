package qa.combined_approach.main;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import qa.combined_approach.Materialization;
import qa.combined_approach.OntologyToProgram;
import qa.combined_approach.Program;
import qa.combined_approach.Util;
import qa.combined_approach.dataStore.DataStoreConfiguration;
import uk.ac.ox.cs.JRDFox.JRDFoxException;

public class LaunchMaterialization {

	public static void main(final String[] args) throws OWLOntologyCreationException, IOException, JRDFoxException {

		final String ontoPath = args[0];
		final String rdfoxInputLocation = args[1];
		final String aboxFolderLocation = args[2];
		final String exportFile = args[3];
		final Integer nbThreads = Integer.valueOf(args[4]);

		materialize(ontoPath, new File(rdfoxInputLocation), new File(aboxFolderLocation), new File(exportFile), nbThreads);
		// materialize(ontoPath);
	}

	public static void materialize(final String ontoPath, final File rdfoxInputLocation, final File aboxFolderLocation, final File exportFile,
			final int nbThreads) throws OWLOntologyCreationException, IOException, JRDFoxException {
		// load
		final long startTime = System.currentTimeMillis();
		final DataStoreConfiguration dataStoreConfiguration = new DataStoreConfiguration();
		dataStoreConfiguration.setRdfoxDataStoreThreads(nbThreads);

		System.out.println("Evaluating ontology: " + ontoPath + "; Time: " + LocalDate.now() + " " + LocalTime.now());
		final OWLOntology ontology = Util.loadOntologyWithImports(new File(ontoPath));
		final OntologyToProgram ontologyToProgram = new OntologyToProgram(ontology);
		final Program program = ontologyToProgram.getProgram();

		// materialize
		final Materialization materialization = new Materialization(rdfoxInputLocation, aboxFolderLocation, exportFile, dataStoreConfiguration, program);
		materialization.materialize();

		final long loadingDuration = materialization.getStartMaterialization() - startTime;
		final long materializationDuration = materialization.getMaterializationDuration();
		// I would not get the turtle facts from RDFox (class, property)
		final long generatedFactsCount = materialization.getGeneratedFactsCount();
		final int numberOfUnnamedIndiv = program.getUnnamedIndividualNames().keySet().size();
		final int numberOfActiveRoleConjunctions = program.getActiveRoleConjunctions().getRoleConjunctionNames().size();

		// final Map<HornAlchoiqAxiomType, Integer> countAxiomsByType = program.getCountAxiomsByType();
		// TODO print TBox axiom counts
		// final String line = ontologyFileName + CSV_COMMA_DELIMITER + numberOfUnnamedIndiv + CSV_COMMA_DELIMITER + loadingDuration + CSV_COMMA_DELIMITER
		// + materializationDuration + CSV_COMMA_DELIMITER + generatedFactsCount + CSV_COMMA_DELIMITER;
		// Util.writeToFile(new File(materializationResultsFile), appendMetrics(line, countAxiomsByType, uniqueHeads), true);

		System.out.println(" - #final active role conjunctions: " + numberOfActiveRoleConjunctions);
		System.out.println(" - #Introduced Unnamed Individuals: " + numberOfUnnamedIndiv);
		System.out.println(" - total loading   duration: " + loadingDuration + " ms");
		System.out.println(" - materialization duration: " + materializationDuration + " ms");

	}

	// public static String appendMetrics(final String initialString, final Map<RoleConjunctionHornAlchoiqAxiomType, Integer> countAxiomsByType,
	// final Set<ExistentialHead> uniqueExistentialHeads) throws IOException {
	// final StringBuilder metricsLine = new StringBuilder(initialString);
	// for (final RoleConjunctionHornAlchoiqAxiomType type : RoleConjunctionHornAlchoiqAxiomType.values()) {
	// final Integer countPerAxiom = countAxiomsByType.getOrDefault(type, 0);
	// metricsLine.append(countPerAxiom + CSV_COMMA_DELIMITER);
	// }
	// metricsLine.append(uniqueExistentialHeads.size());
	// metricsLine.append(CSV_NEW_LINE_SEPARATOR);
	//
	// return metricsLine.toString();
	// }

}
