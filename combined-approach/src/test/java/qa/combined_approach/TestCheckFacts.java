package qa.combined_approach;

import java.io.File;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class TestCheckFacts {

	@Test
	public void testCheckFactsPaperExample() throws OWLOntologyCreationException {
		final File koncludeFactsFile = new File("D:\\phd\\env\\combined-approach\\KR\\compareResults\\first_example_paper\\norm-paper_example_konclude.owl");
		final OWLOntology ontology = Util
				.loadOntologyWithImports(new File("D:\\phd\\env\\combined-approach\\KR\\compareResults\\first_example_paper\\norm-paper_example.owl"));
		final CheckFacts cf = new CheckFacts(ontology, koncludeFactsFile);
		cf.countIndivInEveryClass();
	}

}
