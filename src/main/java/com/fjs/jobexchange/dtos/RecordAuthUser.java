package com.fjs.jobexchange.dtos;

import com.fjs.jobexchange.models.Authentication;
import com.fjs.jobexchange.models.User;
import lombok.Builder;

@Builder
public record RecordAuthUser(Authentication authentication, User user) {}
