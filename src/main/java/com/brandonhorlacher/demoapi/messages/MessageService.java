package com.brandonhorlacher.demoapi.messages;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message createMessage(@NonNull Message message) {
        return null;
    }

    public Message getMessage(long id) {
        return null;
    }

    public List<Message> getMessages(long fromDate) {
        return null;
    }

    public void deleteMessage(long id) {

    }

    @Scheduled(cron = "0 0 0 * * *")
    public void clearMessages() {
        log.info("Deleting all messages from DB...");
        messageRepository.deleteAll();
        log.info("Deleted all messages from DB.");
    }
}
