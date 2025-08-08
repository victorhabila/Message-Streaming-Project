package com.MessageStreaming.MessageStreamingApp.cassandra;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CassandraRepository<MessageEntity, UUID> {

}
