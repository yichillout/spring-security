/**
 * 
 */
package com.jasper.exception;

/**
 * @author jasper
 *
 */
public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int id;

	public UserNotExistException(int id) {
		super("user not exist");
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
