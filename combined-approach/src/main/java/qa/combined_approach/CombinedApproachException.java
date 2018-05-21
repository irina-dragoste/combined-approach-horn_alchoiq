package qa.combined_approach;

import uk.ac.ox.cs.JRDFox.JRDFoxException;

public class CombinedApproachException extends RuntimeException {

	private static final long serialVersionUID = 3811357341357955334L;

	public CombinedApproachException(String string, JRDFoxException e) {
		super(string, e);
	}

	public CombinedApproachException(String string) {
		super(string);
	}

}
