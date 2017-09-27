package com.ddb.xaplan.cadre.config;

import java.io.Serializable;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisConfig {
	List<String> nodes;

	/**
	 * Get initial collection of known cluster nodes in format
	 * {@code host:port}.
	 *
	 * @return
	 */
	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	@Bean
	public RedisConnectionFactory getRedisConnectionFactory() {
		return new JedisConnectionFactory(new RedisClusterConfiguration(getNodes()));
	}

	@Bean
	public RedisTemplate<String, Serializable> getRedisTemplate() {
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<String, Serializable>();
		redisTemplate.setConnectionFactory(getRedisConnectionFactory());
		setMySerializer(redisTemplate);
		return redisTemplate;
	}

	/**
	 * 设置序列化方法
	 */
	private void setMySerializer(RedisTemplate<String, Serializable> redisTemplate) {
		JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
		redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
		redisTemplate.setValueSerializer(jdkSerializer);
	}
}
