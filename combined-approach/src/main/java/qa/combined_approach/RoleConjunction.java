package qa.combined_approach;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

public class RoleConjunction {

	private final OWLObjectPropertyExpression roleName;
	private final Set<OWLObjectPropertyExpression> roleSet = new HashSet<>();

	public RoleConjunction(final OWLObjectPropertyExpression roleName, final Set<OWLObjectPropertyExpression> roleSet) {
		super();
		this.roleName = roleName;
		this.roleSet.addAll(roleSet);
	}

	public boolean isSingleRole() {
		return roleSet.size() == 1;
	}

	// TODO get Inverse ....

	public OWLObjectPropertyExpression getRoleName() {
		return this.roleName;
	}

	public Set<OWLObjectPropertyExpression> getRoleSet() {
		return this.roleSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.roleSet.hashCode();
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
		if (!(obj instanceof RoleConjunction)) {
			return false;
		}
		final RoleConjunction other = (RoleConjunction) obj;
		return this.roleSet.equals(other.roleSet);
	}

}
