package io.github.elcattivo13.ecpoker.model;

import java.util.*;
import java.util.stream.Collectors;

public class PokerRegistry {
  
  private final Map<UUID, PokerSession> sessions = new HashMap<>();
  
  public void register(PokerSession session) {
    sessions.put(session.getSessionId(), session);
  }
  
  public long cleanup() {
    List<UUID> oldIds = sessions.entrySet().stream()
        .filter(e -> e.getValue().isOld())
        .map(Map.Entry::getKey)
        .toList();
        
    oldIds.forEach(sessions::remove);
    
    return oldIds.size();
  }
  
  public Optional<PokerSession> getSession(UUID sessionId) {
    return Optional.ofNullable(this.sessions.get(sessionId));
  }
}