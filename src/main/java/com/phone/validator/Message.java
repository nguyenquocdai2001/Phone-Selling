package com.phone.validator;

public class Message {
	private String type;
	private String content;
	private String isValid;
	
	public Message(String type, String content, String isValid) {
		super();
		this.type = type;
		this.content = content;
		this.isValid = isValid;
	}
	
	public Message(String type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
}
