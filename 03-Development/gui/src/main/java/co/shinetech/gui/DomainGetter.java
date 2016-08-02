/**
 * DomainGetter.java
 */
package co.shinetech.gui;

/**
 * @author Rodrigo
 * @since 2016-08-02
 */
public interface DomainGetter<T> {
	void setDomainModel(T domainData);
	T getDomainModel();
}