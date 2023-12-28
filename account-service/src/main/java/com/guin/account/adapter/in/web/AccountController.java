package com.guin.account.adapter.in.web;

import com.guin.account.adapter.in.web.dto.AccountCreateRequest;
import com.guin.account.port.in.AccountUseCase;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountUseCase accountUseCase;

  @PostMapping
  public ResponseEntity<String> create(
      @Valid @RequestBody AccountCreateRequest accountCreateRequest) {
    final String userId = accountUseCase.create(accountCreateRequest);
    return ResponseEntity.created(URI.create("/account/%d".formatted(userId)))
        .body(userId);
  }

}
