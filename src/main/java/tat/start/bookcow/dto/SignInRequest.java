package tat.start.bookcow.dto;

public record SignInRequest(
    String login,
    String password
) {
}
