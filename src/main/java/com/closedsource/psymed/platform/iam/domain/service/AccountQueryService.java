package com.closedsource.psymed.platform.iam.domain.service;

import com.closedsource.psymed.platform.iam.domain.model.aggregate.Account;
import com.closedsource.psymed.platform.iam.domain.model.queries.GetAccountByIdQuery;

import java.util.List;

public interface AccountQueryService {
    List<Account> handle(GetAccountByIdQuery query);
}
