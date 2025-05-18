package com.menuplus.backend.library.common;

import com.menuplus.backend.library.security.UserDetailsCustom;
import com.menuplus.backend.library.util.HashUtil;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizationService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private SetOperations<String, Object> listOperations;

    @PostConstruct
    void init() {
        listOperations = redisTemplate.opsForSet();
    }

    public void addToken(Long userId, String token) {
        String hashedToken = HashUtil.sha1(token);
        log.info("addToken: userId: {}, token: {}", userId, hashedToken);
        String key = String.format("menuplus_token_%d", userId);
        listOperations.add(key, hashedToken);
    }

    public boolean checkToken(Long userId, String token) {
        String hashedToken = HashUtil.sha1(token);
        log.info("checkToken: userId: {}, token: {}", userId, hashedToken);
        String key = String.format("menuplus_token_%d", userId);
        return Boolean.TRUE.equals(listOperations.isMember(key, hashedToken));
    }

    public void removeTokenSession() {
        log.info("removeTokenSession");
        Long userId = UserDetailsCustom.getCurrentUserId();
        String token = RequestHelper.getJwt();
        removeToken(userId, token);
    }

    public void removeTokenUser(boolean excludeCurrentSession) {
        log.info("removeTokenUser");
        Long userId = UserDetailsCustom.getCurrentUserId();
        String key = String.format("menuplus_token_%d", userId);
        String currentToken = HashUtil.sha1(
            Objects.requireNonNull(RequestHelper.getJwt())
        );

        Set<Object> tokens = listOperations.members(key);
        if (tokens != null) {
            tokens.forEach(token -> {
                String tokenStr = (String) token;
                if (
                    !excludeCurrentSession ||
                    !Objects.equals(tokenStr, currentToken)
                ) removeHash(userId, (String) token);
            });
        }
    }

    public void removeToken(List<Long> userIds) {
        if (userIds == null) return;
        userIds.forEach(this::removeToken);
    }

    public void removeToken(Long userId) {
        log.info("removeToken: userId: {}", userId);
        String key = String.format("menuplus_token_%d", userId);
        Set<Object> tokens = listOperations.members(key);
        if (tokens != null) {
            tokens.forEach(token -> removeHash(userId, (String) token));
        }
    }

    public void removeToken(Long userId, String token) {
        String hashedToken = HashUtil.sha1(token);
        String key = String.format("menuplus_token_%d", userId);
        listOperations.remove(key, hashedToken);
    }

    private void removeHash(Long userId, String hashedToken) {
        String key = String.format("menuplus_token_%d", userId);
        listOperations.remove(key, hashedToken);
    }
}
