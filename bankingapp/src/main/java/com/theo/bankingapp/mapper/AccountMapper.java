package com.theo.bankingapp.mapper;

import com.theo.bankingapp.dto.AccountDto;
import com.theo.bankingapp.entity.Account;

public class AccountMapper {

    // Method to map AccountDto to Account
    public static Account mapToAccount(AccountDto accountDto) {
        return new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
        );
    }

    // Method to map Account to AccountDto
    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
    }
}
