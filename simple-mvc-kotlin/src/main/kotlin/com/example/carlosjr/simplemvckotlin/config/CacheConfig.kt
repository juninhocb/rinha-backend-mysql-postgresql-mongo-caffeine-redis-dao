package com.example.carlosjr.simplemvckotlin.config

import com.example.carlosjr.simplemvckotlin.person.Person
import com.example.carlosjr.simplemvckotlin.person.PersonDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class CacheConfig {

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory)
        : RedisTemplate<String, PersonDto> {

        val template = RedisTemplate<String, PersonDto>();

        template.connectionFactory = factory
        template.keySerializer = StringRedisSerializer()

        val jsonSerializer = Jackson2JsonRedisSerializer(PersonDto::class.java)
        template.valueSerializer = jsonSerializer

        return template;
    }
}