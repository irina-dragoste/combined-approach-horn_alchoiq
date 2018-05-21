package qa.combined_approach;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import qa.combined_approach.main.LaunchMaterialization;
import uk.ac.ox.cs.JRDFox.JRDFoxException;

public class TestMaterialize {

	// @Test
	// @Ignore
	// public void testMaterializePaperExample() throws OWLOntologyCreationException, IOException, JRDFoxException {
	// final String basePath = "src/test/data/paper_example/";
	// LaunchMaterialization.materialize(basePath + "norm-paper_example.owl", new File(basePath + "rdfox"), null, 4);
	// System.out.println("AA");
	// }
	//
	// @Test
	// @Ignore
	// public void testMaterializeReactome080() throws OWLOntologyCreationException, IOException, JRDFoxException {
	// final String basePath = "src/test/data/mat/Reactome/";
	// LaunchMaterialization.materialize(basePath + "Reactome_mod.owl", new File(basePath + "rdfox"),
	// new File("d:\\phd\\env\\repos\\largeOntos\\original-large-ontos-ABoxes\\Reactome\\AcyReasoning\\Reactome080\\"), 4);
	//
	// }
	//
	// @Test
	// @Ignore
	// public void testMaterializeUniprot015() throws OWLOntologyCreationException, IOException, JRDFoxException {
	// final String basePath = "src/test/data/mat/Uniprot/";
	// LaunchMaterialization.materialize(basePath + "Uniprot.owl", new File(basePath + "rdfox"),
	// new File("d:\\phd\\env\\repos\\largeOntos\\original-large-ontos-ABoxes\\Uniprot\\AcyReasoning\\Uniprot015\\"), 4);
	//
	// }
	//
	// // d:\phd\env\repos\largeOntos\original-large-ontos-ABoxes\LUBM\AcyReasoning\LUBM075\
	// @Test
	// @Ignore
	// public void testMaterializeLUBM075() throws OWLOntologyCreationException, IOException, JRDFoxException {
	// final String basePath = "src/test/data/mat/LUBM/";
	// LaunchMaterialization.materialize(basePath + "LUBM.owl", new File(basePath + "rdfox"),
	// new File("d:\\phd\\env\\repos\\largeOntos\\original-large-ontos-ABoxes\\LUBM\\AcyReasoning\\LUBM075\\"), 4);
	// }
	//
	// @Ignore
	// @Test
	// public void testMaterializeUOBM010() throws OWLOntologyCreationException, IOException, JRDFoxException {
	// final String basePath = "src/test/data/mat/UOBM/";
	// LaunchMaterialization.materialize(basePath + "normalized_minCard_manually_UOBM.owl", new File(basePath + "rdfox"), null, 4);
	// }

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testMaterializeLUBM025() throws OWLOntologyCreationException, IOException, JRDFoxException {
		final String tbox = "d:\\phd\\env\\combined-approach\\KR\\ontos\\3_TBoxes_Normalized_minCard_Trimed_HornAlchoiq\\normalized_minCard_manually_trimmed_LUBM.owl";

		final String rdfox = "d:\\phd\\env\\combined-approach\\KR\\compareResults\\LUBM025\\rdfoxinput\\";
		final String export = "d:\\phd\\env\\combined-approach\\KR\\compareResults\\LUBM025\\LUBM025_rdfox.nt";

		final String abox = "d:\\phd\\env\\combined-approach\\KR\\ontos\\ABoxes\\LUBM\\AcyReasoning\\LUBM025\\";
		LaunchMaterialization.materialize(tbox, new File(rdfox), new File(abox), new File(export), 4);
	}

}
