package co.shinetech.dto;

import java.io.Serializable;

/**
 * 
 * @author Robin
 *
 */
public interface Domain extends Serializable {
	/**
	 * Returns this object id.
	 * @return id
	 */
	public long getPk();
}
