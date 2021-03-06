package grails.plugin.gson

import com.google.gson.*
import org.codehaus.groovy.grails.commons.*

class GrailsDomainExclusionStrategy implements ExclusionStrategy {

	private final GrailsApplication grailsApplication

	GrailsDomainExclusionStrategy(GrailsApplication grailsApplication) {
		this.grailsApplication = grailsApplication
	}

	boolean shouldSkipField(FieldAttributes field) {
		def domainClass = getDomainClassForType(field.declaringClass)
		if (domainClass) {
			if (field.name == domainClass.identifier.name || field.name in domainClass.persistentProperties.name) {
				return false
			} else {
				return true
			}
		} else {
			false
		}
	}

	boolean shouldSkipClass(Class<?> aClass) {
		false
	}

	private GrailsDomainClass getDomainClassForType(Class type) {
		grailsApplication.getDomainClasses().find { it.clazz == type }
	}
}
