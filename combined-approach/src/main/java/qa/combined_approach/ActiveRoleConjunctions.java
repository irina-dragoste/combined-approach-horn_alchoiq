package qa.combined_approach;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import com.google.common.collect.Sets;

import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

public class ActiveRoleConjunctions {

	private final Map<Set<OWLObjectPropertyExpression>, OWLObjectPropertyExpression> roleConjunctions = new HashMap<>();
	private final Map<OWLObjectPropertyExpression, RoleConjunction> roleConjunctionsByName = new HashMap<>();

	public void computeActiveRoleConjunctions(final SuperProperties superProperties) {

		/* put keys, and themselves */
		addSingleRoles(superProperties.getSuperProperties().keySet());

		superProperties.getSuperProperties().forEach((property, superRolesSet) -> {
			/* if the role conjunction already exists in the active sets, do nothing */
			if (!roleConjunctions.containsKey(superRolesSet)) {

				final OWLObjectPropertyExpression inverseProperty = property.getInverseProperty().getSimplified();
				final Set<OWLObjectPropertyExpression> inverseSuperRoleSet = superProperties
						.getSuperProperties(inverseProperty);

				final OWLObjectPropertyExpression roleConjunctionName;

				/*
				 * if the inverse role conjunction already exists in the active sets, invert its
				 * name
				 */
				if (roleConjunctions.containsKey(inverseSuperRoleSet)) {
					final OWLObjectPropertyExpression inverseRoleConjunctionName = roleConjunctions
							.get(inverseSuperRoleSet);
					roleConjunctionName = inverseRoleConjunctionName.getInverseProperty().getSimplified();

				} else {
					/* else, add new role set */
					roleConjunctionName = generateRoleConjunctionName();
				}

				final RoleConjunction roleConjunction = new RoleConjunction(roleConjunctionName, superRolesSet);
				roleConjunctions.put(roleConjunction.getRoleSet(), roleConjunction.getRoleName());
				roleConjunctionsByName.put(roleConjunction.getRoleName(), roleConjunction);
			}

		});
	}

	private void addSingleRoles(final Set<OWLObjectPropertyExpression> singleRoles) {
		singleRoles.forEach(role -> {
			final RoleConjunction singleRoleConjunction = new RoleConjunction(role, Sets.newHashSet(role));
			roleConjunctionsByName.put(role, singleRoleConjunction);
			roleConjunctions.put(singleRoleConjunction.getRoleSet(), role);
		});
	}

	public RoleConjunction addRoleConjunction(final Set<OWLObjectPropertyExpression> roleSet) {
		/*
		 * if the role conjunction already exists in the active sets, do nothing, return
		 * it .
		 */
		if (roleConjunctions.containsKey(roleSet)) {
			throw new CombinedApproachException("Expected a new role conjunction: " + roleSet);
			// return roleConjunctionsByName.get(roleConjunctions.get(roleSet));
		} else {
			/* else add a new RoleConjunction to the active set . */
			final OWLObjectPropertyExpression roleConjunctionName;
			final Set<OWLObjectPropertyExpression> inverseRoleSet = invert(roleSet);

			/*
			 * if the inverse role conjunction already exists in the active sets, invert its
			 * name
			 */
			if (roleConjunctions.containsKey(inverseRoleSet)) {
				final OWLObjectPropertyExpression inverseRoleConjunction = roleConjunctions.get(inverseRoleSet);
				roleConjunctionName = inverseRoleConjunction.getInverseProperty().getSimplified();

			} else {
				/* else, add new role set */
				roleConjunctionName = generateRoleConjunctionName();
			}

			final RoleConjunction roleConjunction = new RoleConjunction(roleConjunctionName, roleSet);
			roleConjunctions.put(roleConjunction.getRoleSet(), roleConjunction.getRoleName());
			roleConjunctionsByName.put(roleConjunction.getRoleName(), roleConjunction);

			return roleConjunction;
		}
	}

	public static Set<OWLObjectPropertyExpression> invert(final Set<OWLObjectPropertyExpression> roleSet) {
		final Set<OWLObjectPropertyExpression> inverseRoleSet = new HashSet<>();
		roleSet.forEach(role -> inverseRoleSet.add(role.getInverseProperty().getSimplified()));
		return inverseRoleSet;
	}

	public Set<OWLObjectPropertyExpression> getRoleConjunctionsContainingRole(final OWLObjectPropertyExpression role) {
		final Set<OWLObjectPropertyExpression> roleConjunctionsContainingRole = new HashSet<>();
		roleConjunctions.keySet().forEach(roleSet -> {
			if (roleSet.contains(role)) {
				final OWLObjectPropertyExpression roleConjunction = roleConjunctions.get(roleSet);
				roleConjunctionsContainingRole.add(roleConjunction);
			}
		});
		return roleConjunctionsContainingRole;
	}

	private OWLObjectPropertyImpl generateRoleConjunctionName() {
		return new OWLObjectPropertyImpl(IRI.create("RS_" + roleConjunctions.size()));
	}

	public OWLObjectPropertyExpression getRoleConjunctionName(final Set<OWLObjectPropertyExpression> roleSet) {
		return roleConjunctions.get(roleSet);
	}

	public Set<OWLObjectPropertyExpression> getRoleConjunctionByName(
			final OWLObjectPropertyExpression roleConjunctionName) {
		return this.roleConjunctionsByName.get(roleConjunctionName).getRoleSet();
	}

	public Map<OWLObjectPropertyExpression, RoleConjunction> getRoleConjunctionsByName() {
		return this.roleConjunctionsByName;
	}

	public Set<OWLObjectPropertyExpression> getRoleConjunctionNames() {
		return roleConjunctionsByName.keySet();
	}

}
