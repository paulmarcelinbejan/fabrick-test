package io.github.paulmarcelinbejan.fabrick.utility;

import org.apache.commons.lang3.StringUtils;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.exception.FabrickException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FabrickUtils {

	public static <RESPONSE> void throwFabrickExceptionForUnexpectedApiResponse(String url, RESPONSE response) {
		if (response == null) {
			throw new FabrickException("Received unexpected response from " + url);
		}
	}
	
	public static <RESPONSE extends FabrickApiResponse<?>> void throwFabrickExceptionForKO(String url, RESPONSE response) {
		if (StringUtils.equals(response.getStatus(), "KO")) {
			throw new FabrickException("KO from " + url + " with following errors: " + response.getError());
		}
	}

}
