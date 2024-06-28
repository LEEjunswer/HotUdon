package com.HotUdon.repository.chatMessage;

import com.HotUdon.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage ,Long> {
}
