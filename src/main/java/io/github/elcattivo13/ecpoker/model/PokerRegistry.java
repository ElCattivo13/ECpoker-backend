package io.github.elcattivo13.ecpoker.model;

public class PokerRegistry {
  
  private Map<UUID, PokerSession> sessions = new HashMap<>();
  
  public void register(PokerSession session) {
    sessions.put(session.getSessionId(), session);
  }
  
  public long cleanup() {
    List<UUID> oldIds = sessions.entrySet().stream()
        .filter(e -> e.valuem().isOld())
        .map(e -> e.key())
        .collect(Collectors.toList());
        
    sessions.removeAll(oldIds);
    
    return oldIds.size();
  }
  
  public Optional<PokerSession> getSession(UUID sessionId) {
    return Optional.ofNullable(this.sessions.get(sessionId));
  }
}