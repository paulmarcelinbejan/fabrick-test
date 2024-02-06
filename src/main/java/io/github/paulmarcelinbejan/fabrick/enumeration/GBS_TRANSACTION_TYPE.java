package io.github.paulmarcelinbejan.fabrick.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GBS_TRANSACTION_TYPE {

	GBS_ACCOUNT_TRANSACTION_TYPE_0010, 
	GBS_ACCOUNT_TRANSACTION_TYPE_0034, 
	GBS_ACCOUNT_TRANSACTION_TYPE_0050;

	private String enumeration;
	private String value;

	private GBS_TRANSACTION_TYPE() {
		enumeration = GBS_TRANSACTION_TYPE.class.getSimpleName();
		value = this.name();
	}
	
	@JsonCreator
    public static GBS_TRANSACTION_TYPE fronJsonNode(JsonNode node) {
        return GBS_TRANSACTION_TYPE.valueOf(node.get("value").asText());
    }

}
