package com.neevin.klerk.service;

import bitronix.tm.BitronixTransactionManager;
import com.neevin.klerk.entity.Article;
import com.neevin.klerk.entity.ArticleStatus;
import com.neevin.klerk.entity.Role;
import com.neevin.klerk.exception.UserBanException;
import com.neevin.klerk.exception.UserNotFoundException;
import com.neevin.klerk.exception.UserUnbanException;
import com.neevin.klerk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
}
