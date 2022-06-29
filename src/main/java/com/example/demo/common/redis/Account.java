package com.example.demo.common.redis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@ApiModel(value = "로그인 계정")
@RedisHash(value = "accounts", timeToLive = 300)
public class Account {

    @ApiModelProperty(value = "Id", required = true, position = 1, example = "1")
    @Id
    private Long id;

    @ApiModelProperty(value = "이메일", required = true, position = 2, example = "user@mail.com")
    private String email;

    @ApiModelProperty(value = "이름", required = true, position = 3, example = "GilDong")
    private String fullname;

}
