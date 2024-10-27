package com.closedsource.psymed.platform.iam.application.commandservices;

import com.closedsource.psymed.platform.iam.domain.model.aggregate.Account;
import com.closedsource.psymed.platform.iam.domain.model.commands.SignInCommand;
import com.closedsource.psymed.platform.iam.domain.model.commands.SignUpCommand;
import com.closedsource.psymed.platform.iam.domain.service.AccountCommandService;
import com.closedsource.psymed.platform.iam.infrastructure.persistence.jpa.repositories.AccountRepository;
import org.apache.catalina.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public class AccountCommandServiceImpl implements AccountCommandService {
    private final AccountRepository accountRepository;

    public AccountCommandServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(SignUpCommand command) {
        if(accountRepository.existsByUserName(command.username()))
            throw new RuntimeException("User already exists");
        var account = new Account(command);

        try{
            var accountCreated = accountRepository.save(account);
            return Optional.of(accountCreated);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<ImmutablePair<Account, String>> handle(SignInCommand command) {
        var accountExists = accountRepository.existsByUserName(command.username());
        if(accountExists) {
            var account = accountRepository.findByUserName(command.username());
            return Optional.of(ImmutablePair.of(account.get(), "User found"));
        }
        throw new RuntimeException("User not found");
    }
}
