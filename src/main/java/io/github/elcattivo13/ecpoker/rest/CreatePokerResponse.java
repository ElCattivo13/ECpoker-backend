package io.github.elcattivo13.ecpoker.rest;

public class CreatePokerResponse {

  private UUID: sessionId;
  private UUID: adminKey;

    public CreatePokerResponse(PokerSession session) {
      this.sessionId = session.getSessionId();
      this.adminKey = session.getAdminKey();
    }

  public UUID getSessionId() {
    return this.sessionId;
  }
  
  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }
  
  public UUID getAdminKey() {
    return this.adminKey;
  }
  
  public void setAdminKey(UUID adminKey) {
    this.adminKey = adminKey;
  }
  
}