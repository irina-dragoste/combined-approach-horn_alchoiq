package qa.combined_approach.rules;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

public class ExistentialHead {

	private final String filler;
	private final String role;

	public ExistentialHead(final String filler, final String role) {
		super();
		this.filler = filler;
		this.role = role;
	}

	public String getFiller() {
		return filler;
	}

	public String getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ExistentialHead other = (ExistentialHead) obj;
		if (filler == null) {
			if (other.filler != null) {
				return false;
			}
		} else if (!filler.equals(other.filler)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		return true;
	}

	public static ExistentialHead createExistentialHead(final OWLObjectMinCardinality minCardinality) {
		final String filler = Converter.getConceptName(minCardinality.getFiller());
		final String role = Converter.getRoleName(minCardinality.getProperty());
		return new ExistentialHead(filler, role);
	}

	public static ExistentialHead createExistentialHead(final OWLClassExpression filler, final OWLObjectPropertyExpression role) {
		return new ExistentialHead(Converter.getConceptName(filler), Converter.getRoleName(role));
	}

}
