package qa.combined_approach;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

public class SuperProperties {

	private final Map<OWLObjectPropertyExpression, Set<OWLObjectPropertyExpression>> superProperties = new HashMap<>();

	public Map<OWLObjectPropertyExpression, Set<OWLObjectPropertyExpression>> getSuperProperties() {
		return this.superProperties;
	}

	/**
	 * Make sure property is simplified inside
	 *
	 * @param property
	 * @return
	 */
	public Set<OWLObjectPropertyExpression> getSuperProperties(final OWLObjectPropertyExpression property) {
		return superProperties.get(property.getSimplified());
	}

	/**
	 * Make sure properties are simplified
	 *
	 * @param objectPropertyExpression
	 */
	public void addPropertyAndItsInverse(final OWLObjectPropertyExpression objectPropertyExpression) {
		superProperties.putIfAbsent(objectPropertyExpression.getSimplified(), new HashSet<>());
		superProperties.putIfAbsent(objectPropertyExpression.getInverseProperty().getSimplified(), new HashSet<>());
	}

	/**
	 * Make sure properties are simplified
	 *
	 * @param subProperty
	 * @param superProperty
	 */
	public void addPropertySubsumptionAndItsInverse(final OWLObjectPropertyExpression subProperty, final OWLObjectPropertyExpression superProperty) {
		add(subProperty.getSimplified(), superProperty.getSimplified());
		add(subProperty.getInverseProperty().getSimplified(), superProperty.getInverseProperty().getSimplified());
	}

	/**
	 * Computes the super propertes of all added properties. The superProperty relation is reflexive and transitive.
	 */
	public void computeSuperProperties() {
		computeTransitiveClosureOfPropertySubsumption();
		addReflexivePropertySubsumption();
	}

	private void computeTransitiveClosureOfPropertySubsumption() {
		boolean changed = true;
		while (changed) {
			boolean changedThisRound = false;
			for (final OWLObjectPropertyExpression key : superProperties.keySet()) {
				final Set<OWLObjectPropertyExpression> superPropertiesToAdd = new HashSet<>();
				for (final OWLObjectPropertyExpression superPropOfKey : superProperties.get(key)) {
					for (final OWLObjectPropertyExpression superPropOfSuperPropOfKey : superProperties.get(superPropOfKey)) {
						superPropertiesToAdd.add(superPropOfSuperPropOfKey);
					}
				}
				changedThisRound = changedThisRound || superProperties.get(key).addAll(superPropertiesToAdd);
			}
			changed = changed & changedThisRound;
		}
	}

	private void addReflexivePropertySubsumption() {
		for (final OWLObjectPropertyExpression key : superProperties.keySet()) {
			final Set<OWLObjectPropertyExpression> superPropsOfKey = superProperties.get(key);
			superPropsOfKey.add(key);
		}
	}

	private void add(final OWLObjectPropertyExpression subProperty, final OWLObjectPropertyExpression superProperty) {
		superProperties.get(subProperty).add(superProperty);
	}

}
