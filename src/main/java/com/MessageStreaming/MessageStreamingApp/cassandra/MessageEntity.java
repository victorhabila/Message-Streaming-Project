package com.MessageStreaming.MessageStreamingApp.cassandra;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data // Generates getters, setters, equals, hashCode, toString, and required
      // constructors
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
@Table("messages")
public class MessageEntity {
    @PrimaryKey
    private UUID id;
    private String message;
}
