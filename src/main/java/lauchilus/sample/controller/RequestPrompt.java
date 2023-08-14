package lauchilus.sample.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPrompt(
		@NotBlank
		String type,
		@NotNull
		Integer rows,
		@NotBlank
		String columns,
		@NotBlank
		String name
		) {

}
